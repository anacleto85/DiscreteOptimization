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
public class ColumnConstraint extends Constraint
{
	private List<CellDecisionVariable> vars;
	private int column;
	
	/**
	 * 
	 * @param platform
	 * @param column
	 */
	public ColumnConstraint(SudokuPlatform platform, int column) {
		super();
		this.vars = platform.getVariablesOnColumn(column);
		this.column = column;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColumn() {
		return column;
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
