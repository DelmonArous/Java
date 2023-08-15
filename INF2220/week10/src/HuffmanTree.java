import java.io.*;
import java.util.*;

class HuffmanTree {
	private int n;
	private HashMap<Character, Integer> symbolfrequencyset = new HashMap<Character, Integer>();;
	private String[] code;
	private BinaryMinHeap<Node> priorityqueue;
	//private PriorityQueue<Node> priorityqueue;
	
	private class Node implements Comparable<Node> {
		char ch;
		int freq;
		Node left, right;
		
		Node (char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		
		public boolean isLeaf() {
			return (left == null && right == null);
		}
		
		public int compareTo(Node n) {
			return this.freq - n.freq;
		}
	}
	
	public HuffmanTree(String filename) {
		Scanner scan = null;
		String text = "";
		
		try {
			scan = new Scanner(new File(filename));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		while (scan.hasNext()) {
			text += scan.nextLine();
			text += " ";
		}
		
		scan.close();
		
		char[] chars = text.toCharArray();
		for (Character ch: chars) {
			if (symbolfrequencyset.containsKey(ch))
				symbolfrequencyset.put(ch, symbolfrequencyset.get(ch)+1);
			else
				symbolfrequencyset.put(ch, 1);
		}
		
		n = symbolfrequencyset.size();
		code = new String[n];
	}
	

	public void constructHuffmanTree() {
		priorityqueue = new BinaryMinHeap<Node>(n);
		
		Set<Character> keys = symbolfrequencyset.keySet();
		for(char ch: keys)
			priorityqueue.insert(symbolfrequencyset.get(ch), new Node(ch, symbolfrequencyset.get(ch), null, null));
	}
	
}