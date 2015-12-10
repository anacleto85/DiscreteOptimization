package org.coursera.dopt.cp.sudoku.solver;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.coursera.dopt.cp.exception.ConstraintPropagationException;
import org.coursera.dopt.cp.exception.InfeasibleConstraintException;
import org.coursera.dopt.cp.sudoku.CellDecisionConstraint;
import org.coursera.dopt.cp.sudoku.CellDecisionVariable;
import org.coursera.dopt.cp.sudoku.SudokuDomainManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class SudokuSolver 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			// SUDOKU size
			int size = 3;
			// initialize SUDOKU
			SudokuDomainManager dm = new SudokuDomainManager(size);
			CellDecisionVariableComparator comparator = new CellDecisionVariableComparator(size);
			
			System.out.println(dm);
			System.out.println();
			System.out.println();
			
			boolean hasWork = true;
			while (hasWork) {
				hasWork = false;
				List<CellDecisionVariable> vars = dm.getOpenDecisionVariable();
				if (!vars.isEmpty()) {
					// sort variables
					Collections.sort(vars, comparator);
					// get best variable to select
					CellDecisionVariable var = vars.get(0);
					
					// check selected variable
					if (!var.isBound()) {
						hasWork = true;
						List<CellDecisionConstraint> choices = var.getPossibleChoices();
						if (choices.isEmpty()) {
							throw new RuntimeException("No possible choices for var " + var);
						}
						
						// get the first option
						Random rand = new Random(System.currentTimeMillis());
						int index = rand.nextInt(choices.size());
						CellDecisionConstraint dec = choices.get(index);
						// propagate constraint
						System.out.println("Propagating constraint " + dec);
						dm.applyDecision(dec);
					}
				}
			}
			
			System.out.println();
			System.out.println();
			System.out.println(dm);
		}
		catch (ConstraintPropagationException | InfeasibleConstraintException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
