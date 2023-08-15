public class Graph {
	private ListNode[] L;
	
	private class ListNode {
		int destination;
		ListNode next;
	}
	
	public Graph(ListNode[] L) {
		this.L = L;
	}

	public int[] calculateIndegrees() {
		int[] indegrees = new int[L.length];
		
		for (ListNode ln: L) {
			while (ln != null) {
				indegrees[ln.destination]++;
				ln = ln.next;
			}
		}
		
		return indegrees;
	}
	
}
