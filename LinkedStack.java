import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
    private Node topNode; // references the first node in the chain

    private class Node{
        private T data; // entry in stack
        private Node next; // link to next node

        private Node(){
            this(null, null);
        }
        private Node(T data, Node nextNode){
            this.data = data;
            this.next = nextNode;
        }
        private T getData(){
            return data;
        }
        private void setData(T data){
            this.data = data;
        }
        private Node getNextNode(){
            return next;
        }
        private void setNextNode(Node nextNode){
            this.next = nextNode;
        }
    } // end Node

    public LinkedStack(){
        topNode = null;
    }// end default constructor

    @Override
    public void push(T newEntry) {
        topNode = new Node(newEntry, topNode);
    } // end push

    @Override
    public T pop() {
        T top = peek(); // Might throw EmptyStackException
        // Assertion: topNode != null
        topNode = topNode.getNextNode();
        return top;
    } // end pop

    @Override
    public T peek() {
        if(isEmpty())
            throw new EmptyStackException();
        else
            return topNode.getData();
    } // end peek

    @Override
    public boolean isEmpty() {
        return topNode == null;
    } // end isEmpty

    @Override
    public void clear() {
        topNode = null;
    } // end clear
} // end LinkedStack
