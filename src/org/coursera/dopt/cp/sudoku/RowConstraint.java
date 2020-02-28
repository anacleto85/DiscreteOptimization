package org.coursera.dopt.cp.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RowConstraint extends Constraint //implements DecisionVariableObserver
{
	private List<CellDecisionVariable> vars;
	private int row;
	
	/**
	 * 
	 * @param platform
	 * @param row
	 */
	public RowConstraint(SudokuPlatform platform, int row) {
		super();
		this.vars = platform.getVariablesOnRow(row);
		this.row = row;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 */
	@Override
	public boolean isFeasible() 
	{
		// get union
		Set<Integer> union = new HashSet<Integer>();
		for (CellDecisionVariable var : this.vars) {
			union.addAll(var.getValues());
		}
			
		// check union size
		return (union.size() >= this.vars.size());
	}
	
	/**
	 * 
	 */
	@Override
	public void prune() {
		// get values assigned
		Set<Integer> union = new HashSet<Integer>();
		for (CellDecisionVariable var : this.vars) {
			if (var.isBound()) {
				union.addAll(var.getValues());
			}
		}
		
		// check union size
		if (!union.isEmpty()) {
			// remove values in union from not ground variables
			for (CellDecisionVariable var : this.vars) {
				if (!var.isBound()) {
					var.exclude(union);
				}
			}
		}
	}
}
