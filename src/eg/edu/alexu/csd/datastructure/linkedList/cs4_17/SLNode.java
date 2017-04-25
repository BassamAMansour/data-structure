package eg.edu.alexu.csd.datastructure.linkedList.cs4_17;

/**
 * Created by Bassam on 4/9/2017.
 */
public class SLNode {

    Object element;
    SLNode next;

    public SLNode(Object element, SLNode next) {
        this.element = element;
        this.next = next;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    public SLNode getNext() {
        return next;
    }

    public void setNext(SLNode next) {
        this.next = next;
    }
}
