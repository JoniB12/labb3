import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
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
    for (int i = 0; i < e; i++) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      edges[x][y] = c;
    }
  }


  private int bfs() {
    F = new int[v+1][v+1];
    while (true) {
      path = new int[v+1];
      Arrays.fill(path, -1);
      path[s] = s;
      int maxflow = Integer.MAX_VALUE;
      ArrayDeque<Integer> fifo = new ArrayDeque<Integer>();
      fifo.add(s);
      while (!fifo.isEmpty()) {
        int vertex = fifo.poll();
        for (int i = 1; i < edges[vertex].length; i++) {
          if (edges[vertex][i]-F[vertex][i] > 0 && path[i] == -1) {
            path[i] = vertex;
            maxflow = Math.min(maxflow, edges[vertex][i] - F[vertex][i]);
            if (i != t) {
              fifo.add(i);
            }
            else {
              while (path[i] != i) {
                vertex = path[i];
                F[vertex][i] += maxflow;
                F[i][vertex] -= maxflow;
                i = vertex;
              }
              totFlow += maxflow;
            }
          }
        }
      }
      if (path[t] == -1) {
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
    }
    io.flush();
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