public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] vals;
    private int N;
    
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }
    
    public int size() {
        return N;
    }
    
    public Value get(Key key) {
        if(isEmpty()) {
            return null;
        }
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }
    
    public void put(Key key, Value val) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public int rank(Key key) {
        int lo = 0;
        int hi = N-1;
        while(lo <= hi) {
            int mid = (hi + lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) hi = mid - 1;
            else if(cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;//keys[]中不存在key
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N-1];
    }

    public Key select(int k) {
        return keys[k];
    }

    public Key ceiling(Key key) {
        int i = rank[key];
        return keys[i];
    }

    public Key floor(Key key) {}
    public Key delete(Key key) {}

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for(int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }
        if(contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}

lo hi mid   0 1 2 3 4 5 6 7 8 9
0  9  4     A C E H L M P R S X
5  9  7     A C E H L M P R S X
5  6  5     A C E H L M P R S X
6  6  6     A C E H L M P R S X
7  6        lo > hi时循环退出，返回7 
