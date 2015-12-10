package org.coursera.dopt.cp.exception;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class InfeasibleConstraintException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public InfeasibleConstraintException(String message) {
		super(message);
	}
}
