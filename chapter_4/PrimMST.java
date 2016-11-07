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

public class PrimMST {
    private Edge[] edgeTo;          //距离树最近的边
    private double[] distTo;        //distTo[w] = edgeTo[w].weight()
    private boolean[] marked;       //如果v在树中则为true
    private IndexMinPQ<double> pq;  //有效的横切边
    
    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);          //用顶点0和权重0初始化pq
        while(!pq.isEmpty()) {
            visit(G, pq.deleteMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for(Edge e : G.adj(v)) {
            int w = e.other();
            if(marked[w]) continue; //v-w失效
            if(e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)) {
                    pa.change(w, distTo[w]);
                } else {
                    pa.insert(w, distTo[w]);
                }
            }
        }
    }
}
