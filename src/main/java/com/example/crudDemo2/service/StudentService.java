package com.example.crudDemo2.service;

import java.util.List;

import com.example.crudDemo2.entity.Student;

public interface StudentService {
	
	List<Student> findAll();
	
	Student findById(int theId);
	
	Student save(Student theStudent);
	
	void deleteById(int theId);
}
