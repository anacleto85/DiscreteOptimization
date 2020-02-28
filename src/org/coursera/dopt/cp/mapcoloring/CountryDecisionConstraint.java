package org.coursera.dopt.cp.mapcoloring;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CountryDecisionConstraint extends Constraint 
{
	private CountryDecisionVariable source;
	private int value;

	/**
	 * 
	 * @param source
	 * @param value
	 */
	public CountryDecisionConstraint(CountryDecisionVariable source, int value) {
		super();
		this.source = source;
		this.value = value;
	}
	
	@Override
	public boolean isFeasible() {
		return this.source.isInDomain(this.value);
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		this.source.valueChoice(this.value);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[DECISION_CONSTRAINT var= " + this.source +", value= " + this.value + "]";
	}
}
