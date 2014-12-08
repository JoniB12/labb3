
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
      if (!checkExist(x, y)) {
        Edge xEdge = new (Edge(x, y, c, 0));
        Edge yEdge = new (Edge(y, x, -c, 0));
        xEdge.setReverse(yEdge);
        yEdge.setReverse(xEdge);
        edges.add(x, xEdge);
        edges.add(y, yEdge);
      }
    }
  }

  private void solveFlow() {
    boolean[] visited = new boolean[v];
    Edge edge;
    int vertex = s;
    edge = getNextEdge(visited, vertex);
    // add handling of the recursion results

  }

  // fixed, might actually work
  private Edge getNextEdge(boolean[] visited, int x) {
    ArrayList<Edge> edgeX = edges.get(x);
    int size = edgeX.size();
    for (int i = 0; i < size; i++) {
      Edge curEdge = edgeX.get(i);
      if (curEdge.getCap()>0 && !visited[curEdge.getEnd()]) {
        visited[curEdge.getEnd()] = true;
        return curEdge;
      }
    }
    else return null;
  }

  // Calculates one path from S to T
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

  private boolean checkExist(int x, int y) {
    ArrayList<Edge> edgeX = edges.get(x);
    int size = edgeX.size();
    for (int i = 0; i < size; i++) {
      if (edgeX.get(i).getEnd() == y) {
        return true;
      }
    }
    return false;
  }

}