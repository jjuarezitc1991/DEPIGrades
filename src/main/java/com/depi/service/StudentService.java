package com.depi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.depi.entity.Student;
import com.depi.entity.Thesis;
import com.depi.exception.ResourceNotFoundException;
import com.depi.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ThesisService thesisService;;
	
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

	public Student assignThesis(Long studentId, Long thesisId) {
		Student student = getStudentById(studentId);
		Thesis thesis = thesisService.getThesisById(thesisId);
		
		student.setThesis(thesis);
		thesis.setStudent(student);
		
		return saveStudent(student);
	}
}
