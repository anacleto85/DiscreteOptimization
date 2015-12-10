package org.coursera.dopt.cp.mapcoloring;

import org.coursera.dopt.cp.DomainManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MapColoringDomainManager extends DomainManager<CountryDecisionVariable> 
{
	private CountryDecisionVariableDomain vdom;
	
	/**
	 * 
	 * @param colors
	 */
	public MapColoringDomainManager(int colors) {
		super();
		this.vdom = new CountryDecisionVariableDomain(colors);
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 */
	public void addDomainConstraint(CountryDecisionVariable source, CountryDecisionVariable target) {
		this.domConstraints.add(new NotEqualsConstraint(source, target));
		this.domConstraints.add(new NotEqualsConstraint(target, source));
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public CountryDecisionVariable createDecisionVariable(String name) {
		CountryDecisionVariable var = new CountryDecisionVariable(this.vdom, name); 
		this.vars.add(var);
		return var;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String desc = "[MAPCOLORING_DOMAIN #vars= " + this.vars.size() + "]\n";
		desc += this.vdom + "\n";
		for (CountryDecisionVariable var : this.vars) {
			desc += var + "\n";
		}
		return desc;
	}
}
