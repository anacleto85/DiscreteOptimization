package org.coursera.dopt.cp.sudoku;

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
public class CellDecisionVariable extends DecisionVariable<CellDecisionVariableDomain> 
{
	private int row;
	private int column;
	private boolean[] values;
	
	/**
	 * 
	 * @param dom
	 * @param row
	 * @param column
	 */
	public CellDecisionVariable(CellDecisionVariableDomain dom, int row, int column) {
		super(dom, "<"+row+","+column+">");
		this.row = row;
		this.column = column;
		this.values = new boolean[dom.getCellMaxValue()];
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = true;
		}
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
	 * @return
	 */
	public int getColumn() {
		return column;
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
	 * @param val
	 */
	public void valueChoice(int val) {
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = (i == val);
		}
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
	 * @return
	 */
	public boolean isBound() {
		return (this.getValues().size() == 1);
	}

	/**
	 * 
	 * @return
	 */
	public List<CellDecisionConstraint> getPossibleChoices() {
		List<CellDecisionConstraint> choices = new ArrayList<CellDecisionConstraint>();
		for (Integer value : this.getValues()) {
			choices.add(new CellDecisionConstraint(this, value));
		}
		return choices;
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
		return "[CELL_DECISION_VARIABLE name=" + this.name + ", values=" + vals + "]";
	}
}
