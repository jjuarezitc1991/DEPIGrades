package com.depi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<?> delete(Long studentId) {
		Student student = this.studentRepository.findById(studentId).orElse(null);
		this.studentRepository.delete(student);
		return ResponseEntity.ok().build();
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
	
	public Student updateStudent(Long studentId, Student studentDetails) {
		Student student = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentDetails));
		student.setName(studentDetails.getName());
		student.setLastName(studentDetails.getLastName());
		student.setStudentNumber(studentDetails.getStudentNumber());
		student.setThesis(studentDetails.getThesis());
		
		return this.studentRepository.save(student);
	}
}
