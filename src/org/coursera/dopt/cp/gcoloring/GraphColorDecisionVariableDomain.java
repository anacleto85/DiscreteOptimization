package org.coursera.dopt.cp.gcoloring;

import org.coursera.dopt.cp.DecisionVariableDomain;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphColorDecisionVariableDomain extends DecisionVariableDomain 
{
	private int colors;
	
	/**
	 * 
	 * @param colors
	 */
	public GraphColorDecisionVariableDomain(int colors) {
		super(0, colors-1);
		this.colors = colors;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColors() {
		return colors;
	}
}
