package org.coursera.dopt.cp.gcoloring;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.coursera.dopt.cp.DecisionVariable;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphColorDecisionVariable extends DecisionVariable<GraphColorDecisionVariableDomain> 
{
	private boolean[] values;
	
	/**
	 * 
	 * @param dom
	 * @param node
	 */
	public GraphColorDecisionVariable(GraphColorDecisionVariableDomain dom, GraphNode node) {
		super(dom, "Node_" + node.getId() + "_Color");
		this.values = new boolean[this.dom.getColors()];
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = true;
		}
	}
	
	/**
	 * 
	 * @param dom
	 */
	public void setDomain(GraphColorDecisionVariableDomain dom) {
		this.dom = dom;
	}
	
	/**
	 * 
	 */
	public void clean() {
		// re-initialize variable
		this.values = new boolean[this.dom.getColors()];
		for (int i=0; i < this.values.length; i++) {
			this.values[i] = true;
		}
	}
	
	/**
	 * 
	 * @return
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
		return "[NODE_COLOR name=" + this.name + ", values=" + vals + "]";
	}
}
