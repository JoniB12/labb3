/**
 * Exempel på in- och utdatahantering för maxflödeslabben i kursen
 * ADK.
 *
 * Använder Kattio.java för in- och utläsning.
 * Se http://kattis.csc.kth.se/doc/javaio
 *
 * @author: Per Austrin
 */

public class BipRed {
    Kattio io;
    int x;
    int y;
    int e;
	int[][] allEdges;
	int totflow;

    void readBipartiteGraph() {
	// Läs antal hörn och kanter
	x = io.getInt();
	y = io.getInt();
	e = io.getInt();
	
	allEdges = new int[2][e];

	// Läs in kanterna
	for (int i = 0; i < e; ++i) {
	    allEdges[0][i] = io.getInt();
	    allEdges[1][i] = io.getInt();
	}
    }
    
    
    void writeFlowGraph() {
	int v = x + y; 
	int s = v + 1; 
	int t = v + 2;
	int c = 1;
	// Skriv ut antal hörn och kanter samt källa och sänka
	io.println(v+2);
	io.println(s + " " + t);
	io.println(e+x+y);
	for(int i = 1; i <= x; i++){
		io.println(s + " " + i + " " + c);
	}
	for (int i = 0; i < e; ++i) {
	    io.println(allEdges[0][i] + " " + allEdges[1][i] + " " + c);
	}
	for(int i = x+1; i <= v; i++){
		io.println(i + " " + t + " "+ c);
	}

	// Var noggrann med att flusha utdata när flödesgrafen skrivits ut!
	io.flush();
	
	// Debugutskrift
	//System.err.println("Skickade iväg flödesgrafen");
    }
    
    
    void readMaxFlowSolution() {
	// Läs in antal hörn, kanter, källa, sänka, och totalt flöde
	// (Antal hörn, källa och sänka borde vara samma som vi i grafen vi
	// skickade iväg)
	int v = io.getInt();
	int s = io.getInt();
	int t = io.getInt();
	totflow = io.getInt();
	e = io.getInt();
	x=2;
	y=3;


	allEdges = new int[3][e];
	
	//io.println(x+" "+y);
	//io.println(totflow);

	for (int i = 0; i < e; ++i) {
	    // Flöde f från a till b
	    allEdges[0][i] = io.getInt();
	    allEdges[1][i] = io.getInt();
	    allEdges[2][i] = io.getInt();
	   
	    
	}


    }
    
    
    void writeBipMatchSolution() {
	
	// Skriv ut antal hörn och storleken på matchningen
	io.println(x + " " + y);
	io.println(totflow);
	for(int i = 0; i <e; i++){
		if(allEdges[0][i] <= (x+y) && allEdges[1][i] <= (x+y)){
			io.println(allEdges[0][i]+" "+allEdges[1][i]);
		}
	}
	io.flush();
	
    }
    
    BipRed() {
	io = new Kattio(System.in, System.out);
	
	//readBipartiteGraph();
	
	//writeFlowGraph();
	
	readMaxFlowSolution();
	
	writeBipMatchSolution();

	// debugutskrift
	//System.err.println("Bipred avslutar\n");

	// Kom ihåg att stänga ner Kattio-klassen
	io.close();
    }
    
    public static void main(String args[]) {
	new BipRed();
    }
}

