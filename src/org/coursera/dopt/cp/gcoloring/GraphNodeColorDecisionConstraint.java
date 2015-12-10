package org.coursera.dopt.cp.gcoloring;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphNodeColorDecisionConstraint extends Constraint 
{
	private GraphNode node;
	private int color;
	
	/**
	 * 
	 * @param node
	 * @param color
	 */
	public GraphNodeColorDecisionConstraint(GraphNode node, int color) {
		super();
		this.node = node;
		this.color = color;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isFeasible() {
		return this.node.isColorInDomain(this.color);
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		this.node.selectColor(this.color);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[GRAPH_NODE_COLOR_DECISION_CONSTRAINT node=" + this.node + ", color= " + this.color + "]";
	}
}
