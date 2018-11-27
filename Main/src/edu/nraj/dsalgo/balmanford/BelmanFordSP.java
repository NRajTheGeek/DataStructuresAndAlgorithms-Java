package edu.nraj.dsalgo.balmanford;

import edu.nraj.dsalgo.directededge.DirectedEdge;
import edu.nraj.dsalgo.edgeweighteddigraph.EdgeWeightedDigraph;
import edu.nraj.dsalgo.edgeweighteddirectedcycle.EdgeWeightedDirectedCycle;
import edu.nraj.dsalgo.queue.Queue;

public class BelmanFordSP {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private boolean[] onQueue;
	private Queue<Integer> queue;
	private int cost;
	private Iterable<DirectedEdge> cycle;
	
	public BelmanFordSP(EdgeWeightedDigraph G, int s) {
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		onQueue = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		
		distTo[s] = 0.0;
		
		queue = new Queue<>();
		queue.enqueue(s);
		onQueue[s] = true;
		
		while(!queue.isEmpty() && !hasNegativeCycle()) {
			int v = queue.dequeue();
			onQueue[v] = false;
			mutedRelax(G, v);
		}
	}
	
	public void mutedRelax(EdgeWeightedDigraph G, int v) {
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			if(distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if(!onQueue[w]) {
					queue.enqueue(w);
					onQueue[w] = true;
				}
			}
			if(cost++ % G.V() == 0) {
				findNegativeCycle();
				if(hasNegativeCycle())	return;
			}
		}
	}
	
	private void findNegativeCycle() {
		int V = edgeTo.length;
		EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
		for(int v = 0; v <  spt.V(); v++)
			if(edgeTo[v] != null)
				spt.addEdge(edgeTo[v]);
		
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
		
		cycle = finder.cycle();
	}

	public boolean hasNegativeCycle() {
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> negaativeCycle(){
		return cycle;
	}
	
	
	public static void main(String[] args) {
		
	}
}
