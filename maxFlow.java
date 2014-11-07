/**
*
*
*@autor: Sara F och Katja R
*/
import java.util.*;

public class maxFlox {
	Kattio io;
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int e = io.getInt();
	
	int vertex = io.getInt();
	int neighbor;
	int capacity;
	int[][] flowMatrix = new int[v][v];
	int[] parentTable = new int[v];

	List<List<Integer>> neighborList = new ArrayList<List<Integer>>(); 
	int[][] capacityMatrix = new int[v][v];
	int[] m = new int[e];
	
	
	void buildNaighburAndCapacity(){
		// Bygger grannlista och capacitetsmatris
	for(int i = 0; i < v; i++ ){
		int vertexTmp = vertex;
		neighborList.add(new ArrayList<Integer>());
		while(vertexTmp == vertex){
			neighbor = io.getInt();
			neighborList.get(i).add(neighbor);
			capacity = io.getInt();
			capacityMatrix[i][neighbor] = capacity;
			vertex = io.getInt();
		}
	}
}
	int edmondsKarp(){
		int flow = 0;
		int m;
		int ttmp;
		 while(m!=0) {
		 	m = breadthFirstSearch();
		 	flow = flow + m;
		 	ttmp = t;
		 	while(ttmp != s) {
		 		int u = parentTable[ttmp];
		 		flowMatrix[u][v] = flowMatrix[u][v]+m;
		 		flowMatrix[v][u] = flowMatrix[v][u]-m;
		 		v = u;
		 	}
		 }
		 return flow;
	}

	int breadthFirstSearch(){
		for(int i = 0;i < v; i++){
			parentTable[i] = -1;
		}
		parentTable[s] = -2;
		m[s] = 0;
		Queue queue = new LinkedList<Integer>();
		queue.offer(s);
		while(queue.size() > 0) {
			int u = (int)queue.poll();
			List<Integer> a = neighborList.get(u);
			for(int i = 0; i < a.size();i++){
				if((capacityMatrix[u][i] - flowMatrix[u][i] > 0) && parentTable[i]==-1)
					parentTable[i] = u;
				m[v] = Math.min(m[i], (capacityMatrix[u][i] - flowMatrix[u][i]));
				if(v != t){
					queue.offer(i);
				}else{
					return m[t];
				}	
			}
		}
		return 0;
	}
}
