package eg.edu.alexu.csd.datastructure.stack.cs17;

import eg.edu.alexu.csd.datastructure.stack.IStack;

/**
 * Created by Bassam on 4/20/2017.
 */
public class Stack implements IStack {

    private SLNode head;
    private int size;

    public Stack() {
        size = 0;
        head = new SLNode(null, null);
    }

    @Override
    public void add(int index, Object element) {

        if (isIndexInRange(index) || index == size()) {

            SLNode beforeNode = getNodeAt(index - 2);
            SLNode afterNode = beforeNode.getNext();

            SLNode nodeToAdd = new SLNode(element, afterNode);

            beforeNode.setNext(nodeToAdd);

            increaseSizeByOne();
        } else {
            throw null;
        }
    }

    @Override
    public Object pop() {

        if (size() != 0) {
            SLNode firstNode = head.getNext();
            head.setNext(firstNode.getNext());
            firstNode.setNext(null);

            decreaseSizeByOne();

            return firstNode.getElement();
        } else {
            throw null;
        }
    }

    @Override
    public Object peek() {

        if (size() != 0) {
            return head.getNext().getElement();
        } else {
            throw null;
        }
    }

    @Override
    public void push(Object element) {
        SLNode nodeToAdd = new SLNode(element, head.getNext());
        head.setNext(nodeToAdd);
        increaseSizeByOne();
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseSizeByOne() {
        size++;
    }

    private void decreaseSizeByOne() {
        size--;
    }

    private boolean isIndexInRange(int index) {
        if (index < size() && index >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private SLNode getNodeAt(int index) {

        SLNode node = head;

        for (int i = 0; i <= index; i++) {
            node = node.getNext();
        }
        return node;
    }
}
