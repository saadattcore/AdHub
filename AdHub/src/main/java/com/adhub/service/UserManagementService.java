package com.adhub.service;

import java.util.List;


import com.adhub.entity.UserEntity;



public interface UserManagementService {

	UserEntity getUser(Long userId);
	List<UserEntity> getUsers();
	UserEntity saveUser(UserEntity user);	
	void updateUser(Long userId, UserEntity user);
	void deleteUser(Long userId);
	
}
