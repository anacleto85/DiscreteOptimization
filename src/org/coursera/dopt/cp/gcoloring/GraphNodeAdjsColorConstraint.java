package org.coursera.dopt.cp.gcoloring;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphNodeAdjsColorConstraint extends Constraint
{
	private GraphNode node;
	
	/**
	 * 
	 * @param node
	 */
	public GraphNodeAdjsColorConstraint(GraphNode node) {
		this.node = node;
	}
	
	/**
	 * 
	 * @return
	 */
	public GraphNode getNode() {
		return node;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isFeasible() {
		Set<Integer> colors = new HashSet<Integer>();
		// add node colors
		colors.addAll(this.node.getNodeColors());
		// get adjacent nodes
		for (GraphNode adj : this.node.getAdjsNodes()) {
			colors.addAll(adj.getNodeColors());
		}
		
		// check feasibility
		return (colors.size() > 1);
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		// get node colors
		List<Integer> colors = this.node.getNodeColors();
		if (colors.size() == 1) {
			// prune 
			for (GraphNode adj : this.node.getAdjsNodes()) {
				adj.excludeColors(colors);
			}
		}
	}
}
