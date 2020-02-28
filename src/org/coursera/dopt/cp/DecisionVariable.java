package org.coursera.dopt.cp;



/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class DecisionVariable<T extends DecisionVariableDomain>
{
	private static long COUNTER = 0;
	protected long id;
	protected String name;
	protected T dom;
	
	/**
	 * 
	 * @param dom
	 */
	protected DecisionVariable(T dom, String name) {
		this.id = getNextId();
		this.dom = dom;
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	private static long getNextId() {
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
		if (!(obj instanceof DecisionVariable)) {
			throw new RuntimeException("Uncomparable objects");
		}
		return this.id == ((DecisionVariable<?>) obj).id;
	}
}
