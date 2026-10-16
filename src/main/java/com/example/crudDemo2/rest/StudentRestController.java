package com.example.crudDemo2.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudDemo2.entity.Student;
import com.example.crudDemo2.service.StudentService;

import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private StudentService studentService;
	
	private JsonMapper jsonMapper;
	
	@Autowired
	public StudentRestController(StudentService theStudentService, JsonMapper theJasonMapper) {
		super();
		studentService = theStudentService;
		jsonMapper=theJasonMapper;
	}
	
	@GetMapping("/students")
	public List<Student> findAll()
	{
		return studentService.findAll();
	}
	
	@GetMapping("/students/{studentId}")
	public Student getEmployee(@PathVariable int studentId)
	{
		Student theStudent=studentService.findById(studentId);
		
		if(theStudent==null)
		{
			throw new RuntimeException("Student id not found- "+studentId);
		}
		return theStudent;
	}
	
	@PostMapping("/students")
	public Student addEmployee(@RequestBody Student theStudent) {
		theStudent.setId(0);
		
		Student dbStudent = studentService.save(theStudent);
		return dbStudent;
	}
	
	@PutMapping("/students")
	public Student updateEmployees(@RequestBody Student theStudent)
	{
		Student dbStudent = studentService.save(theStudent);
		
		return dbStudent;
	}
	
	@PatchMapping("/students/{studentId}")
	public Student patchEmployee(@PathVariable int studentId, @RequestBody Map<String, Object> patchPayload)
	{
		Student tempStudent= studentService.findById(studentId);
		
		if(tempStudent==null)
		{
			throw new RuntimeException("Student id not found- "+studentId);
		}
		if(patchPayload.containsKey("id"))
		{
			throw new RuntimeException("Student id not allowed in request body- "+studentId);
		}
		
		Student patchedStudent=jsonMapper.updateValue(tempStudent, patchPayload);
		
		Student dbStudent = studentService.save(patchedStudent);
		
		return dbStudent;
	}
	
	@DeleteMapping("/students/{studentId}")
	public String deleteEmployee(@PathVariable int studentId)
	{
		Student tempStudent = studentService.findById(studentId);
		
		if(tempStudent==null)
		{
			throw new RuntimeException("Student id not found- "+studentId);
		}
		
		studentService.deleteById(studentId);
		
		return "Deleted Student id - "+studentId;
	}
}
