package org.coursera.dopt.cp.gcoloring.solver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.coursera.dopt.cp.exception.ConstraintPropagationException;
import org.coursera.dopt.cp.exception.InconsistentDecisionVariable;
import org.coursera.dopt.cp.exception.InfeasibleConstraintException;
import org.coursera.dopt.cp.gcoloring.GraphColoringDomainManager;
import org.coursera.dopt.cp.gcoloring.GraphNode;
import org.coursera.dopt.cp.gcoloring.GraphNodeColorDecisionConstraint;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class GraphColoringSolver 
{
	private int colors;
	private GraphColoringDomainManager dm;
	private boolean running;
	private Stack<GCSearchSpaceNode> stack;
	private GraphNodeComparator comparator;
	private GCSearchSpaceNode current;
	private Logger logger;
	
	/**
	 * 
	 * @param colors
	 * @param data
	 */
	public GraphColoringSolver(int colors, List<String> data, Level lev) {
		this.colors = colors;
		this.dm = new GraphColoringDomainManager(this.colors, data);
		this.stack = new Stack<GCSearchSpaceNode>();
		this.comparator = new GraphNodeComparator();
		this.current = null;
		this.logger = Logger.getLogger(this.getClass().getName());
		this.logger.setLevel(lev);
		
		// set console handler for logger
		this.logger.setUseParentHandlers(false);
		Handler handler = new ConsoleHandler();
		handler.setLevel(Level.FINEST);
		this.logger.addHandler(handler);
	}
	
	/**
	 * 
	 * @return
	 */
	public void solve() {
		// write log
		this.logger.fine(this.dm + "\n");
		// create root (empty) node
		this.current = new GCSearchSpaceNode();
		
		// start search
		this.running = true;
		while (this.running) {
			try {
				// get all nodes to color
				List<GraphNode> toColor = this.dm.getNotColoredNodes();
				// select the best node to color
				Collections.sort(toColor, this.comparator);
				// get the best
				GraphNode best = toColor.get(0);
			
				// get possible values
				for (GraphNodeColorDecisionConstraint constraint : best.getValueChoices()) {
					// make a node
					GCSearchSpaceNode node = new GCSearchSpaceNode(this.current, constraint);
					// add to stack 
					this.stack.add(node);
				}
				
				// get next
				this.current = this.stack.pop();
				// get constraint to apply
				GraphNodeColorDecisionConstraint dec = this.current.getToApply();
				try {
					this.logger.fine("Apply constraint " + dec + "\n");
					// apply constraint
					this.dm.applyDecision(dec);
					// mark node as expanded
					this.current.markAsExpanded();
				}
				catch (ConstraintPropagationException | InfeasibleConstraintException ex) {
					this.logger.fine(ex.getMessage() + "\nRetract decision " + dec + "\n");
					// backtrack
					this.dm.retractDecision(dec);
				}
			}
			catch (InconsistentDecisionVariable ex) {
				// inconsistent variable
				this.logger.severe("Inconsistent node\n" + ex.getMessage() + "\n");
				this.running = false;
			}
			catch (EmptyStackException ex) {
				// empty stack
				this.logger.fine("Empty Stack!\n");
				this.running = false;
			}
			catch (IndexOutOfBoundsException ex) {
				// no node to color
				this.running = false;
			}
		}
		
		// print solution
		this.logger.info(this.dm + "\n");
	}
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// set number of colors
			int colors = 21;
			GraphColoringSolver solver = new GraphColoringSolver(colors, parseInput(args), Level.INFO);
			// solve problem
			solver.solve();
		}
		catch (IOException ex) {
			// parsing error
			System.err.println(ex.getMessage());
		}
	}

	/**
     * 
     * @param args
     * @return
     */
    private static List<String> parseInput(String[] args) throws IOException 
    {
    	String fileName = null;
        
        // get the temp file name
        for(String arg : args){
            if(arg.startsWith("-file=")){
                fileName = arg.substring(6);
            } 
        }
        if(fileName == null)
            return null;
        
        // read the lines out of the file
        List<String> lines = new ArrayList<String>();

        BufferedReader input =  new BufferedReader(new FileReader(fileName));
        try {
            String line = null;
            while (( line = input.readLine()) != null){
                lines.add(line);
            }
        }
        finally {
            input.close();
        }
        return lines;
    }
}
