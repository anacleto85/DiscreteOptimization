package org.coursera.dopt.knapsack;

import org.coursera.dopt.knapsack.KnapsackProblem.KnapsackItem;

public class KnapsackSolution 
{
	protected KnapsackProblem problem;
	private int value;
	private boolean[] variables;
	
	/**
	 * 
	 * @param problem
	 */
	public KnapsackSolution(KnapsackProblem problem, int value) {
		this.problem = problem;
		this.value = value;
		this.variables = new boolean[problem.getItems().length];
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setItemInSolution(KnapsackItem item) {
		this.variables[item.getId() - 1] = true;
	}
	
	public String toString() {
		String solution = "";
		for (boolean in : this.variables) {
			if (in) {
				solution += "1 ";
			}
			else {
				solution += "0 ";
			}
		}
		return solution;
	}
}
