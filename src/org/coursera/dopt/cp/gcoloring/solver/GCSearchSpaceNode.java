package org.coursera.dopt.cp.gcoloring.solver;

import java.util.LinkedList;
import java.util.List;

import org.coursera.dopt.cp.gcoloring.GraphNodeColorDecisionConstraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GCSearchSpaceNode 
{
	private static int COUNTER = 0;
	private int id;
	private List<GraphNodeColorDecisionConstraint> constraints;
	private GraphNodeColorDecisionConstraint toApply;
	
	/**
	 * 
	 */
	public GCSearchSpaceNode() {
		this.id = getNext();
		this.constraints = new LinkedList<GraphNodeColorDecisionConstraint>();
	}
	
	/**
	 * 
	 * @param parent
	 * @param constraint
	 */
	public GCSearchSpaceNode(GCSearchSpaceNode parent, GraphNodeColorDecisionConstraint toApply) {
		this.id = getNext();
		this.constraints = new LinkedList<>(parent.getConstraints());
		this.toApply = toApply;
	}
	
	/**
	 * 
	 */
	public void markAsExpanded() {
		this.constraints.add(this.toApply);
		this.toApply = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public GraphNodeColorDecisionConstraint getToApply() {
		return toApply;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<GraphNodeColorDecisionConstraint> getConstraints() {
		return constraints;
	}
	
	/**
	 * 
	 * @return
	 */
	private synchronized static int getNext() {
		return COUNTER++;
	}
}
