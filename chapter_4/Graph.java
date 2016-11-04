public class Graph {
    private final int v;
    private int E;
    private Bag<Integer>[] adj;

    private class Bag<Item> implements Iterable<Item> {

        private Node first;
        private class Node {
            Item item;
            Node next;
        }

        public void add(Item item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }

        public Iterator<Item> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<Item> {

            private Node current = first;
            public boolean hasNext() {
                return current != null;
            }
            public void remove() {}
            public Item next() {
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }
    
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
} 
