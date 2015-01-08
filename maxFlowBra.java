import java.util.ArrayList;
import java.lang.Math;
import java.util.ArrayDeque;

public class maxFlowBra {
  int s;
  int t;
  int v;
  int e;
  int totFlow;
  ArrayList<Edge>[] edges;
  int cap = 0;
  Kattio io;
  ArrayDeque<Edge> fifo;
  Edge[] path;
  Edge curEdge;
  boolean[] visited;

  maxFlowBra() {
    io = new Kattio(System.in, System.out);
    readFlowGraph();
    path = new Edge[v+1];
    edmondsKarp();
    writeGraph();
    io.close();
  }

  private void readFlowGraph() {
    v = io.getInt();
    s = io.getInt();
    t = io.getInt();
    e = io.getInt();
    edges = new ArrayList[v+1];
    
    for (int i = 0; i < e; i++) {
      int x = io.getInt();
      int y = io.getInt();
      int c = io.getInt();
      if (edges[x]==null) {
        edges[x]=new ArrayList<Edge>(); 
      }
      if (edges[y] == null){
        edges[y]=new ArrayList<Edge>();
      }
      Edge xEdge = new Edge(x, y, c, 0);
      Edge yEdge = new Edge(y, x, -c, 0);
      xEdge.setReverse(yEdge);
      yEdge.setReverse(xEdge);
      edges[x].add(xEdge);
      edges[y].add(yEdge);
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
  //int sizeX = edges.length;
    //io.println("edge.size "+edges.size());
    for (int i = 0; i <= e; i++) {
      if(edges[i] != null){
        ArrayList<Edge> curEdges = edges[i];
        for (int j = 0; j < curEdges.size(); j++) {
          Edge curEdge = curEdges.get(j);
       // io.println("flow " + curEdge.getFlow());
          if (curEdge.getFlow() > 0) {
            int[] edge = {curEdge.getStart(), curEdge.getEnd(), curEdge.getFlow()};
            matrix.add(edge);
          }
        }
      }
    }
    //io.println("matrix.size "+matrix.size());
    return matrix;
  }

  public static void main(String args[]) {
    new maxFlowBra();
  }
  private void edmondsKarp(){

    while(bfs()){
      ArrayList<Edge> localPath = new ArrayList<Edge>();
      Edge edge = new Edge(t, 0, 0, 0);
      int minCap = Integer.MAX_VALUE;
      while(edge.getStart()!=s){
        edge = path[edge.getStart()];
        localPath.add(edge);
        minCap = Math.min(minCap, edge.getCap());
      }
      totFlow += minCap;
      for(int i = 0; i < localPath.size(); i++){
        edge = localPath.get(i);
        edge.setFlow(minCap);
        edge.setCap(-minCap);
        edge.getReverse().setFlow(-minCap);
        edge.getReverse().setCap(minCap);
      }
    }

  }
  private boolean bfs(){
    if(edges[s]==null || s==t || edges[s].size() == 0){
      return false;
    }
    visited = new boolean[v+1];
    fifo = new ArrayDeque<Edge>();
    visited[s] = true;
    addToQueue(s);
    while(!fifo.isEmpty()){
      curEdge = fifo.poll();
      //io.println(curEdge.getStart());
      if(addToQueue(curEdge.getEnd()))
        return true;
    }
    return false;
  }
  private boolean addToQueue(int murEdge){

    if(edges[murEdge]!=null){
      ArrayList<Edge> neighbours = edges[murEdge];
      for(int i = 0; i < neighbours.size(); i++){
        
        Edge thisEdge = neighbours.get(i);
        //io.println("forloopin" + i + " " + thisEdge.getStart());
        if(thisEdge.getCap()>0 && !visited[thisEdge.getEnd()]){
          fifo.add(thisEdge); 
          //io.println(thisEdge.getCap());
          path[thisEdge.getEnd()] = thisEdge;
          if(thisEdge.getEnd() == t)
            return true;
        }
      }
      visited[murEdge] = true;
    }
    return false;
  }
}
