package com.adhub.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.adhub.entity.UserEntity;

import jakarta.transaction.Transactional;

@Repository
public class UserManagementDAOImpl implements UserManagementDAO {

	private final SessionFactory sessionFactory;

	public UserManagementDAOImpl(SessionFactory sessionFactory) {
	 this.sessionFactory = sessionFactory;
	 }
	
	
	@Transactional
	@Override
	public UserEntity saveUser(UserEntity user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		return user;		
	}


	@Transactional
	@Override
	public UserEntity getUser(Long userId) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity dbUser = session.find(UserEntity.class, userId);
		return dbUser;
	}

	
	@Transactional
	@Override
	public List<UserEntity> getUsers() {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<UserEntity> dbUsers = session.createQuery("from UserEntity",UserEntity.class).list();
		return dbUsers;
	}


	@Transactional
	@Override
	public void updateUser(Long userId, UserEntity user) {
		// TODO Auto-generated method stub
		
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity dbUser = session.find(UserEntity.class, userId);
		dbUser.setName(user.getName());
				
	}


	@Override
	@Transactional
	public void deleteUser(Long userId) {
		Session session = this.sessionFactory.getCurrentSession();
		UserEntity dbEntity = session.get(UserEntity.class,userId);
		session.remove(dbEntity);
	}

}
