package org.coursera.dopt.cp.mapcoloring;

import org.coursera.dopt.cp.DecisionVariableDomain;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CountryDecisionVariableDomain extends DecisionVariableDomain 
{
	private int colors;				// number of colors to use for coloring the map
	
	/**
	 * 
	 * @param colors
	 */
	public CountryDecisionVariableDomain(int colors) {
		super(0, colors - 1);
		this.colors = colors;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColors() {
		return colors;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[COUNTRY_DOMAIN #colors= " + this.colors + "]";
	}
}
