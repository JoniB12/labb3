public class Edge {

	private int start; // Start noden
	private int end; // Slut noden
	private int cap; 
	private int flow;

	// Konstruktorer för Edge objekt.
	public Edge(int start, int end, int cap, int flow) {
		this.start = start;
		this.end = end;
		this.cap = cap;
		this.flow = flow;
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

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}
}