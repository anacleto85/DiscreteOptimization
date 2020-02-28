package org.coursera.dopt.cp.mapcoloring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.coursera.dopt.cp.DecisionVariable;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CountryDecisionVariable extends DecisionVariable<CountryDecisionVariableDomain>
{
	private boolean[] values;
	
	/**
	 * 
	 * @param dom
	 * @param name
	 */
	public CountryDecisionVariable(CountryDecisionVariableDomain dom, String name) {
		super(dom, name);
		this.values = new boolean[dom.getColors()];
		// initially the variable can assume all colors
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = true;
		}
	}
	
	/**
	 * 
	 */
	public List<Integer> getValues() {
		List<Integer> vals = new LinkedList<Integer>();
		for (int i=0; i < this.values.length; i++) {
			if (this.values[i]) {
				vals.add(i);
			}
		}
		return vals;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CountryDecisionConstraint> getPossibleChoices() {
		List<CountryDecisionConstraint> decs = new ArrayList<CountryDecisionConstraint>();
		for (Integer value : this.getValues()) {
			decs.add(new CountryDecisionConstraint(this, value));
		}
		return decs;
	}
	
	/**
	 * 
	 * @param val
	 */
	public void valueChoice(int val) {
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = (i == val);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isBound() {
		return this.getValues().size() == 1;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isInDomain(int value) {
		return this.values[value];
	}
	
	/**
	 * 
	 * @param commValue
	 */
	public void exclude(Collection<Integer> exludes) {
		for (Integer val : exludes) {
			this.values[val] = false;
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String vals = "{";
		for (int i=0; i < this.values.length; i++) {
			if (this.values[i]) {
				vals += " " + i + " ";
			}
		}
		vals += "}";
		return "[COUNTRY_DECISION_VARIABLE name=" + this.name + ", values=" + vals + "]";
	}
}
