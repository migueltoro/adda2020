package us.lsi.search.mochila;

import java.util.Locale;

import us.lsi.graphs.search.DPRSearch;
import us.lsi.graphs.search.DynamicProgramming.PDType;
import us.lsi.graphs.search.DynamicProgrammingReduction;
import us.lsi.graphs.search.GSearch;
import us.lsi.graphs.search.GreedySearch;
import us.lsi.graphs.virtual.EGraph;
import us.lsi.graphs.virtual.SimpleVirtualGraph;
import us.lsi.mochila.datos.DatosMochila;
import us.lsi.mochila.datos.SolucionMochila;
import us.lsi.path.EGraphPath;


public class TestPDRMochila {
	
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		DatosMochila.iniDatos("ficheros/objetosMochila.txt");
		DatosMochila.capacidadInicial = 78;	
		MochilaVertex e1 = MochilaVertex.of(78.);
		MochilaVertex e2 = MochilaVertex.lastVertex();
		EGraph<MochilaVertex, MochilaEdge> graph = SimpleVirtualGraph.sum(e1,x->x.getEdgeWeight());	
		
		GreedySearch<MochilaVertex, MochilaEdge> rr = GSearch.greedy(graph,MochilaVertex::greadyEdge,e->e.equals(e2));
		Double bv = rr.weightToEnd();	
		
		DynamicProgrammingReduction<MochilaVertex, MochilaEdge, SolucionMochila> ms = 
				DPRSearch.dynamicProgrammingReductionEnd(graph,
						e2,
						MochilaHeuristic::heuristic,
						MochilaVertex::getSolucion,
						PDType.Max);
		
		ms.bestValue = bv;
		
		System.out.println(ms.search());
		System.out.println(ms.solutionsTree);
		EGraphPath<MochilaVertex, MochilaEdge> s1 = ms.pathFrom(e1);
		System.out.println(s1);
		SolucionMochila s = ms.getSolution(s1);
		System.out.println(s);
	}

}