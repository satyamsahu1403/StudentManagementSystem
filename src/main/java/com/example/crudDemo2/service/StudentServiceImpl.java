package com.example.crudDemo2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudDemo2.dao.StudentDAO;
import com.example.crudDemo2.entity.Student;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{

	private StudentDAO studentDAO;
	
	@Autowired
	public StudentServiceImpl(StudentDAO theStudentDAO) {
		super();
		studentDAO = theStudentDAO;
	}

	@Override
	public List<Student> findAll() {
		return studentDAO.findAll();
	}

	@Override
	public Student findById(int theId) {
		return studentDAO.findById(theId);
	}

	@Transactional
	@Override
	public Student save(Student theStudent) {
		return studentDAO.save(theStudent);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		studentDAO.deleteById(theId);
	}

}
