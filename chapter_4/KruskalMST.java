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

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        UF uf = new UF(G, V());
        while (!pq.isEmpty() && mst.size() < G.V()-1) {
            Edge e = pq.deleteMin();
            int v = e.either();
            int w = e.other(v);
            if(uf.connection(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
    }
}
