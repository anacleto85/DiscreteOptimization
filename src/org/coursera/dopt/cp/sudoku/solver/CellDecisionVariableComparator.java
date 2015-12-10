package org.coursera.dopt.cp.sudoku.solver;

import java.util.Comparator;

import org.coursera.dopt.cp.sudoku.CellDecisionVariable;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class CellDecisionVariableComparator implements Comparator<CellDecisionVariable> 
{
	private double diagonal;
	
	/**
	 * 
	 * @param platformSize
	 */
	public CellDecisionVariableComparator(int platformSize) {
		double row = platformSize / 2;
		double col = platformSize / 2;
		this.diagonal = this.computeDiagonal(row, col);
	}
	
	/**
	 * 
	 */
	@Override
	public int compare(CellDecisionVariable o1, CellDecisionVariable o2) {
		int result = o1.getValues().size() - o2.getValues().size();
		if (result == 0) {
			// compute diagonals
			double d1 = this.computeDiagonal(o1.getRow(), o1.getColumn());
			double d2 = this.computeDiagonal(o2.getRow(), o2.getColumn());
			
			// get difference with the reference diagonal
			double diff1 = Math.abs((d1 - this.diagonal));
			double diff2 = Math.abs((d2 - this.diagonal));
			
			// compute result
			result = (diff1 <= diff2) ? -1 : +1; 
		}
		
		// returns comparison result
		return result;
	}
	
	/**
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	private double computeDiagonal(double row, double column) {
		return Math.sqrt((row * row) + (column * column));
	}
}
