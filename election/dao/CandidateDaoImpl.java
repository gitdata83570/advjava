package com.election.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.election.pojos.Candidate;
import com.election.utils.DbUtils;

public class CandidateDaoImpl implements CandidateDao {
    private Connection connection = null;
    private String query = "";

    public CandidateDaoImpl() throws SQLException {
        connection = DbUtils.getConnection();
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Candidate> findAll() throws Exception {
        List<Candidate> list = new ArrayList<>();
        query = "select * from candidates;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return list;
        }
    }

    @Override
    public List<Candidate> findByParty(String party) throws Exception {
        List<Candidate> list = new ArrayList<>();
        query = "select * from candidates where party = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, party);            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return list;
        }
    }

    @Override
    public List<Candidate> findAllOrderByVotesDesc() throws Exception {
        List<Candidate> list = new ArrayList<>();
        query = "select * from candidates order by votes desc;";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
            return list;
        }
    }

    @Override
    public int save(Candidate c) throws Exception {
        int insert = 0;
        query = "insert into candidates(name,party,votes) VALUES (?, ?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getParty());
            stmt.setInt(3, 0);
            insert = stmt.executeUpdate();
        }
        return insert;
    }

    @Override
    public int deleteById(int id) throws Exception {
        query = "DELETE FROM candidates WHERE id = ?";
        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
        }
        return result;
    }

    @Override
    public int update(Candidate c) throws Exception {
        query = "UPDATE candidates SET name = ?, party = ?, votes = ? WHERE id = ?";
        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, c.getName());
            stmt.setString(2, c.getParty());
            stmt.setInt(3, c.getVotes());
            stmt.setInt(4, c.getId());
            result = stmt.executeUpdate();
        }
        return result;
    }

    @Override
    public Candidate findById(int id) throws Exception {
        query = "select * from candidates where id =?";
        Candidate candidate = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                candidate = new Candidate(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        }        
        return candidate;
    }

    @Override
    public int incrementVotes(int id) throws Exception {
        query = "UPDATE candidates SET votes = votes + 1 WHERE id = ?";
        int result = 0;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
        }
        return result;
    }
}
