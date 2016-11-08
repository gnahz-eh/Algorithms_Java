public class DirectedEdge {
    private final int v;//边的起点
    private final int w;//边的终点
    private final double weight;//边的权重

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }
} 

public class EdgeWeightedDigraph {
    private final int V;                //顶点总数
    private int E;                      //边的总数
    private Bag<DirectedEdge>[] adj;    //邻接表

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[v];
        for(int v = 0; v < V; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge e) {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for(int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }
        return bag;
    }    
}

//无环加权有向图的最短路径算法
public class AcyclicSP {

    private DirectedEdge[] edgeTo;
    private double[] distTo;
    
    public AcyclicSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];

        for(int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        distTo[s] = 0.0;

        //拓扑排序
        Topological top = new Topological(G);
        for(int v : top.order()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for(DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if(!hasPathTo(v)) {
            return null;
        }
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
