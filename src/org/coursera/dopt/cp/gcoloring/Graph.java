package org.coursera.dopt.cp.gcoloring;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class Graph 
{
	private int V;
	private int E;
	private GraphColorDecisionVariableDomain dom;
	private GraphNode[] nodes;
	private List<GraphNode>[] adjs;
	
	/**
	 * 
	 * @param colors
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V, int E, int colors) {
		this.V = V;
		this.E = E;
		this.dom = new GraphColorDecisionVariableDomain(colors);
		this.nodes = new GraphNode[this.V];
		this.adjs = (List<GraphNode>[]) new List[this.V];
	}
	
	/**
	 * 
	 * @param colors
	 */
	public void clean(int colors) {
		// update variable domain
		this.dom = new GraphColorDecisionVariableDomain(colors);
		// clean all node variables
		for (GraphNode node : this.nodes) {
			node.setVariableDomain(this.dom);
			node.clean();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getV() {
		return V;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getE() {
		return E;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void createNode(int id) {
		if (this.nodes[id] == null) {
			this.nodes[id] = new GraphNode(id, this.dom, this);
			this.adjs[id] = new LinkedList<GraphNode>();
		}
	}
	
	/**
	 * 
	 * @param aId
	 * @param bId
	 */
	public void addEdge(int aId, int bId) {
		if (this.nodes[aId] != null && this.nodes[bId] != null) {
			this.adjs[aId].add(this.nodes[bId]);
			this.adjs[bId].add(this.nodes[aId]);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<GraphNode> getNodesToColor() {
		List<GraphNode> list = new LinkedList<GraphNode>();
		for (GraphNode node : this.nodes) {
			if (!node.hasColor()) {
				list.add(node);
			}
		}
		return list;
 	}
	
	/**
	 * 
	 * @return
	 */
	public List<GraphNode> getAllNodes() {
		return Arrays.asList(this.nodes);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<GraphNode> getAdjNodes(int id) {
		return this.adjs[id];
	}
	
	/**
	 * 
	 * @return
	 */
	public String printSolution() {
		String sol = "";
		for (GraphNode node : this.nodes) {
			sol += node.getColor() + " ";
		}
		return sol;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String desc = "[GRAPH <V=" + this.V + ",E=" + this.E + ">]\n";
		for (GraphNode node : this.nodes) {
			desc += node + " -> " + "{" + this.adjs[node.getId()] + "}\n";
		}
		return desc;
	}
}
