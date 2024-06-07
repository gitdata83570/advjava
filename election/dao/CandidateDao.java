package com.election.dao;

import java.io.Closeable;
import java.util.List;

import com.election.pojos.Candidate;

public interface CandidateDao extends AutoCloseable {
	     List<Candidate> findAll() throws Exception; 
	     List<Candidate> findByParty(String party) throws Exception; 
	    public List<Candidate> findAllOrderByVotesDesc() throws Exception; 
	    public int save(Candidate c) throws Exception; 
	    public int deleteById(int id) throws Exception; 
	    public int update(Candidate c) throws Exception; 
	    public Candidate findById(int id) throws Exception; 
	    public int incrementVotes(int id) throws Exception;
}
