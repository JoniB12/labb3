/**
*
*
*@autor: Sara F och Katja R
*/

public class maxFlox {
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	int e = io.getInt();
	
	int vertex = io.getInt();
	int neighbor;
	int capacity;

	List<List<Integer>> neighborList = new List<List<Integer>>(); 
	int[][] capacityMatrix = new int[][] capacityMatrix();
	
	// Bygger grannlista och capacitetsmatris
	for(int i = vertex; i < v; i++ ){
		int vertexTmp = vertex;
		while(vertexTmp == vertex){
			neighbor = io.getInt()
			neighborList(i).add(neighbor);
			capacity = io.getInt();
			capacityMatrix[i][neighbor] = capacity;
			vertex = io.getInt();
		}	
	}

	void edmondsKarp(){

	}
}
