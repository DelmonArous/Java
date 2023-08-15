import java.util.*;

public class DistanceGraph {
	private HashMap<String, Node> graph;
	
	private class Node {
		String id;
		LinkedList<Node> neighbours;
		boolean visited;
		int distance;
		
		Node(String _id) {
			id = _id;
			neighbours = new LinkedList<Node>();
			visited = false;
		}
		
		public boolean addEdge(Node to) {
			return neighbours.add(to);
		}
		
		public LinkedList<Node> getNeighbours() {
			return neighbours;
		}
		
		public String toString() {
			return String.format("Node id: %2s, distance %d ", id, distance);
		}
	}
	
	public DistanceGraph() {
		graph = new HashMap<String, Node>();
	}
	
	public void addVertex(String id) {
		graph.put(id, new Node(id));
	}
	
	public boolean addEdge(String from, String to) {
		Node fromNode = graph.get(from);
		Node toNode = graph.get(to);
		
		if (fromNode == null || toNode == null)
			return false;
		
		return fromNode.addEdge(toNode);
	}
	
	private void removeVisitedFlags() {
		for (Node n: graph.values())
			n.visited = false;
	}
	
	public LinkedList<Node> shortestPathFrom(String id) {
		
		// first we have to clear all flags..
		removeVisitedFlags();
		
		// make a que and a done list
		LinkedList<Node> que = new LinkedList<Node>();
		LinkedList<Node> done = new LinkedList<Node>();
		
		// ----------------- TODO --------------------------
		// Pseudocode on page 392
		for (Node n: graph.values())
			n.distance = (int) Double.POSITIVE_INFINITY;
		
		Node s = graph.get(id);
		s.distance = 0;
		que.add(s);
		Node n;
		
		while (!que.isEmpty()) {
			n = que.remove();
			
			for (Node w: n.getNeighbours()) {
				if (w.distance == (int) Double.POSITIVE_INFINITY) {
					w.distance = n.distance + 1;
					// need to add path here, but how?
					// w.path = v;
					que.add(w);
				}	
			}
			done.add(n);
		}
		
		return done;
	}
	
	// testing it with the graph from slide 27 last lecture
	public static void main(String[] args) {
		DistanceGraph dg = new DistanceGraph();

		for (int i = 1; i < 8; i++)
			dg.addVertex(String.format("V%d",i));
	
		dg.addEdge("V1", "V2");
		dg.addEdge("V1", "V4");
		dg.addEdge("V2", "V5");
		dg.addEdge("V2", "V4");
		dg.addEdge("V3", "V6");
		dg.addEdge("V3", "V1");
		dg.addEdge("V4", "V3");
		dg.addEdge("V4", "V6");
		dg.addEdge("V4", "V7");
		dg.addEdge("V4", "V5");
		dg.addEdge("V5", "V7");
		dg.addEdge("V7", "V6");
	
		for (Node n: dg.shortestPathFrom("V3"))
			System.out.println(n);
		
		System.out.println("------------------------");
		
		for (Node n: dg.shortestPathFrom("V4"))
			System.out.println(n);
	}
}
