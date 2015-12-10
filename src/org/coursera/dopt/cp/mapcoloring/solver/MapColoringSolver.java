package org.coursera.dopt.cp.mapcoloring.solver;

import java.util.List;

import org.coursera.dopt.cp.exception.ConstraintPropagationException;
import org.coursera.dopt.cp.exception.InfeasibleConstraintException;
import org.coursera.dopt.cp.mapcoloring.CountryDecisionConstraint;
import org.coursera.dopt.cp.mapcoloring.CountryDecisionVariable;
import org.coursera.dopt.cp.mapcoloring.MapColoringDomainManager;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MapColoringSolver 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// number of countries
		int colors = 3;
		// create domain manager
		MapColoringDomainManager dm = new MapColoringDomainManager(colors);
		
		// create variables
		CountryDecisionVariable england = dm.createDecisionVariable("England");
		CountryDecisionVariable france = dm.createDecisionVariable("France");
		CountryDecisionVariable belgium = dm.createDecisionVariable("Belgium");
		CountryDecisionVariable germany = dm.createDecisionVariable("Germany");
		CountryDecisionVariable switzerland = dm.createDecisionVariable("Switzerland");
		CountryDecisionVariable italy = dm.createDecisionVariable("Italy");
		
		// create domain constraints
		dm.addDomainConstraint(england, france);
		dm.addDomainConstraint(england, belgium);
		dm.addDomainConstraint(france, belgium);
		dm.addDomainConstraint(france, germany);
		dm.addDomainConstraint(france, italy);
		dm.addDomainConstraint(france, switzerland);
		dm.addDomainConstraint(germany, belgium);
		dm.addDomainConstraint(germany, switzerland);
		dm.addDomainConstraint(switzerland, italy);
		
		// print initial status
		System.out.print(dm);
		System.out.println();
		System.out.println();
		
		try {
			boolean hasWork = true;
			while (hasWork) {
				hasWork = false;
				for (CountryDecisionVariable var : dm.getVariables()) {
					if (!var.isBound()) {
						hasWork = true;
						// get possible choices
						List<CountryDecisionConstraint> choices = var.getPossibleChoices();
						if (choices.isEmpty()) {
							throw new RuntimeException("No possible choices for var " + var);
						}
						
						// get the first option
						CountryDecisionConstraint dec = choices.get(0);
						// propagate constraint
						System.out.println("Propagating constraint " + dec);
						dm.applyDecision(dec);
						break;
					}
				}
			}
			
			// print final state
			System.out.println();
			System.out.println();
			System.out.println(dm);
		}
		catch (ConstraintPropagationException | InfeasibleConstraintException ex) {
			System.err.println(ex.getMessage());
		}
	}
}
