package org.coursera.dopt.knapsack.bb.search;

import java.util.List;
import java.util.Stack;

import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public class DFS implements SearchStrategyImpl
{
	private Stack<KnapsackProblemNode> stack;
	
	public DFS() {
		this.stack = new Stack<KnapsackProblemNode>();
	}
	
	@Override
	public void addNode(KnapsackProblemNode item) {
		this.stack.push(item);
	}
	
	public void addNode(List<KnapsackProblemNode> items) {
		for (KnapsackProblemNode item : items) {
			this.stack.push(item);
		}
	}
	
	@Override
	public KnapsackProblemNode getNext() {
		return this.stack.pop();
	}
	
	@Override
	public boolean isEmpty() {
		return this.stack.isEmpty();
	}
}
