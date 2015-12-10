package org.coursera.dopt.cp.sudoku;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class SquareConstraint extends Constraint
{
	private List<CellDecisionVariable> vars;
	
	/**
	 * 
	 * @param platform
	 * @param minRow
	 * @param maxRow
	 * @param minCol
	 * @param maxCol
	 */
	public SquareConstraint(SudokuPlatform platform, int minRow, int maxRow, int minCol, int maxCol) {
		this.vars = new ArrayList<CellDecisionVariable>();
		for (int i=minRow; i <= maxRow; i++) {
			for (int j=minCol; j <= maxCol; j++) {
				CellDecisionVariable var = platform.getVariableAtCell(i, j); 
				this.vars.add(var);
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isFeasible() {
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
