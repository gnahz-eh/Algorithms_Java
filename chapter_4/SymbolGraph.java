public class SymbolGraph {
    private ST<String, Integer> st; //符号名——>索引
    private String[] keys;          //索引——>符号名
    private Graph G;

    public SymbolGraph(String stream, String sp) {

        st = new ST<String, Integer>();
        In in = new In(stream);
        while(in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for(int i = 0; i < a.lengrh(); i++) {
                if(!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        
        keys = new String[st.size()];
        for(String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
        in = new In(stream);
        while(in.hasNextLine()) {
            String[] a = in.readLine().split();
            int v = st.get(a[0]);
            for(int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }
}
