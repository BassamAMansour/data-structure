package eg.edu.alexu.csd.datastructure.linkedList.cs4_17;

/**
 * Created by Bassam on 4/9/2017.
 */
public class DLNode {

    Object element;
    DLNode previous, next;

    public DLNode(Object element, DLNode previous, DLNode next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public DLNode getPrevious() {
        return previous;
    }

    public void setPrevious(DLNode previous) {
        this.previous = previous;
    }

    public DLNode getNext() {
        return next;
    }

    public void setNext(DLNode next) {
        this.next = next;
    }
}
