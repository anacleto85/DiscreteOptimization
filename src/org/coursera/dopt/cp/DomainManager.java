package org.coursera.dopt.cp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursera.dopt.cp.exception.ConstraintPropagationException;
import org.coursera.dopt.cp.exception.InfeasibleConstraintException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class DomainManager <T extends DecisionVariable<?>>
{
	protected List<T> vars;
	protected Set<Constraint> domConstraints;
	protected Set<Constraint> committedDecisions;
	protected Set<Constraint> toCommit;
	
	/**
	 * 
	 */
	protected DomainManager() {
		this.vars = new ArrayList<T>();
		this.domConstraints = new HashSet<Constraint>();
		this.toCommit = new HashSet<Constraint>();
		this.committedDecisions = new HashSet<Constraint>();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<T> getVariables() {
		return new ArrayList<T>(this.vars);
	}
	
	/**
	 * 
	 * @param decision
	 * @throws InfeasibleConstraintException 
	 * @throws ConstraintPropagationException 
	 */
	public void applyDecision(Constraint constraint) 
			throws ConstraintPropagationException, InfeasibleConstraintException
	{
		this.toCommit.add(constraint);
		// propagate
		this.propagate();
	}
	
	/**
	 * 
	 * @throws ConstraintPropagationException
	 * @throws InfeasibleConstraintException
	 */
	protected void propagate() 
			throws ConstraintPropagationException, InfeasibleConstraintException
	{
		// get constraints to propagate
		List<Constraint> decs = new ArrayList<>(this.toCommit);
		for (Constraint dec : decs) {
			try 
			{
				// check constraint feasibility
				if (!dec.isFeasible()) {
					throw new InfeasibleConstraintException("Trying to apply an infeasible constraint\n" + dec);
				}
				
				// apply constraint
				dec.prune();
				// clean to commit list
				this.toCommit.remove(dec);
				// add to committed list
				this.committedDecisions.add(dec);
			}
			catch (InfeasibleConstraintException ex) {
				throw new ConstraintPropagationException(dec, "Trying to propagate an infeasible constraint");
			}
		}
		
		// propagate all domain constraints also
		for (Constraint c : this.domConstraints) {
			// check constraint feasibility
			if (!c.isFeasible()) {
				throw new InfeasibleConstraintException("Infeasible constraint\n" + c);
			}
			// apply constraint
			c.prune();
		}
	}
}
