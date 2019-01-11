package com.depi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.depi.entity.Student;
import com.depi.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public ResponseEntity<List<Student>> getAllUsers() {
		return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, BindingResult result){
		if(result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value="id") Long studentId){
		return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value="id") Long studentId, @Valid @RequestBody Student studentDetails) {
		return new ResponseEntity<>(studentService.updateStudent(studentId, studentDetails), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value="id") Long studentId) {
		return this.studentService.delete(studentId);
	}
}
