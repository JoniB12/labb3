public class Edge {

	private int start; // Start noden
	private int end; // Slut noden
	private int cap; 

	// Konstruktorer för Edge objekt.
	public Edge(int start, int end, int cap) {
		this.start = start;
		this.end = end;
		this.cap = cap;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(int cap) {
		this.cap = cap;
	}
}