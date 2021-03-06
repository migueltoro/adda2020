package us.lsi.alg.reinas;

import java.util.stream.Collectors;

import org.jgrapht.GraphPath;

import us.lsi.common.Lists2;

public class SolucionReinas {
	
	public static SolucionReinas of(GraphPath<ReinasVertex, ReinasEdge> path) {		
		SolucionReinas r = new SolucionReinas(path);
		if(r.errores >0) r = null;
		return r;
	}

//	private GraphPath<ReinasVertex, ReinasEdge> path;
	private ReinasVertex last;
	private Integer error1;
	private Integer error2;
	private Integer error3;
	private Integer errores;

	private SolucionReinas(GraphPath<ReinasVertex, ReinasEdge> path) {
		super();
		this.last = Lists2.last(path.getVertexList());
		this.error1 = (ReinasVertex.n - this.last.fo.stream().collect(Collectors.toSet()).size());
		this.error2 = ReinasVertex.n - this.last.dpo.size();
		this.error3 = ReinasVertex.n - this.last.dso.size();
		this.errores = this.error1+this.error2+this.error3;
	}

	@Override
	public String toString() {
		return String.format("\nErrores %d\n%s",this.errores,this.last.fo);
	}
	
}
