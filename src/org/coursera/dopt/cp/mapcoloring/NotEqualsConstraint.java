package org.coursera.dopt.cp.mapcoloring;

import java.util.HashSet;
import java.util.Set;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class NotEqualsConstraint extends Constraint
{	
	private CountryDecisionVariable source;
	private CountryDecisionVariable target; 
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	public NotEqualsConstraint(CountryDecisionVariable source, CountryDecisionVariable target) {
		super();
		this.source = source;
		this.target = target;
	}
	
	/**
	 * 
	 * @return
	 */
	public CountryDecisionVariable getSource() {
		return source;
	}
	
	/**
	 * 
	 * @return
	 */
	public CountryDecisionVariable getTarget() {
		return target;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isFeasible() {
		Set<Integer> union = new HashSet<Integer>();
		union.addAll(this.source.getValues());
		union.addAll(this.target.getValues());
		// check union size
		return (union.size() > 1);
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		// get intersection
		Set<Integer> intersection = new HashSet<Integer>();
		for (Integer value : this.source.getValues()) {
			if (this.target.isInDomain(value)) {
				intersection.add(value);
			}
		}
		
		// check intersection
		if (!intersection.isEmpty()) {
			// check source variable
			if (this.source.isBound()) {
				this.target.exclude(intersection);
			}	// check target variable
			else if (this.target.isBound()) {
				this.source.exclude(intersection);
			}
			else {
				// not possible to prune vlaues
			}
		}
	}
}
