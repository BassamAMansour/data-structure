package eg.edu.alexu.csd.datastructure.linkedList.cs4_17;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

/**
 * Created by Bassam on 4/9/2017.
 */
public class SLList implements ILinkedList {

    private SLNode head, tail;
    private int size;

    public SLList() {
        initializeSLL();
    }

    @Override
    public void add(int index, Object element) {

        if (isIndexInRange(index) || index == size()) {

            SLNode beforeNode = getNodeAt(index - 1);
            SLNode afterNode = beforeNode.getNext();

            SLNode nodeToAdd;

            if (element == null) {
                nodeToAdd = new SLNode(null, afterNode);
            } else {
                nodeToAdd = new SLNode(element, afterNode);
            }

            beforeNode.setNext(nodeToAdd);

            size++;
        } else {
            printErrorMessage("add()", ErrorMessages.INDEX_OUT_OF_RANGE);
            throw null;
        }
    }

    @Override
    public void add(Object element) {

        SLNode nodeToAdd;

        if (element == null) {
            nodeToAdd = new SLNode(null, tail);
        } else {
            nodeToAdd = new SLNode(element, tail);
        }

        SLNode beforeLast = getNodeAt(size() - 1);
        beforeLast.setNext(nodeToAdd);

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

        SLNode lastNode = getNodeAt(size() - 1);

        lastNode.setNext(null);

        initializeSLL();

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

            SLNode previousNode = getNodeAt(index - 1);
            SLNode nodeToDelete = previousNode.getNext();

            previousNode.setNext(nodeToDelete.getNext());
            nodeToDelete.setNext(null);
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
            ILinkedList subList = new SLList();
            SLNode firstNode = getNodeAt(fromIndex);

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
        SLNode dummy = head;

        for (int i = 0; i < size(); i++) {

            dummy = dummy.getNext();

            if (dummy.getElement() == null && o == null) {
                found = true;
                break;
            } else if (dummy.getElement() == null && o != null) {
                found = false;
            } else if (dummy.getElement().equals(o)) {
                found = true;
                break;
            }
        }

        return found;
    }

    private SLNode getNodeAt(int index) {

        SLNode node = head;

        for (int i = 0; i <= index; i++) {
            node = node.getNext();
        }

        return node;
    }

    private boolean isIndexInRange(int index) {
        if (index < size() && index >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private void initializeSLL() {
        this.tail = new SLNode("TAIL", null);
        this.head = new SLNode("HEAD", this.tail);
        this.size = 0;
    }

    private void printErrorMessage(String functionName, String message) {
        //TODO: print the error to the logs
        System.out.println("Error at: " + functionName + "\nError type: " + message);
    }

}
