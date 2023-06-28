package it.polito.tdp.PremierLeague.model;

public class teamDifferenzaPunti  implements Comparable<teamDifferenzaPunti>{
	
	Team team;
	Integer differenzaPunti;
	
	

	public teamDifferenzaPunti(Team team, Integer differenzaPunti) {
		super();
		this.team = team;
		this.differenzaPunti = differenzaPunti;
	}

	
	
	@Override
	public String toString() {
		return "" + team + ",    differenzaPunti:  " + differenzaPunti;
	}



	public Team getTeam() {
		return team;
	}


	public void setTeam(Team team) {
		this.team = team;
	}


	public Integer getDifferenzaPunti() {
		return differenzaPunti;
	}

	public void setDifferenzaPunti(Integer differenzaPunti) {
		this.differenzaPunti = differenzaPunti;
	}



	@Override
	public int compareTo(teamDifferenzaPunti o) {
		return this.differenzaPunti-	o.differenzaPunti; 
	
	} 
	
	

}
