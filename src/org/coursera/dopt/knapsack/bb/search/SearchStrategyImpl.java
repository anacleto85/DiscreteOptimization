package org.coursera.dopt.knapsack.bb.search;

import java.util.List;

import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public interface SearchStrategyImpl {
	
	public void addNode(KnapsackProblemNode node);
	
	public void addNode(List<KnapsackProblemNode> nodes);
	
	public KnapsackProblemNode getNext();
	
	public boolean isEmpty();
}
