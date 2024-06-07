package com.election.dao;

import com.election.pojos.User;

public interface UserDao { 
     User findByEmail(String email) throws Exception; 
    User findById(int id) throws Exception; 
     int save(User user) throws Exception; 
     int updateStatus(int userId, boolean voted) throws Exception; 
     int updatePassword(int userId, String newPassword) throws Exception; 
     int deleteById(int id) throws Exception; 
     int update(User user) throws Exception;
	//User signIn(String email, String Password) throws Exception;
}
