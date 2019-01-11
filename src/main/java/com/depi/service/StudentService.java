package com.depi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.depi.entity.Student;
import com.depi.exception.ResourceNotFoundException;
import com.depi.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public Student saveStudent(Student student) {
		return this.studentRepository.save(student);
	}
	
	public void delete(Long studentId) {
		Student student = this.studentRepository.findById(studentId).orElse(null);
		this.studentRepository.delete(student);
	}
	
	public List<Student> getAllStudents() {
		List<Student> studentsList = new ArrayList<>(); 
		this.studentRepository.findAll().forEach(studentsList::add);
		
		return studentsList;
	}
	
	public Student getStudentById(Long studentId) {
		return this.studentRepository.findById(studentId)
					.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
	}
}
