package org.coursera.dopt.knapsack.bb.bound;

import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public interface EvaluationStrategyImpl {
	
	public void computeEvaluation(KnapsackProblemNode child);
}
