package org.coursera.dopt.knapsack.bb;

import java.util.List;

import org.coursera.dopt.knapsack.KnapsackProblem;
import org.coursera.dopt.knapsack.KnapsackSolution;
import org.coursera.dopt.knapsack.KnapsackSolverImpl;
import org.coursera.dopt.knapsack.bb.bound.EvaluationStrategyImpl;
import org.coursera.dopt.knapsack.bb.bound.LineareRegressionEvaluationStrategy;
import org.coursera.dopt.knapsack.bb.search.DFS;
import org.coursera.dopt.knapsack.bb.search.SearchStrategyImpl;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class BranchBoundKnapsackSolverImpl implements KnapsackSolverImpl 
{
	private KnapsackProblem problem;
	private KnapsackProblemNode solutionNode;
	private SearchStrategyImpl strategy;
	private EvaluationStrategyImpl boundStrategy;
	
	/**
	 * 
	 * @param problem
	 */
	public BranchBoundKnapsackSolverImpl(KnapsackProblem problem) {
		this.problem = problem;
		this.solutionNode = null;
		this.strategy = new DFS();
//		this.strategy = new BestFirstSearch();
//		this.boundStrategy = new OptimisticEvaluationBoundStrategy();
		this.boundStrategy = new LineareRegressionEvaluationStrategy(this.problem);
	}
	
	/**
	 * 
	 */
	@Override
	public KnapsackSolution solve() {
		KnapsackProblemNode root = new KnapsackProblemNode(this.problem);
		this.boundStrategy.computeEvaluation(root);
		this.strategy.addNode(root);
		
		// start search
		while (!this.strategy.isEmpty()) {
			// next node
			KnapsackProblemNode node = this.strategy.getNext();
			// check consistency
			if (node.isConsistent() && (this.solutionNode == null || !this.bound(node, this.solutionNode))) {
				// check value
				if (node.isSolution()) {
					if (this.solutionNode == null || node.getKnapsackValue() > this.solutionNode.getKnapsackValue()) {
						this.solutionNode = node;
					}
				}
				
				// branch step
				List<KnapsackProblemNode> childs = node.branch();
				for (KnapsackProblemNode child : childs) {
					this.boundStrategy.computeEvaluation(child);
				}
				this.strategy.addNode(childs);
			}
		}
		
		// get problem solution
		return this.solutionNode.getSolution();
	}
	
	private boolean bound(KnapsackProblemNode ni, KnapsackProblemNode nj) {
		return ni.getOptimisticEvaluation() <= nj.getOptimisticEvaluation();
	}
}
