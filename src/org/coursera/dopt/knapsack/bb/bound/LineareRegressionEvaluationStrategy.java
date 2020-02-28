package org.coursera.dopt.knapsack.bb.bound;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.coursera.dopt.knapsack.KnapsackProblem;
import org.coursera.dopt.knapsack.KnapsackProblem.KnapsackItem;
import org.coursera.dopt.knapsack.bb.KnapsackProblemNode;

public class LineareRegressionEvaluationStrategy implements EvaluationStrategyImpl, Comparator<KnapsackItem>
{
	private KnapsackProblem problem;
	private List<KnapsackItem> items;
	
	public LineareRegressionEvaluationStrategy(KnapsackProblem problem) {
		this.problem = problem;
		this.items = Arrays.asList(this.problem.getItems());

		Collections.sort(this.items, this);
	}
	
	@Override
	public void computeEvaluation(KnapsackProblemNode child) {
		child.setOptimisticEvaluation(this.evaluate(child));
	}
	
	private double evaluate(KnapsackProblemNode node) {
		double value = 0;
		int knapsackCapacity = this.problem.getKnapsackCapacity();
		for (KnapsackItem item : this.items) {
			if (knapsackCapacity > 0 && (node.isItemInTheKnapsack(item) || !node.isItemBounded(item))) {
				if (item.getWeight() <= knapsackCapacity) {
					value += item.getValue();
					knapsackCapacity -= item.getWeight();
				}
				else {
					double diff = knapsackCapacity;
					value += Math.round(item.getValue() * (diff / item.getWeight()));
					knapsackCapacity -= diff;
				}
			}
		}
		
		return value;
	}
	
	@Override
	public int compare(KnapsackItem o1, KnapsackItem o2) {
		double a = (o1.getValue() / o1.getWeight());
		double b = (o2.getValue() / o2.getWeight());
		return (a <= b) ? -1 : +1;
	}
}
