package us.lsi.flowgraph.examples;


import us.lsi.flowgraph.FlowGraphSolution;
import us.lsi.common.Files2;
import us.lsi.flowgraph.FlowGraph;
import us.lsi.flowgraph.FlowGraph.FGType;
import us.lsi.lpsolve.AlgoritmoLpSolve;
import us.lsi.lpsolve.SolutionLpSolve;



/**
 * Un ejemplo de red de flujo
 * 
 * @author Miguel Toro
 *
 */
public class Flow1 {

	/**
	 * @param args Argumentos
	 */
	public static void main(String[] args) {
		
		FlowGraph fg = FlowGraph.newGraph("ficheros/flow1.txt",FGType.Max);
		String constraints = fg.getConstraints();
		Files2.toFile(constraints,"ficheros/flow1Constraints.txt");		
		SolutionLpSolve s = AlgoritmoLpSolve.getSolutionFromFile("ficheros/flow1Constraints.txt");	
		FlowGraphSolution fs = FlowGraphSolution.create(fg,s);
		FlowGraphSolution.exportToDot(fg,fs,"ficheros/flow1Soluciones.gv");
	}
	
	
	
}
