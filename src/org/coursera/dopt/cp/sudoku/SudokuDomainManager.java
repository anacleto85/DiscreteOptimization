package org.coursera.dopt.cp.sudoku;

import java.util.List;

import org.coursera.dopt.cp.DomainManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class SudokuDomainManager extends DomainManager<CellDecisionVariable> 
{
	private SudokuPlatform platform;

	/**
	 * 
	 * @param size
	 */
	public SudokuDomainManager(int size) {
		super();
		this.platform = new SudokuPlatform(size);
		for (int i=0; i < size; i++) {
			this.domConstraints.add(new RowConstraint(this.platform, i));
		}
		
		for (int i=0; i < size; i++) {
			this.domConstraints.add(new ColumnConstraint(this.platform, i));
		}
		
		int offset = (size / 3);
		for (int i=0; i < size; i+=offset) {
			for (int j=0; j < size; j+=offset) {
				this.domConstraints.add(new SquareConstraint(this.platform, i, i+ (offset-1), j, j+(offset-1)));
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<CellDecisionVariable> getOpenDecisionVariable() {
		return this.platform.getOpenChoiceVariables();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[SUDOKU_DOMAIN_MANAGER]\n" + this.platform;
	}
}
