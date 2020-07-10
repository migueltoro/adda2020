package us.lsi.search.floyd;


import java.util.Locale;
import java.util.stream.Collectors;

import org.jgrapht.graph.SimpleWeightedGraph;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.alg.DP;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.hypergraphs.SimpleVirtualHyperGraph;

public class TestFloyd {
	
	public static SimpleWeightedGraph<Ciudad, Carretera> leeDatos(String fichero) {
		SimpleWeightedGraph<Ciudad, Carretera> graph = GraphsReader.newGraph(fichero, 
				Ciudad::ofFormat, 
				Carretera::ofFormat,
				Graphs2::simpleWeightedGraph, 
				Carretera::getKm);
		return graph;
	}
	
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		SimpleWeightedGraph<Ciudad, Carretera> graph = leeDatos("./ficheros/andalucia.txt");
		
		System.out.println(graph);
		
		Ciudad origen = Ciudad.ofName("Cadiz");
		Ciudad destino = Ciudad.ofName("Almeria");
		
		FloydVertex<Ciudad,Carretera> p = FloydVertex.of(graph,origen,destino);
		
		SimpleVirtualHyperGraph<FloydVertex<Ciudad,Carretera>,FloydEdge<Ciudad,Carretera>,FloydVertex.ActionFloyd> graph2 = 
				Graphs2.simpleVirtualHyperGraph();
		
		DP<FloydVertex<Ciudad,Carretera>,FloydEdge<Ciudad,Carretera>,FloydVertex.ActionFloyd> a = 
				DP.dynamicProgrammingSearch(graph2,PDType.Min);
		
		a.search(p);
		System.out.println(FloydVertex.solution(a.tree(p)).getVertexList().stream().collect(Collectors.toList()));
		System.out.println(a.tree(p));
	}

}
