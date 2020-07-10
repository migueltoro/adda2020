/**
 * 
 */
/**
 * @author migueltoro
 *
 */
module solve {
	exports us.lsi.pli;
	exports us.lsi.lpsolve;
	exports us.lsi.solve;
	exports us.lsi.gurobi;
	exports us.lsi.model;

	requires datos_compartidos;
	requires gurobi;
	requires lpsolve;
	requires org.antlr.antlr4.runtime;
	requires partecomun;
}