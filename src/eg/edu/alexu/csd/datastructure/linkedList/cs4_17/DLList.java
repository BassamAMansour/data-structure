package eg.edu.alexu.csd.datastructure.linkedList.cs4_17;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Created by Bassam on 4/9/2017.
 */
public class DLList implements ILinkedList {

    private DLNode head, tail;
    private int size;

    public DLList() {
        initializeDLL();
    }

    @Override
    public void add(int index, Object element) {

        if (isIndexInRange(index) || index == size()) {

            DLNode beforeNode = getNodeAt(index - 1);
            DLNode afterNode = beforeNode.getNext();

            DLNode nodeToAdd;

            if (element == null) {
                nodeToAdd = new DLNode(null, beforeNode, afterNode);
            } else {
                nodeToAdd = new DLNode(element, beforeNode, afterNode);
            }
            beforeNode.setNext(nodeToAdd);
            afterNode.setPrevious(nodeToAdd);

            size++;
        } else {
            printErrorMessage("add()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }
    }

    @Override
    public void add(Object element) {

        DLNode beforeLast = tail.getPrevious();

        DLNode nodeToAdd;

        if (element == null) {
            nodeToAdd = new DLNode(null, beforeLast, tail);
        } else {
            nodeToAdd = new DLNode(element, beforeLast, tail);
        }

        beforeLast.setNext(nodeToAdd);
        tail.setPrevious(nodeToAdd);

        size++;
    }

    @Override
    public Object get(int index) {
        if (isIndexInRange(index)) {
            return getNodeAt(index).getElement();
        } else {
            printErrorMessage("get()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }
    }

    @Override
    public void set(int index, Object element) {
        if (isIndexInRange(index)) {
            getNodeAt(index).setElement(element);
        } else {
            printErrorMessage("set()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }
    }

    @Override
    public void clear() {

        DLNode firstNode = head.getNext();
        DLNode lastNode = tail.getPrevious();

        firstNode.setPrevious(null);
        lastNode.setNext(null);

        initializeDLL();
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void remove(int index) {
        if (isIndexInRange(index)) {

            DLNode nodeToDelete = getNodeAt(index);
            DLNode previousNode = nodeToDelete.getPrevious();
            DLNode nextNode = nodeToDelete.getNext();

            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
            nodeToDelete.setNext(null);
            nodeToDelete.setPrevious(null);
            nodeToDelete = null;

            size--;
        } else {
            printErrorMessage("remove()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {

        if (isIndexInRange(fromIndex) && isIndexInRange(toIndex)) {
            ILinkedList subList = new DLList();
            DLNode firstNode = getNodeAt(fromIndex);

            for (int i = fromIndex; i <= toIndex; i++) {
                subList.add(firstNode.getElement());
                firstNode = firstNode.getNext();
            }

            return subList;
        } else {
            printErrorMessage("sublist()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }
    }

    @Override
    public boolean contains(Object o) {

        boolean found = false;
        DLNode dummy = head;

        for (int i = 0; i < size(); i++) {
            dummy = dummy.getNext();
            if (dummy.getElement() == null && o == null){
                found = true;
                break;
            }
            else if(dummy.getElement() == null && o != null){
                found = false;
            }
            else if (dummy.getElement().equals(o)) {
                found = true;
                break;
            }
        }

        return found;
    }

    private DLNode getNodeAt(int index) {

        if (isIndexAtFirstHalf(index)) {
            DLNode node = head;

            for (int i = 0; i <= index; i++) {
                node = node.getNext();
            }

            return node;
        } else {
            DLNode node = tail;

            for (int i = (size() - 1); i >= index; i--) {
                node = node.getPrevious();
            }

            return node;
        }

    }

    private boolean isIndexAtFirstHalf(int index) {
        if (index <= ((size() - 1) / 2)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isIndexInRange(int index) {
        if (index < size() && index >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initializeDLL() {
        this.head = new DLNode("HEAD", null, this.tail);
        this.tail = new DLNode("TAIL", this.head, null);
        this.size = 0;
    }

    private void printErrorMessage(String functionName, String message) {
        //TODO: print the error to the logs
        System.out.println("Error at: " + functionName + "\nError type: " + message);
    }
}
