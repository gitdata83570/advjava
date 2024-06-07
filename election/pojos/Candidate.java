package com.election.pojos;

public class Candidate {
	//candidates(id int primary key auto_increment, name varchar(20) unique,party varchar(20), votes int);
	private int id ,votes;
	private String name,party;
	public Candidate(int id,String name,String party, int votes) {
		this.id = id;
		this.votes = votes;
		this.name = name;
		this.party = party;
	}
	public Candidate(String name,String party, int votes) {
		this.votes = votes;
		this.name = name;
		this.party = party;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	@Override
	public String toString() {
		return "Candidates [id=" + id + ", votes=" + votes + ", name=" + name + ", party=" + party + "]";
	}
	
}
