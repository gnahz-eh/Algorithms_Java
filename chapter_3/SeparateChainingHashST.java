
public class SeparateChainingHashST<Key, Value> {
    private int N;//键值对的总数
    private int M;//散列表的大小
    
    private SequentialSearchST<Key, Value>[] st;//存放链表对象的数组

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for(int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST();
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0X7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st.[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        st[hash(key)].put(key, val);
    }

    //散列表内部使用的链表内部类
    private class SequentialSearchST<Key, Value> {
    
        private Node first;
    
        private class Node {
            Key key;
            Value val;
            Node next;
    
            public Node(Key key, Value val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }
    
        public Value get(Key key) {
            
            for(Node x = first; x != null; x = x.next) {
                if(key.equals(x.key)) {
                    return x.val;
                }
            }
            return null;
        }
    
        public void put(Key key, Value val) {
    
            for(Node x = first; x != null; x = x.next) {
                if(key.equals(x.key)) {
                    x.val = val;
                    return;
                }
            }
            first = new Node(key, val, first);
        }    
    }
}
