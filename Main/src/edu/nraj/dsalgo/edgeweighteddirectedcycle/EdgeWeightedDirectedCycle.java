package edu.nraj.dsalgo.edgeweighteddirectedcycle;

import edu.nraj.dsalgo.directededge.DirectedEdge;
import edu.nraj.dsalgo.edgeweighteddigraph.EdgeWeightedDigraph;
import edu.nraj.dsalgo.stack.Stack;

public class EdgeWeightedDirectedCycle {
	private boolean[] marked;
	private DirectedEdge[] edgeTo;
	private boolean[] onStack;
	private Stack<DirectedEdge> cycle;
	
	public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
		marked = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		for(int v = 0 ; v < G.V(); v++)
			if(!marked[v])	mutedDFS(G, v);
	}

	private void mutedDFS(EdgeWeightedDigraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		
		for(DirectedEdge e : G.adj(v)) {
			int w = e.to();
			
			// short circuit if the cycle found
			if(cycle != null)	return;
			else if(!marked[w]) {
				edgeTo[w] = e;
				mutedDFS(G, w);
			}
			else if(onStack[w]) {
				cycle = new Stack<>();
				
				DirectedEdge f = e;
				while(f.from() != w) {
					cycle.push(f);
					f = edgeTo[f.from()];
				}
				cycle.push(f);
				return;
			}
		}
		onStack[v] = false;
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
	
}
