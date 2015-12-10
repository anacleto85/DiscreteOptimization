package org.coursera.dopt.cp;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class DecisionVariableDomain 
{
	protected int lb;	// domain lower bound
	protected int ub;	// domain upper bound
	
	/**
	 * 
	 * @param lb
	 * @param ub
	 */
	protected DecisionVariableDomain(int lb, int ub) {
		this.lb = lb;
		this.ub = ub;
	}
	
	/**
	 * 
	 * @return
	 */
	protected int getLb() {
		return lb;
	}
	
	/**
	 * 
	 * @return
	 */
	protected int getUb() {
		return ub;
	}
	
	/**
	 * 
	 * @param lb
	 */
	protected void setLb(int lb) {
		this.lb = lb;
	}
	
	/**
	 * 
	 * @param ub
	 */
	protected void setUb(int ub) {
		this.ub = ub;
	}
}
