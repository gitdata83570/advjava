package com.election.dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.election.pojos.User;
import com.election.utils.DbUtils;

public class UserDaoImpl implements UserDao, AutoCloseable {
 private Connection connection = null;
 private String query = "";	

	public UserDaoImpl() throws SQLException {
		connection = DbUtils.getConnection();
	}

	@Override
	public void close() throws IOException {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User findByEmail(String email) throws Exception {
		query = "select * from users where email =?";
		 User user = null;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, email);
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next()) {
		    	user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),new java.util.Date(rs.getDate(6).getTime()),rs.getBoolean(7),rs.getString(8));          
		    }
		}		
		return user;
	}

	@Override
	public User findById(int id) throws Exception {
		query = "select * from users where   id =?";
		 User user = null;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    while(rs.next()) {
		    	user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),new java.util.Date(rs.getDate(6).getTime()),rs.getBoolean(7),rs.getString(8));          
		    }
		}		
		return user;
	}

	@Override
	public int save(User user) throws Exception {
		int insert = 0;
		 query = "insert into users(first_name,last_name,email,password,dob,status,role) values(?,?,?,?,?,?,?);";
		 try (PreparedStatement stmt = connection.prepareStatement(query)) {
				stmt.setString(1, user.getFirst_name());
				stmt.setString(2, user.getLast_name());
				stmt.setString(3, user.getEmail());
				stmt.setString(4, user.getPassword());
				stmt.setDate(5, new Date(user.getDob().getTime()));
				stmt.setBoolean(6, false);
				stmt.setString(7, user.getRole());
			 insert = stmt.executeUpdate();
			}
		 return insert;
	}

	@Override
	public int updateStatus(int userId, boolean voted) throws Exception {
		query = "UPDATE users SET status = ? WHERE id = ?";
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setBoolean(1, voted);
			stmt.setInt(2, userId);
		    result = stmt.executeUpdate();
		}		
		return result;
	}

	@Override
	public int updatePassword(int userId, String newPassword) throws Exception {
		query = "UPDATE users SET password = ? WHERE id = ?";
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, newPassword);
			stmt.setInt(2, userId);
		    result = stmt.executeUpdate();
		}		
		return result;
	}

	@Override
	public int deleteById(int id) throws Exception {
		query = "DELETE FROM users WHERE id = ?";
		int result = 0;
		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setInt(1, id);
		    result = stmt.executeUpdate();
		}		
		return result;
	}

	@Override
	public int update(User user) throws Exception {
	    int updatedRows = 0;
	    String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, dob = ?, status = ?, role = ? WHERE id = ?;";
	    try (PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setString(1, user.getFirst_name());
	        stmt.setString(2, user.getLast_name());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getPassword());
	        stmt.setDate(5, new Date(user.getDob().getTime()));
	        stmt.setBoolean(6, false);
	        stmt.setString(7, user.getRole());
	        stmt.setInt(8, user.getId());  // Assuming there's a getId() method in User class
	        
	        updatedRows = stmt.executeUpdate();
	    }
	    return updatedRows;
	}

}
