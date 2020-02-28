package org.coursera.dopt.cp.sudoku;

import org.coursera.dopt.cp.DecisionVariableDomain;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CellDecisionVariableDomain extends DecisionVariableDomain 
{
	/**
	 * 
	 * @param lb
	 * @param ub
	 */
	public CellDecisionVariableDomain(int lb, int ub) {
		super(lb, ub);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCellMaxValue() {
		return this.ub;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCellMinValue() {
		return this.lb;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[CELL_DECISION_DOMAIN lb=" + this.lb + ", ub= " + this.ub + "]";
	}
}