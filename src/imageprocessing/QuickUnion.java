package imageprocessing;

import java.util.ArrayList;

public class QuickUnion {
	
	private ArrayList<Integer> labels;
	private int labelNo;
	private int noOfLabels;
	
	public QuickUnion() {
		labels = new ArrayList<Integer>();
		labelNo = 0;
		labels.add(labelNo++);
		noOfLabels = 0;
		
	}
	
	public void addLabel(){
		labels.add(labelNo++);
		noOfLabels++;
	}
	
	
	public int root(int i) {
		while (i != labels.get(i)) i = labels.get(i);
		return i;
	}
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	public void union(int p, int q) {
		if(!connected(p,q)){
			int i = root(p), j = root(q);			
			labels.set(i, j);
			noOfLabels--;
		}
		
	}
	
	public int labelCount(){
		return noOfLabels;
	}
}