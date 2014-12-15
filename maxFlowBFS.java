import java.util.*;
import java.lang.Math;

public class maxFlowBFS {

  Kattio io = new Kattio(System.in, System.out);
  int s;
  int t;
  int v;
  int e;
  int totFlow;
  int[][] edges;
  int[][] F;
  int[] path;
  int[] maxflow;

  maxFlowBFS() {
    readFlowGraph();
    bfs();
    writeGraph();
    io.close();
  }

  private void readFlowGraph() {
    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    edges = new int[v+1][v+1];
    for (int i = 0; i < edges.length; i++) {
      Arrays.fill(edges[i], 0);
    }
    for (int i = 0; i < e; i++) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      edges[x][y] += c;
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

  private int bfs() {
    F = new int[v+1][v+1];
    while (true) {
      path = new int[v+1];
      Arrays.fill(path, -1);
      path[s] = s;
      maxflow = new int[v+1];
      maxflow[s] = Integer.MAX_VALUE;
      Queue<Integer> fifo = new LinkedList<Integer>();
      fifo.offer(s);
      while (!fifo.isEmpty()) {
        int vertex = fifo.poll();
        for (int i = 1; i < edges[vertex].length; i++) {
          if (edges[vertex][i]-F[vertex][i] > 0 && path[i] == -1) {
            path[i] = vertex;
            maxflow[i] = Math.min(maxflow[vertex], edges[vertex][i] - F[vertex][i]);
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
        for (int i = 0; i < F[s].length; i++) {
          totFlow += F[s][i];
        }
        return totFlow;
      }
    }
  }

  private void writeGraph() {
    ArrayList<int[]> matrix = getMatrix();
    int e = matrix.size();
    io.println(v);
    io.println(s + " " + t + " " + totFlow);
    io.println(e);
    for (int i = 0; i < e; i++) {
      int[] edge = matrix.get(i);
      io.println(edge[0] + " " + edge[1] + " " + edge[2]);
      io.flush();
    }
  }

  private ArrayList<int[]> getMatrix() {
    ArrayList<int[]> matrix = new ArrayList<int[]>();
    int sizeX = edges.length;
    for (int i = 0; i < sizeX; i++) {
      int sizeY = edges[i].length;
      for (int j = 0; j < sizeY; j++) {
        if (F[i][j] > 0) {
          int[] edge = {i, j, F[i][j]};
          matrix.add(edge);
        }
      }
    }
    return matrix;
  }

  public static void main(String args[]) {
    new maxFlowBFS();
  }

}