package org.coursera.dopt.knapsack.dp;

import org.coursera.dopt.knapsack.KnapsackProblem;
import org.coursera.dopt.knapsack.KnapsackProblem.KnapsackItem;
import org.coursera.dopt.knapsack.KnapsackSolution;
import org.coursera.dopt.knapsack.KnapsackSolverImpl;


public class DynamicProgrammingKnapsackSolverImpl implements KnapsackSolverImpl 
{
	private KnapsackProblem problem;
	private int[][] matrix;
	
	/**
	 * 
	 * @param problem
	 */
	public DynamicProgrammingKnapsackSolverImpl(KnapsackProblem problem) {
		this.problem = problem;
		this.matrix = new int[problem.getKnapsackCapacity() + 1][problem.getItems().length + 1];
        
		for (KnapsackItem item : problem.getItems()) {
        	for (int j=1; j <= problem.getKnapsackCapacity(); j++) {
        		if (item.getWeight() <= j) {
        			// get left value
        			int a = this.matrix[j][item.getId()-1];
        			int b = item.getValue();
        			if ((j - item.getWeight()) >= 0) {
        				b += this.matrix[j - item.getWeight()][item.getId()-1];
        			}
        			
        			// set value
        			this.matrix[j][item.getId()] = Math.max(a, b);
        		}
        		else {
        			this.matrix[j][item.getId()] = this.matrix[j][item.getId()-1];
        		}
        	}
        }
	}
	
	/**
	 * 
	 */
	@Override
	public KnapsackSolution solve() 
	{
		// get items and capacity
		int capacity = this.problem.getKnapsackCapacity();
		int solValue = this.matrix[capacity][this.problem.getItems().length];
		// get problem solution
		KnapsackSolution solution = new KnapsackSolution(this.problem, solValue);
		for (int index = this.problem.getItems().length; index > 0; index--) {
			// test
			if (this.matrix[capacity][index] != this.matrix[capacity][index-1]) {
				KnapsackItem item = this.problem.getItems()[index-1];
				// set solution
				solution.setItemInSolution(item);
				capacity -= item.getWeight();
			}
		}

		return solution;
	}
}
