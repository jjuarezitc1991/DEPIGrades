package com.depi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.depi.entity.Thesis;
import com.depi.exception.ResourceNotFoundException;
import com.depi.repository.ThesisRepository;

@Service
public class ThesisService {
	
	@Autowired
	private ThesisRepository thesisRepository;
	
	public List<Thesis> getAllThesis() {
		List<Thesis> thesisList = new ArrayList<>();
		this.thesisRepository.findAll().forEach(thesisList::add);
		return thesisList;
	}
	
	public Thesis saveThesis(Thesis thesis) {
		return this.thesisRepository.save(thesis);
	}
	
	public ResponseEntity<?> delete(Long thesisId) {
		Thesis thesis = this.thesisRepository.findById(thesisId).orElse(null);
		this.thesisRepository.delete(thesis);
		return ResponseEntity.ok().build();
	}
	
	@Transactional
	public Thesis getThesisById(Long thesisId) {
		return this.thesisRepository.findById(thesisId)
				.orElseThrow(() -> new ResourceNotFoundException("Thesis", "id", thesisId));
	}
	
	public Thesis updateThesis(Long thesisId, Thesis thesisDetails) {
		Thesis thesis = this.thesisRepository.findById(thesisId)
				.orElseThrow(() -> new ResourceNotFoundException("Thesis", "id", thesisId));
		
		thesis.setJury(thesisDetails.getJury());
		thesis.setStudent(thesisDetails.getStudent());
		thesis.setTitle(thesisDetails.getTitle());
		
		return this.thesisRepository.save(thesis);
	}
}
