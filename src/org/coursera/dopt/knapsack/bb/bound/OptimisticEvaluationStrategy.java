package org.coursera.dopt.knapsack.bb.bound;

import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public class OptimisticEvaluationStrategy implements EvaluationStrategyImpl 
{	
	@Override
	public void computeEvaluation(KnapsackProblemNode child) {
		child.setOptimisticEvaluation(this.computeOptimisticEvaluation(child));
	}
	
	private int computeOptimisticEvaluation(KnapsackProblemNode node) {
		return node.getValueOfItemsInKnapsack() + node.getValueOfItemsNotBounded();
	}
}
