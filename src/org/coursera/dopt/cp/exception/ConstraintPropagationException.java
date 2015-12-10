package org.coursera.dopt.cp.exception;

import org.coursera.dopt.cp.Constraint;

/**
 * 
 * @author alessandroumbrico
 *
 * @param <T>
 */
public class ConstraintPropagationException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private Constraint constraint;
	
	/**
	 * 
	 * @param constraint
	 * @param message
	 */
	public ConstraintPropagationException(Constraint constraint, String message) {
		super(message);
		this.constraint = constraint;
	}
	
	/**
	 * 
	 * @return
	 */
	public Constraint getConstraint() {
		return constraint;
	}
}
