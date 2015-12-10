package org.coursera.dopt.cp.gcoloring;

import java.util.List;

import org.coursera.dopt.cp.DomainManager;
import org.coursera.dopt.cp.exception.ConstraintPropagationException;
import org.coursera.dopt.cp.exception.InfeasibleConstraintException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphColoringDomainManager extends DomainManager<GraphColorDecisionVariable> 
{
	private Graph graph;
	private int colors;
	
	/**
	 * 
	 * @param graph
	 */
	public GraphColoringDomainManager(Graph graph, int colors) {
		super();
		this.colors = colors;
		this.graph = graph;
		// create constraint
		for (GraphNode node : this.graph.getAllNodes()) {
			this.domConstraints.add(new GraphNodeAdjsColorConstraint(node));
		}
	}
	
	/**
	 * 
	 * @param colors
	 * @param data
	 */
	public GraphColoringDomainManager(int colors, List<String> data) {
		super();
		this.colors = colors;
		this.graph = buildGraph(data, this.colors);
		// create constraint
		for (GraphNode node : this.graph.getAllNodes()) {
			this.domConstraints.add(new GraphNodeAdjsColorConstraint(node));
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<GraphNode> getNotColoredNodes() {
		return this.graph.getNodesToColor();
	}
	
	/**
	 * 
	 * @param toRetract
	 */
	public void retractDecision(GraphNodeColorDecisionConstraint toRetract) 
	{
		try 
		{
			// remove from committed decisions
			this.committedDecisions.remove(toRetract);
			this.toCommit.clear();
			// clean domain
			this.graph.clean(this.colors);
			// restore last stable state
			this.propagate();
		}
		catch (ConstraintPropagationException | InfeasibleConstraintException ex) {
			System.err.println("Error while retracting a decisions\n" + ex.getMessage());
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[GRAPH_COLORING_DOMAIN_MANAGER #colors= " + this.colors + "]\n" + this.graph;
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	private static Graph buildGraph(List<String> data, int colors) {
		// get graph's vector and edges
		String[] firstRow = data.get(0).split("\\s+");
		int V = Integer.parseInt(firstRow[0]);
		int E = Integer.parseInt(firstRow[1]);
		
		// build the graph
		Graph graph = new Graph(V, E, colors);
		for (int i=1; i < data.size(); i++) {
			String[] row = data.get(i).split("\\s+");
			int nodeAId = Integer.parseInt(row[0]);
			int nodeBId = Integer.parseInt(row[1]);
			
			// create nodes
			graph.createNode(nodeAId);
			graph.createNode(nodeBId);
			// create edge
			graph.addEdge(nodeAId, nodeBId);
		}
		return graph;
	}
}
