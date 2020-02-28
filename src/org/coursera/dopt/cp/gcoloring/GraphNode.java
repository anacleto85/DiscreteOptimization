package org.coursera.dopt.cp.gcoloring;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.coursera.dopt.cp.exception.InconsistentDecisionVariable;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphNode implements Comparable<GraphNode>
{
	private Graph graph;
	protected int id;
	private GraphColorDecisionVariable var;
	
	/**
	 * 
	 * @param id
	 * @param dom
	 * @param graph
	 */
	public GraphNode(int id, GraphColorDecisionVariableDomain dom, Graph graph) {
		this.id = id;
		this.var = new GraphColorDecisionVariable(dom, this);
		this.graph = graph;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 * @throws InconsistentDecisionVariable
	 */
	public List<GraphNodeColorDecisionConstraint> getValueChoices() 
			throws InconsistentDecisionVariable 
	{
		// get variable possible values
		List<Integer> values = this.var.getValues();
		if (values.isEmpty()) {
			throw new InconsistentDecisionVariable("Inconsistente decision variable " + this.var);
		}
		
		List<GraphNodeColorDecisionConstraint> choices = new LinkedList<>();
		for (Integer color : values) {
			choices.add(new GraphNodeColorDecisionConstraint(this, color));
		}
		return choices;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<GraphNode> getAdjsNodes() {
		return new LinkedList<GraphNode>(this.graph.getAdjNodes(this.id));
	}
	
	/**
	 * 
	 * @param color
	 */
	public void selectColor(int color) {
		this.var.valueChoice(color);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasColor() {
		return this.var.isBound();
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Integer> getNodeColors() {
		return this.var.getValues();
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public boolean isColorInDomain(int color) {
		return this.var.isInDomain(color);
	}
	
	/**
	 * 
	 * @param colors
	 */
	public void excludeColors(Collection<Integer> colors) {
		this.var.exclude(colors);
	}

	/**
	 * 
	 */
	public void clean() {
		this.var.clean();
	}

	/**
	 * 
	 * @param dom
	 */
	public void setVariableDomain(GraphColorDecisionVariableDomain dom) {
		this.var.setDomain(dom);
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return this.id;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(GraphNode o) {
		return this.id - o.id;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof GraphNode) && (this.id == ((GraphNode) obj).id);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[GRAPH_NODE id=" + this.id + ", var=" + this.var + "]";
	}

	/**
	 * 
	 * @return
	 */
	public String getColor() {
		return ""+ this.var.getValues().get(0);
	}
}
