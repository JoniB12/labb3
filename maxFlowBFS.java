import java.util.ArrayList;
import java.lang.Math;

public class maxFlow {

  Kattio io = new Kattio();
  int s;
  int t;
  int v;
  int e;
  int totFlow;
  int[][] edges;
  int[][] F;
  int[] path;
  int[] maxflow;

  private void readFlowGraph() {
    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    edges = new int[v+1][v+1];
    
    for (int i = 0; i < e; i++) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      if (int[x][y] != null) {
        int[x][y] += c;
      }
      else int[x][y] = c;
    }
  }

  // private void solveFlow() {
  //   boolean[] visited;
  //   int flow = -1;
  //   totFlow = 0;
  //   while (flow != 0) {
  //     visited = new boolean[v+1];
  //     visited[s] = true;
  //     flow = recursion(visited, s, 0);
  //     totFlow += flow;
  //   }
  // }

  // fixed, might actually work
  // private int getNextEdge(boolean[] visited, int x) {
  //   for (int i = 0; i < v+1; i++) {
  //     Edge curEdge = edgeX.get(i);
  //     if (curEdge.getCap()>0 && !visited[curEdge.getEnd()]) {
  //       visited[curEdge.getEnd()] = true;
  //       return curEdge;
  //     }
  //   }
  //   return null;
  // }

  private void bfs() {
    F = new int[v+1][v+1];
    path = new int[v+1];
    Arrays.fill(path, -1);
    maxflow = new int[v+1];
    maxflow[s] = Integer.MAX_VALUE;
    Queue<Integer> fifo = new LinkedList<Integer>();
    fifo.offer(s);
    while (!fifo.isEmpty()) {
      int vertex = fifo.poll();
      for (int i = 1; i < edges[vertex].length; i++) {
        if (edges[vertex][i]-F[vertex][i] > 0 && path[i] == -1) {
          path[i] = vertex;
          M[i] = Math.min(maxflow[vertex], edges[vertex][i] - F[vertex][i]);
          if (i != t) {
            fifo.offer(i);
          }
          else {
            while (path[i] != i) {
              vertex = path[i];
              F[vertex][i] += maxflow[t];
              F[i][vertex] -= maxflow[t];
              i = vertex;
            }
          }
        }
      }
    }
    if (path[t] == -1) {
      totFlow = 0;
      for (int i; i < F[s].length; i++) {
        totFlow += F[s];
      }
    }
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