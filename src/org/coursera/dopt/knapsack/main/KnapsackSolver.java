package org.coursera.dopt.knapsack.main;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.coursera.dopt.knapsack.KnapsackProblem;
import org.coursera.dopt.knapsack.KnapsackSolution;
import org.coursera.dopt.knapsack.KnapsackSolverImpl;
import org.coursera.dopt.knapsack.bb.BranchBoundKnapsackSolverImpl;

/**
 * The class <code>Solver</code> is an implementation of a greedy algorithm to solve the knapsack problem.
 *
 */
public class KnapsackSolver {
    
    /**
     * The main class
     */
    public static void main(String[] args) {
        try 
        {
            KnapsackProblem problem = new KnapsackProblem(parseInput(args));
//        	KnapsackSolverImpl solver = new DynamicProgrammingKnapsackSolverImpl(problem);
            KnapsackSolverImpl solver = new BranchBoundKnapsackSolverImpl(problem);
            KnapsackSolution solution = solver.solve();
            
            // prepare the solution in the specified output format
            System.out.println(solution.getValue()+" 0");
            System.out.println(solution);
            System.out.println("");
            
        } catch (IOException e) {
            e.printStackTrace();
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