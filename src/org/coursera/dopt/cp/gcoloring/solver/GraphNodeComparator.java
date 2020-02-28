package org.coursera.dopt.cp.gcoloring.solver;

import java.util.Comparator;

import org.coursera.dopt.cp.gcoloring.GraphNode;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphNodeComparator implements Comparator<GraphNode> 
{
	/**
	 * 
	 */
	@Override
	public int compare(GraphNode o1, GraphNode o2) {
		int result = o1.getNodeColors().size() - o2.getNodeColors().size();
		if (result == 0) {
			// select the most constrained node
			result = (o1.getAdjsNodes().size() >= o2.getAdjsNodes().size()) ? -1 : +1;
		}
		return result;
	}
}
