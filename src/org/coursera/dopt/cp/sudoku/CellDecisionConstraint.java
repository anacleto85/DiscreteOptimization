package org.coursera.dopt.cp.sudoku;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CellDecisionConstraint extends Constraint
{
	private CellDecisionVariable var;
	private int value;
	
	/**
	 * 
	 * @param var
	 * @param value
	 */
	public CellDecisionConstraint(CellDecisionVariable var, int value) {
		super();
		this.var = var;
		this.value = value;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isFeasible() {
		return this.var.isInDomain(this.value);
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		this.var.valueChoice(this.value);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[CELL_DECISION_CONSTRAINT var=" + this.var + ", value= " + this.value + "]";
	}
}
