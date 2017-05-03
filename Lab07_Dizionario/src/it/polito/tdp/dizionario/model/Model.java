package it.polito.tdp.dizionario.model;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.dizionario.db.WordDAO;

public class Model {
	
	WordDAO wD;

	public Model() {
		super();
		wD = new WordDAO();
	}

	public UndirectedGraph<String, DefaultEdge> createGraph(int numeroLettere) {
		List<String> listaLunghe = wD.getAllWordsFixedLength(numeroLettere);
		
		UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		for (String s : listaLunghe)
			grafo.addVertex(s);
		
		for (String s1 : listaLunghe){
			for (String s2 : listaLunghe){
				char array1[] = s1.toCharArray();
				char array2[] = s2.toCharArray();
				int cont=0;
				for(int i=0; i<s1.length(); i++)
					if(array1[i]==array2[i])
						cont++;
				if(cont==s1.length()-1)
					grafo.addEdge(s1, s2);
			}
		}
		
		return grafo;
	}

	public List<String> displayNeighbours(String parolaInserita, int numeroLettere) {
		List<String> listaLunghe = wD.getAllWordsFixedLength(numeroLettere);
		
		UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		for (String s : listaLunghe)
			grafo.addVertex(s);
		
		for (String s1 : listaLunghe){
			for (String s2 : listaLunghe){
				char array1[] = s1.toCharArray();
				char array2[] = s2.toCharArray();
				int cont=0;
				for(int i=0; i<s1.length(); i++)
					if(array1[i]==array2[i])
						cont++;
				if(cont==s1.length()-1)
					grafo.addEdge(s1, s2);
			}
		}
		
		List<String> vicini = new ArrayList<String>();
		
		if(!listaLunghe.contains(parolaInserita)){
			vicini.add("Il dizionario non contiene la parola inserita!");
		}
		
		for (DefaultEdge e : grafo.edgeSet()){
			if(grafo.getEdgeSource(e).compareTo(parolaInserita)==0)
				vicini.add(grafo.getEdgeTarget(e));
			else if(grafo.getEdgeTarget(e).compareTo(parolaInserita)==0)
				vicini.add(grafo.getEdgeSource(e));
		}
		
		return vicini;
	}
	
	public List<String> tuttiVicini(String parolaInserita, int numeroLettere) {
		List<String> listaLunghe = wD.getAllWordsFixedLength(numeroLettere);
		
		UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
		
		for (String s : listaLunghe)
			grafo.addVertex(s);
		
		for (String s1 : listaLunghe){
			for (String s2 : listaLunghe){
				char array1[] = s1.toCharArray();
				char array2[] = s2.toCharArray();
				int cont=0;
				for(int i=0; i<s1.length(); i++)
					if(array1[i]==array2[i])
						cont++;
				if(cont==s1.length()-1)
					grafo.addEdge(s1, s2);
			}
		}
		
		List<String> tuttiVicini = new ArrayList<String>();
		
		if(!listaLunghe.contains(parolaInserita)){
			tuttiVicini.add("Il dizionario non contiene la parola inserita!");
		}
		
		BreadthFirstIterator<String, DefaultEdge> bfi = new BreadthFirstIterator <String, DefaultEdge>(grafo, parolaInserita);
		
		while(bfi.hasNext()){
			tuttiVicini.add(bfi.next());
		}
		
		return tuttiVicini;
	}

	public String findMaxDegree(int numeroLettere) {
		List<String> listaLunghe = wD.getAllWordsFixedLength(numeroLettere);
		
		UndirectedGraph<String, DefaultEdge> grafo = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		for (String s : listaLunghe)
			grafo.addVertex(s);
		
		for (String s1 : listaLunghe){
			for (String s2 : listaLunghe){
				char array1[] = s1.toCharArray();
				char array2[] = s2.toCharArray();
				int cont=0;
				for(int i=0; i<s1.length(); i++)
					if(array1[i]==array2[i])
						cont++;
				if(cont==s1.length()-1)
					grafo.addEdge(s1, s2);
			}
		}
		
		String res="";
		
		List<String>verticiMax = new ArrayList <String>();
		int gradoMax = 0;
		
		for (String vertex : grafo.vertexSet()){
			int grado=grafo.degreeOf(vertex);
			if(grado>gradoMax)
				gradoMax=grado;
		}
		res+="Grado massimo\n";
		
		for (String vertex : grafo.vertexSet()){
			if(grafo.degreeOf(vertex)==gradoMax)
				verticiMax.add(vertex);
		}
		
		for(String v : verticiMax)
			res+=this.stampaGrado(gradoMax, v, grafo);
		
		return res;
	}
	
	public String stampaGrado(int gradoMax, String v, UndirectedGraph<String, DefaultEdge> grafo){
		String parziale="";
		parziale+=v+": grado "+gradoMax+"\n";
		
		parziale+="Vicini: [";
		
		List<String> vicini = new ArrayList<String>();
		
		for (DefaultEdge e : grafo.edgeSet()){
			if(grafo.getEdgeSource(e).compareTo(v)==0)
				vicini.add(grafo.getEdgeTarget(e));
			else if(grafo.getEdgeTarget(e).compareTo(v)==0)
				vicini.add(grafo.getEdgeSource(e));
		}
		
		int lunghezzaLista = vicini.size();
		int cont=0;
		for (String s : vicini){
			cont++;
			if(cont==lunghezzaLista)
				parziale+=s+"]";
			else
				parziale+=s+", ";
		}
		
		return parziale;
	}
	
	
}
