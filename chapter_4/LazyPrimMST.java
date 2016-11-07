//带权重边的数据类型
public class Edge implements Comparable<Edge> {
    private final int v;//顶点之一
    private final int w;//另一个顶点
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if(vertex == v) return w;
        if(vertex == w) return v;
    }

    public int compareTo(Edge that) {
        if(this.weight() < that.weight()) return -1;
        else if(this.weight() > that.weight()) return 1;
        else return 0;
    }
}

//加权无向图的数据类型
public class EdgeWeightedGraph {
    private final int v;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[])new Bag[V];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for(int v = 0; v < V; v++) {
            for(Edge e : adj[v]) {
                if(e.other(v) > v) {
                    b.add(e);
                }
            }
        }
        return b;
    }
}

public class LazyPrimMST {
    private boolean[] marked;//最小生成树的顶点
    private Queue<Edge> mst;//最小生成树的边
    private MinPQ<Edge> pq;//横切边

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0);
        while(!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(marked[v] && marked[w]) continue;//跳过失效的边
            mst.enqueue(e);
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(Edge e : G.adj(v)) {
            if(!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }
}
