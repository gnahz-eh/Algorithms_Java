//基于单词查找树的符号表

public class TrieST<Value> {

    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value)x.val;
    }

    //返回以x作为根节点的子单词查找树中与key相关联的值
    private Node get(Node x, String key, int d) {
        if(x == null) {
            return null;
        }
        if(d == key.length()) {
            return x;
        }

        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }

        if(d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt[d];
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }
}
