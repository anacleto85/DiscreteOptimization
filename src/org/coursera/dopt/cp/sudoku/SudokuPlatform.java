package org.coursera.dopt.cp.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class SudokuPlatform 
{
	private int size;
	private CellDecisionVariableDomain cdom;
	private CellDecisionVariable[][] cells;
	
	/**
	 * 
	 * @param size
	 */
	public SudokuPlatform(int size) {
		this.size = size;
		this.cdom = new CellDecisionVariableDomain(0, size);
		this.cells = new CellDecisionVariable[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.cells[i][j] = new CellDecisionVariable(this.cdom, i, j);
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CellDecisionVariable> getOpenChoiceVariables() {
		List<CellDecisionVariable> vars = new ArrayList<CellDecisionVariable>();
		for (int i=0; i < this.size; i++) {
			for (int j=0; j < this.size; j++) {
				if (!this.cells[i][j].isBound()) {
					vars.add(this.cells[i][j]);
				}
			}
		}
		return vars;
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public CellDecisionVariable getVariableAtCell(int i, int j) {
		return this.cells[i][j];
	}
	
	/**
	 * 
	 * @param row
	 * @return
	 */
	public List<CellDecisionVariable> getVariablesOnRow(int row) {
		List<CellDecisionVariable> rows = new ArrayList<CellDecisionVariable>();
		for (int i = 0; i < this.size; i++) {
			rows.add(this.cells[row][i]);
		}
		return rows;
	}
	
	/**
	 * 
	 * @param column
	 * @return
	 */
	public List<CellDecisionVariable> getVariablesOnColumn(int column) {
		List<CellDecisionVariable> cols = new ArrayList<CellDecisionVariable>();
		for (int i = 0; i < this.size; i++) {
			cols.add(this.cells[i][column]);
		}
		return cols;
	}

	/**
	 * 
	 * @return
	 */
	public int getRows() {
		return this.size;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getColumns() {
		return this.size;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String desc = "[SUDOKU_PLATFROM size= " + this.size + "]\n";
		for (int i=0; i < this.size; i++) {
			for (int j=0; j < this.size; j++) {
				desc += " " + this.cells[i][j].getValues() + " ";
			}
			desc += "\n";
		}
		return desc;
	}
}
