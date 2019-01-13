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

import com.depi.entity.Thesis;
import com.depi.service.ThesisService;

@RestController
@RequestMapping("/api/thesis")
public class ThesisController {
	
	@Autowired
	private ThesisService thesisService;
	
	@GetMapping("/")
	public ResponseEntity<List<Thesis>> getAllThesis() {
		return new ResponseEntity<>(thesisService.getAllThesis(), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Thesis> createThesis(@Valid @RequestBody Thesis thesis, BindingResult result) {
		if(result.hasErrors()) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(thesisService.saveThesis(thesis), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Thesis> getThesisById(@PathVariable(value="id") Long thesisId) {
		return new ResponseEntity<>(thesisService.getThesisById(thesisId), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Thesis> updateThesis(@PathVariable(value="id") Long thesisId, @Valid @RequestBody Thesis thesisDetails) {
		return new ResponseEntity<>(thesisService.getThesisById(thesisId), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteThesis(@PathVariable(value="id") Long thesisId) {
		return thesisService.delete(thesisId);
	}
}
