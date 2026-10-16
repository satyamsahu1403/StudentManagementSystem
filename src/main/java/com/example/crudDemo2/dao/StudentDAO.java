package com.example.crudDemo2.dao;

import java.util.List;

import com.example.crudDemo2.entity.Student;

public interface StudentDAO {
	List<Student> findAll();
	
	Student findById(int theId);
	
	Student save(Student theStudent);
	
	void deleteById(int theId);
}
