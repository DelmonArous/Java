public class Graph {
	private Node [] graph;
	
	private class Node {
		Node succ;
		int mark = -1;
		int id;
	}
	
	public Graph(int n) {
		graph = new Node[n];
		setId();
	}
	
	public void resetMark() {
		for (int i = 0; i < graph.length; i++)
			graph[i].mark = -1;
	}
	
	public void setId() {
		for (int i = 0; i < graph.length; i++)
			graph[i].id = i;
	}	
	
	public boolean isSimpleLinear() {
		resetMark();
		
		for (int i = 0; i < graph.length; i++) {
			if (isSimpleLinear(graph[i]) == false)
				return false;
		}
		
		return true;
	}
	
	public boolean isSimpleLinear(Node n) {
		Node iter = n.succ;
		
		if (n.mark == 0) // 0 = in progress
			return false;
		else if (n.mark == -1) { // -1 = unvisited
			n.mark = 1;
			while (iter != null) {
				isSimpleLinear(iter);
				iter = iter.succ;
			}
			n.mark = 1; // 1 = finished
		}
//		n.mark = 0;
		return true;
	}
	
	public boolean isSimpleCycle() {
		resetMark();
		Node first = graph[0];
		int iter = 0;
		
		while (first != null){
			first.mark++;
			iter++;
			
			if(first.mark == 1 && iter < graph.length)
				return false;
			
			first = first.succ;
		}
				
		return true;
	}
	
	public boolean isSingleTree() {
		resetMark();
		Node n = null, start;
		int counter = 0;
		int teller = 0;
				
		for (int i = 0; i < graph.length; i++) {
			n = graph[i];
			start = n;
			while (n.succ != null && n.succ.mark == -1 && teller++ < graph.length)
				n = n.succ;
			
			if(teller > graph.length)
				return false;
			
			if(n.succ==null){
				n.mark = n.id;
				teller = 0;
			}
			else{
				n.mark = n.succ.mark;
				teller = 0;
			}
			
			while(start.mark == -1){
				start.mark = n.mark;
			 	start = start.succ;
			}
			
		}
		
		for (int i = 0; i < graph.length; i++) 
			if (graph[i].mark == n.mark)
				counter++;
		
		if (counter == graph.length)
			return true;
		
		return false;
	}
}
