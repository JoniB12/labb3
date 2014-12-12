import java.util.ArrayList;
import java.lang.Math;

public class maxFlowBra {

  Kattio io = new Kattio(System.in, System.out);
  int s;
  int t;
  int v;
  int e;
  int flow;
  ArrayList<ArrayList<Edge>> edges;

  private void readFlowGraph() {
    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    edges = new ArrayList<ArrayList<Edge>>();
    for (int i = 0; i < v; i++) {
      // ArrayList<Edge> list = ;
      edges.add(new ArrayList<Edge>());
    }
    for (int i = 0; i < e; i++) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      if (!checkExist(x, y)) {
        Edge xEdge = new Edge(x, y, c, 0);
        Edge yEdge = new Edge(y, x, -c, 0);
        xEdge.setReverse(yEdge);
        yEdge.setReverse(xEdge);
        edges.get(x).add(xEdge);
        edges.get(y).add(yEdge);
      }
    }
  }

  private void solveFlow() {
    boolean[] visited;
    Edge edge;
    int vertex = s;
    int cap = -1;
    flow = 0;
    while (cap != 0) {
      visited = new boolean[v];
      visited[s] = true;
      cap = recursion(visited, s, 0);
      flow += cap;
    }
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
    return null;
  }

  // Calculates one path from S to T
  private int recursion(boolean[] visited, int vertex, int miniCap) {
    int cap;
    if (vertex != t) {
      Edge edge = getNextEdge(visited, vertex);
      if (edge != null) {
        cap = Math.min(edge.getCap(), miniCap);
        flow = recursion(visited, edge.getEnd(), cap);
        edge.setFlow(flow);
        edge.setCap(-flow);
        edge.getReverse().setFlow(-flow);
        edge.getReverse().setCap(flow);
        return cap;
      }
      else return 0;
    }
    else return cap;
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

  private void writeGraph() {
    ArrayList<int[]> matrix = getMatrix();
    int e = matrix.size();
    io.print(v);
    io.print(s + " " + t + " " + flow);
    io.print(e);
    for (int i = 0; i < e; i++) {
      int[] edge = matrix.get(i);
      io.print(edge[0] + " " + edge[1] + " " + edge[2]);
    }
  }

  private ArrayList<int[]> getMatrix() {
    ArrayList<int[]> matrix = new ArrayList<int[]>();
    int sizeX = edges.size();
    for (int i = 0; i < sizeX; i++) {
      ArrayList<Edge> edgeX = edges.get(i);
      int sizeY = edgeX.size();
      for (int j = 0; j < sizeY; j++) {
        Edge curEdge = edgeX.get(j);
        if (curEdge.getFlow() > 0) {
          int[] edge = {curEdge.getStart(), curEdge.getEnd(), curEdge.getFlow()};
          matrix.add(edge);
        }
      }
    }
    return matrix;
  }

}