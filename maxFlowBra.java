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
      xEdge.reverse=yEdge;
      yEdge.reverse=xEdge;
      edges[x].add(xEdge);
      edges[y].add(yEdge);
    }
  }

  private void writeGraph() {
    io.println(v);
    io.println(s + " " + t + " " + totFlow);
    ArrayList<Edge> list = new ArrayList<Edge>();
    for (int i = 1; i <= v; i++) {
      if(edges[i] != null){
        //ArrayList<Edge> curEdges = edges[i];
        for (int j = 0; j < edges[i].size(); j++) {
          Edge curEdge = edges[i].get(j);
          if (edges[i].get(j).flow > 0 ) {
            list.add(edges[i].get(j));
            // int[] edge = {curEdge.start, curEdge.end, curEdge.flow};
            // matrix.add(edge);
          }
        }
      }
    }
    io.println(list.size());
    for (int i = 0; i < list.size(); i++) {
      Edge thisfreakinedge = list.get(i);
      io.println(thisfreakinedge.start + " " + thisfreakinedge.end + " " + thisfreakinedge.flow);
    }
    io.flush();
  }
  

  public static void main(String args[]) {
    new maxFlowBra();
  }
  private void edmondsKarp(){
    Edge e;
    while(bfs()){
      ArrayList<Edge> localPath = new ArrayList<Edge>();
      //Edge edge = new Edge(t, 0, 0, 0);
      Edge edge = path[t];
      int minCap = Integer.MAX_VALUE;

      while(edge.start!=s){
        minCap = Math.min(minCap, edge.cap);
        localPath.add(edge);
        edge = path[edge.start];
      }
      minCap = Math.min(minCap, edge.cap);
      localPath.add(edge);
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
    Edge sourceEdge = new Edge(0,s,0,0);

    visited = new boolean[v+1];
    fifo = new ArrayDeque<Edge>();

    fifo.add(sourceEdge);

    // visited[s] = true;
    // addToQueue(s);
    while(!fifo.isEmpty()){
      curEdge = fifo.poll();
      //io.println(curEdge.start);
      if(addToQueue(curEdge.end))
        return true;
    }
    return false;
  }
  private boolean addToQueue(int murEdge){

    if(edges[murEdge]!=null){
      ArrayList<Edge> neighbours = edges[murEdge];

      Edge thisEdge;
      for(int i = 0; i < neighbours.size(); i++){

        thisEdge = neighbours.get(i);
        //io.println("forloopin" + i + " " + thisEdge.start);
        if(thisEdge.cap>0 && !visited[thisEdge.end]){
          // fifo.add(thisEdge); 
          // //io.println(thisEdge.cap);
          // path[thisEdge.end] = thisEdge;
          if(thisEdge.end == t){
            visited[thisEdge.end] = true;
            path[t] = thisEdge;
            return true;
          }
          visited[thisEdge.end] = true;
          path[thisEdge.end] = thisEdge;
          fifo.add(thisEdge);
        }
      }
      
    }
    return false;
  }
}
