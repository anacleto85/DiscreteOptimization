package org.coursera.dopt.cp;


/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class Constraint
{
	private static long COUNTER = 0;
	protected long id;
	
	/**
	 * 
	 */
	protected Constraint() {
		this.id = getNextId();
	}
	
	/**
	 * 
	 * @return
	 */
	private synchronized static long getNextId() {
		return COUNTER++;
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return new Long(this.id).hashCode();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Constraint)) {
			throw new RuntimeException("Uncomparable objects");
		}
		return this.id == ((Constraint) obj).id;
	}
	
	/**
	 * Check if the constraint is feasible or not.
	 * It returns true if the constraint can be satisfied given the values
	 * in the domains of its variables.
	 * It returns false if the constraint can not be satisfied.
	 * 
	 * @return
	 */
	public abstract boolean isFeasible();
	
	/**
	 * If the constraint is satisfiable,
	 * the method apply the constraint in order to determine which values in the
	 * domain can not be part of any solution.
	 * It prunes variable domains.
	 */
	public abstract void prune();
}
