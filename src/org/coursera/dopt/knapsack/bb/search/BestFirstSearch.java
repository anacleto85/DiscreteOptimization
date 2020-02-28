package org.coursera.dopt.knapsack.bb.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public class BestFirstSearch implements SearchStrategyImpl, Comparator<KnapsackProblemNode> 
{
	private List<KnapsackProblemNode> queue;
	
	public BestFirstSearch() {
		this.queue = new ArrayList<KnapsackProblemNode>();
	}
	
	@Override
	public void addNode(KnapsackProblemNode node) {
		this.queue.add(node);
	}
	
	@Override
	public void addNode(List<KnapsackProblemNode> nodes) {
		this.queue.addAll(nodes);
	}
	
	@Override
	public KnapsackProblemNode getNext() {
		Collections.sort(this.queue, this);
		return this.queue.remove(0);
	}
	
	@Override
	public boolean isEmpty() {
		return this.queue.isEmpty();
	}
	
	@Override
	public int compare(KnapsackProblemNode o1, KnapsackProblemNode o2) {
		return (o1.getOptimisticEvaluation() <= o2.getOptimisticEvaluation()) ? -1 : +1;
	}
}
