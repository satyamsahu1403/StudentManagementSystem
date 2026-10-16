package com.example.crudDemo2.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.crudDemo2.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOJpaImpl implements StudentDAO {

	private EntityManager entityManager;
	
	
	@Autowired
	public StudentDAOJpaImpl(EntityManager theEntityManager) {
		super();
		entityManager = theEntityManager;
	}



	@Override
	public List<Student> findAll() {
		TypedQuery<Student> theQuery=entityManager.createQuery("from Student",Student.class);
		
		List<Student> students=theQuery.getResultList();
		
		return students;
	}

	@Override
	public Student findById(int theId) {
		Student theStudent=entityManager.find(Student.class,theId);
		return theStudent;
	}



	@Override
	public Student save(Student theStudent) {
		Student dbStudent=entityManager.merge(theStudent);
		return dbStudent;
	}



	@Override
	public void deleteById(int theId) {
		Student theEmployee=entityManager.find(Student.class, theId);
		entityManager.remove(theEmployee);
		
	}
	
}
