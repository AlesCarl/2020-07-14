package it.polito.tdp.PremierLeague.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;
import it.polito.tdp.yelp.model.Simulator;


public class Model {
	
	PremierLeagueDAO dao; 
    private SimpleWeightedGraph<Team, DefaultWeightedEdge> graph;  // SEMPLICE, PESATO, NON ORIENTATO
    private List<Team> allTeams ; 

	
    

	public Model() {
		this.dao= new PremierLeagueDAO();  
    	this.graph= new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    	//this.allNegozi= new ArrayList<>(); 
    	
		
	}
	
	private void loadAllNodes( ) {
		this.allTeams= dao.listAllTeams();

		
	}	
	
	// calcolo la classifica di ciascuna squadra, volta per volta.
 public void creaGrafo() {
		 
		 
		 loadAllNodes(); 

		 
		 /** VERTICI */
	    	Graphs.addAllVertices(this.graph, allTeams);
	 		System.out.println("NUMERO vertici GRAFO: " +this.graph.vertexSet().size());
	 	
	 		/*
	 		l’arco orientato da più punti verso quella che ne ha collezionati di meno. 
	 		il peso, sempre positivo, sarà la differenza di punti. S
	 		 	 */
	 		 			
	 		 	/** ARCHI */
	 		 		for(Team t1: allTeams) {
	 		 			for(Team t2: allTeams) {
	 		 				
	 		 				if(t1.getTeamID().compareTo(t2.getTeamID())!=0) {
	 		 					double peso= calcolaPeso(t1,t2); 
	 		 					
	 		 					  if(peso>0) {  // t1-t2  t1 ha piu punti
	 		 						Graphs.addEdgeWithVertices(this.graph, t1, t2, peso);
	 		 					  }
	 		 					
	 		 				}
	 		 			}
	 		 		}
	 		 		
	 	 	 		System.out.println("\nnumero ARCHI: "+ this.graph.edgeSet().size());
	 			
	 	    }

private int calcolaPeso(Team t1, Team t2) {
	int puntiT1= getPunteggio(t1); 
	int puntiT2= getPunteggio(t2); 
	
	//System.out.println("\nprova: "+ puntiT1);

	
	return puntiT1-puntiT2;
	
}

public List<Team> getAllTeam(){
	return this.allTeams; 
}


public int getPunteggio(Team t) {
	int puntiC= dao.getPunteggioCasa(t); 
	int puntiFC= dao.getPunteggioFuoriCasa(t); 
	
	return puntiC+puntiFC; 
	
}

List<teamDifferenzaPunti> temPeggiori= new ArrayList<>(); 

public List<teamDifferenzaPunti> getTeamPeggiori(Team team){
	
	int punteggioTeam= getPunteggio(team); 
	
	
	for(Team tt: this.allTeams) {
		if(tt.getTeamID()!=team.getTeamID()) {
		int punteggio= getPunteggio(tt);
		if(punteggioTeam>punteggio) {
			int differenza= punteggioTeam-punteggio; 
			teamDifferenzaPunti tDiff = new teamDifferenzaPunti(tt,differenza); 
			temPeggiori.add(tDiff);
		}
	 }
	}
	
	return temPeggiori;
	
}
List<teamDifferenzaPunti> teamMigliori= new ArrayList<>(); 

public List<teamDifferenzaPunti> getTeamMIgliori(Team team){
	
	int punteggioTeam= getPunteggio(team); 
	//List<teamDifferenzaPunti> result= new ArrayList<>(); 
	
	
	for(Team tt: this.allTeams) {
		
		if(tt.getTeamID()!=team.getTeamID()) {
		int punteggio= getPunteggio(tt);
		if(punteggioTeam<punteggio) {
			int differenza= punteggio- punteggioTeam; 
			teamDifferenzaPunti tDiff = new teamDifferenzaPunti(tt,differenza); 
			teamMigliori.add(tDiff);
		}
	 }
	}	
	return teamMigliori;
	
}

//public List<teamDifferenzaPunti> getClassificaTotale() {
//	List<teamDifferenzaPunti> temp= new ArrayList<>(); 
//	temp
//	
//
//}


/***************  COLLEGA SIMULAZIONE E MODEL   **************/ 
public  Map<Integer, Integer>  simula(int totReporter, int minReporter ) 
{
		
	Simulator sim = new Simulator(this.graph, totReporter,minReporter);
	
	sim.initialize();
	sim.run();
	
	giorniTrascorsiSim= sim.getGiorniTrascorsiSim();
	
	return sim.getSimulationMap(); 
	
}













}
