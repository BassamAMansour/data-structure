package eg.edu.alexu.csd.datastructure.stack.cs17;/**
 * Created by Bassam on 4/20/2017.
 */

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

public class ExpressionEvaluator implements IExpressionEvaluator {

    public static void main(String[] args) {

    }

    @Override
    public String infixToPostfix(String expression) {

        String postFixExpression = "";
        Stack operatorsStack = new Stack();
        expression = expression.replaceAll("\\s", "");

        checkInFixExpressionValidity(expression);

        for (int i = 0; i < expression.length(); i++) {

            Character character = expression.charAt(i);

            if (isRightParentheses(character)) {
                while (!operatorsStack.peek().equals('(')) {
                    postFixExpression += (Character) operatorsStack.pop();
                }
                operatorsStack.pop();
            } else if (isLeftParentheses(character)) {
                operatorsStack.push(character);
            } else if (isOperator(character)) {
                if ((operatorsStack.size() == 0) || hasHigherPrecedence(character, (Character) operatorsStack.peek()) || isLeftParentheses((Character) operatorsStack.peek())) {
                    operatorsStack.push(character);
                } else {
                    postFixExpression += (Character) operatorsStack.pop();
                    i--;
                }
            } else {
                postFixExpression += character;
            }
        }

        postFixExpression += popAllElementsOfTheStack(operatorsStack);

        postFixExpression = makeSpacesBetweenLiterals(postFixExpression);

        return postFixExpression;
    }


    @Override
    public int evaluate(String expression) {


        Stack operandsStack = new Stack();

        expression = expression.replaceAll("\\s", "");

        checkPostFixExpressionValidity(expression);

        float value;

        for (int i = 0; i < expression.length(); i++) {

            Character character = expression.charAt(i);

            if (Character.isDigit(character)) {

                operandsStack.push((float) Character.getNumericValue(character));
            } else {

                float result;
                float secondOperand = (float) operandsStack.pop();
                float firstOperand = (float) operandsStack.pop();

                switch (character) {
                    case '+':
                        result = firstOperand + secondOperand;
                        break;
                    case '-':
                        result = firstOperand - secondOperand;
                        break;
                    case '*':
                        result = firstOperand * secondOperand;
                        break;
                    case '/':
                        if (secondOperand == 0) {
                            throw null;
                        }
                        result = firstOperand / secondOperand;
                        break;
                    default:
                        result = 0;
                }

                operandsStack.push(result);
            }
        }

        if (operandsStack.size() > 1) {
            return 0;
        }

        value = (float) operandsStack.pop();

        return (int) value;
    }

    private boolean isLeftParentheses(Character character) {

        if (character.equals('(')) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isRightParentheses(Character character) {

        if (character.equals(')')) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isOperator(Character character) {
        if (character.equals('+') || character.equals('-') || character.equals('*') || character.equals('/')) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasHigherPrecedence(Character firstOperator, Character secondOperator) {
        if ((firstOperator.equals('*') || firstOperator.equals('/')) && (secondOperator.equals('+') || secondOperator.equals('-'))) {
            return true;
        } else {
            return false;
        }
    }

    private String popAllElementsOfTheStack(Stack operatorsStack) {

        String postFixExpression = "";

        for (int i = 0; i <= operatorsStack.size(); i++) {
            postFixExpression += operatorsStack.pop();
        }

        return postFixExpression;
    }

    private void checkInFixExpressionValidity(String expression) {

        checkExpressionForSize(expression);
        checkExpressionForNull(expression);
        checkExpressionForUnaryOperators(expression);
        checkParenthesesEquality(expression);

    }

    private void checkPostFixExpressionValidity(String expression) {
        checkExpressionForSize(expression);
        checkExpressionForNull(expression);

    }

    private void checkExpressionForUnaryOperators(String expression) {

        boolean precedingOperator = false;

        for (int i = 0; i < expression.length(); i++) {
            if (isOperator(expression.charAt(i)) && precedingOperator) {
                throw null;
            } else if (isOperator(expression.charAt(i)) && !precedingOperator) {
                precedingOperator = true;
            } else {
                precedingOperator = false;
            }
        }
        if(isOperator(expression.charAt(expression.length()-1))){
            throw null;
        }

    }


    private void checkExpressionForNull(String expression) {
        if (expression == null) {
            throw null;
        }
    }


    private void checkExpressionForSize(String expression) {
        if (expression.length() == 0) {
            throw null;
        }
    }


    private void checkParenthesesEquality(String expression) {

        int leftParentheses = 0;
        int rightParentheses = 0;

        for (int i = 0; i < expression.length(); i++) {

            if (rightParentheses > leftParentheses) {
                throw null;
            }

            if (isLeftParentheses(expression.charAt(i))) {
                leftParentheses++;
            } else if (isRightParentheses(expression.charAt(i))) {
                rightParentheses++;
            }
        }
        if (leftParentheses != rightParentheses) {
            throw null;
        }
    }


    private String makeSpacesBetweenLiterals(String postFixExpression) {

        StringBuilder decomposedString = new StringBuilder(postFixExpression);

        for (int i = postFixExpression.length() - 1; i > 0; i--) {
            decomposedString.insert(i, " ");
        }
        return decomposedString.toString();
    }

}
