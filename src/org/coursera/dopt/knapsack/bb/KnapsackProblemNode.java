package org.coursera.dopt.knapsack.bb;

import java.util.LinkedList;
import java.util.List;

import org.coursera.dopt.knapsack.KnapsackProblem;
import org.coursera.dopt.knapsack.KnapsackProblem.KnapsackItem;
import org.coursera.dopt.knapsack.KnapsackSolution;

public class KnapsackProblemNode implements Comparable<KnapsackProblemNode>
{
	protected KnapsackProblem problem;
	protected boolean[] bounded;
	protected int[] vars;
	protected int currentItemId;
	protected int knapsackValue;
	protected int knapsackCapacityLeft;
	protected double optimisticEvaluation;

	/**
	 * 
	 * @param problem
	 */
	public KnapsackProblemNode(KnapsackProblem problem) {
		this.problem = problem;
		this.knapsackCapacityLeft = problem.getKnapsackCapacity();
		this.currentItemId = 0;
		
		// item IDs start from 1
		this.vars = new int[this.problem.getItems().length + 1];
		this.bounded = new boolean[this.problem.getItems().length + 1];
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected KnapsackProblemNode(KnapsackProblemNode parent) {
		this.problem = parent.problem;
		this.currentItemId = parent.currentItemId + 1;
		this.knapsackValue = parent.knapsackValue;
		this.knapsackCapacityLeft = parent.knapsackCapacityLeft;
		this.vars = new int[parent.vars.length];
		this.bounded = new boolean[parent.bounded.length];
		for (int i=0; i < this.vars.length; i++) {
			this.vars[i] = parent.vars[i];
			this.bounded[i] = parent.bounded[i];
		}
	}
	
	public void setOptimisticEvaluation(double optimisticEvaluation) {
		this.optimisticEvaluation = optimisticEvaluation;
	}
	
	public double getOptimisticEvaluation() {
		return optimisticEvaluation;
	}
	
	public boolean isConsistent() {
		return (this.knapsackCapacityLeft >= 0);
	}
	
	public int getKnapsackValue() {
		return this.knapsackValue;
	}
	
	public int getKnapsackCapacityLeft() {
		return this.knapsackCapacityLeft;
	}
	
	public boolean isSolution() {
		boolean sol = true;
		try {
			for (KnapsackItem item : this.problem.getItems()) {
				if (!this.bounded[item.getId()]) {
					throw new NoSolutionNode("No solution node");
				}
			}
		}
		catch (NoSolutionNode ex) {
			// no solution
			sol = false;
		}
		return sol;
	}
	
	public KnapsackSolution getSolution() {
		KnapsackSolution sol = new KnapsackSolution(this.problem, this.knapsackValue);
		for (KnapsackItem item : this.problem.getItems()) {
			if (this.vars[item.getId()] == 1) {
				sol.setItemInSolution(item);
			}
		}
		return sol;
	}
	
	public List<KnapsackProblemNode> branch() {
		List<KnapsackProblemNode> childs = new LinkedList<KnapsackProblemNode>();
		if (!this.isSolution()) {
			KnapsackProblemNode child1 = new KnapsackProblemNode(this);
			child1.vars[child1.currentItemId] = 0;
			child1.bounded[child1.currentItemId] = true;
			childs.add(child1);
			
			KnapsackProblemNode child2 = new KnapsackProblemNode(this);
			child2.vars[child2.currentItemId] = 1;
			child2.bounded[child2.currentItemId] = true;
			// get item 
			KnapsackItem item = this.problem.getItem(child2.currentItemId);
			child2.knapsackValue += item.getValue();
			child2.knapsackCapacityLeft -= item.getWeight();
			childs.add(child2);
		}
		return childs;
	}
	
	@Override
	public int compareTo(KnapsackProblemNode o) {
		return this.getKnapsackValue() - o.getKnapsackValue();
	}

	public int getValueOfItemsInKnapsack() {
		int val = 0;
		for (KnapsackItem item : this.problem.getItems()) {
			val += (this.vars[item.getId()] * item.getValue());
		}
		return val;
	}

	public int getValueOfItemsNotBounded() {
		int val=0;
		for (KnapsackItem item : this.problem.getItems()) {
			if (!this.bounded[item.getId()]) {
				val += item.getValue();
			}
		}
		return val;
	}

	public boolean isItemInTheKnapsack(KnapsackItem item) {
		return (this.vars[item.getId()] == 1);
	}

	public boolean isItemBounded(KnapsackItem item) {
		return this.bounded[item.getId()];
	}
	
	class NoSolutionNode extends Exception {
		private static final long serialVersionUID = 1L;
		public NoSolutionNode(String msg) {
			super(msg);
		}
	}
}
