
public class maxFlow {

  Kattio io;
  int s;
  int t;
  int v;
  int e;
  ArrayList<ArrayList<Edge>> edges;

  private void readFlowGraph() {

    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    edges = new ArrayList<ArrayList<Edge>>(e);

    for (int i = 0; i < e; ++i) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      Edge xEdge = new (Edge(x, y, c, 0));
      Edge yEdge = new (Edge(y, x, -c, 0));
      xEdge.setReverse(yEdge);
      yEdge.setReverse(xEdge);
      edges.add(x, xEdge);
      edges.add(y, yEdge);
    }
  }

  private void solveFlow() {
    boolean[] visited = new boolean[v];
    Edge edge;
    int vertex = s;
    edge = getNextEdge(visited, vertex);
    // add handling of the recursion results

  }
  
  // fix this, it doesn't work!
  private Edge getNextEdge(boolean[] visited, int vertex) {
    int i = 0;
    while (edges(vertex).hasNext()) {
      edge = edges(vertex).get());
      if (edge.getCap()>0 && !visited[vertex]) {
        if !edge==t {
          getNextEdge(visited, edge.getEnd());
        }
        visited[vertex] = true;
        return edge;
      }
    }
    else return null;
  }


  private int recursion(boolean[] visited, int vertex, int miniCap) {
    if (vertex != t) {
      edge = getNextEdge(visited, vertex);
      int cap = min(edge.getCap(), miniCap);
      flow = recursion(visited, edge.getEnd, cap);
      edge.setFlow(flow);
      edge.setCap(-flow);
      edge.getReverse().setFlow(-flow);
      edge.getReverse().setCap(flow);
      return cap;
    }
    else return cap;
    }
  }

}