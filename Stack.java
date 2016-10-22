public class Stack<Item> implements Iterable<Item>{

    private Node first;
    private int N;
    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return frist == null;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        frist.next = oldfirst;
        N++;
    }

    public Item pop() {
        Item item = frist.item;
        first = first.next;
        N--;
        return item;
    }
}
