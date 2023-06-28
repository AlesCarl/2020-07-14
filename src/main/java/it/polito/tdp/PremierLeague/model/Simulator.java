package it.polito.tdp.PremierLeague.model;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;
import it.polito.tdp.yelp.model.Event;
import it.polito.tdp.yelp.model.Event.EventType;




public class Simulator {

	//  input 
	    private int reporterTeam; 
	    private int minReporter;
	    
	  	Map <Team,Integer> mapTeamReporters=  new HashMap<>();
	  	private PremierLeagueDAO dao; 

	  

	// Stato del sistema:
	    SimpleWeightedGraph<Team, DefaultWeightedEdge> graph; 
	    
	    
	// paramentri di OUTPUT:
	  Map <Integer,Integer>mapIntervistati=  new HashMap<Integer,Integer>();
	 	

	// coda eventi
	  private PriorityQueue<Event> queue; 
	    
	    
	    
	public Simulator(SimpleWeightedGraph<Team, DefaultWeightedEdge> graph, int reporterTeam, int minReporter) {
		
		this.graph= graph;
		this.reporterTeam=reporterTeam;
		this.minReporter=minReporter; 
		
	}
	
	public void initialize() {
		this.queue= new PriorityQueue<Event>();
		
		/**ASSEGNO ad ogni team 5 reporter all'inizio (nella mappa) **/ 
		for(Team tt: this.graph.vertexSet()) {
			// qui ho messo N reporter ad ogni team.
			mapTeamReporters.put(tt, reporterTeam);
			
		}
		List<Match> allMatch = dao.listAllMatches(); 
		
		/**CREO EVENTI INIZIALI, inizializzando la CODA degli eventi **/ 
		
		for( Match m: allMatch) {
			this.queue.add(new Event(EventType.CONFERMA,5, t)) ; 

		}
		
// l'evento è: 
		// tipi: 
	
	}

	public void run() {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
