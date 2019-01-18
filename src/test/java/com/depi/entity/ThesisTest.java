package com.depi.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.depi.exception.ResourceNotFoundException;
import com.depi.service.ThesisService;

@SpringBootTest
public class ThesisTest extends AbstractTestNGSpringContextTests{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThesisTest.class);
	
	@Autowired
	private ThesisService thesisService;
	
	private final String THESIS_TITLE = "Estudio de la Máquina Virtual en Java 11";
	private final String NEW_THESIS_TITLE = "Estudio de la Máquina Virtual en Java 10";
	private Long thesisGeneratedId;
	
	@Test(priority = 1)
	public void createThesisTest() {
		Thesis thesis = new Thesis();
		thesis.setTitle(this.THESIS_TITLE);
		
		this.thesisService.saveThesis(thesis);
		this.thesisGeneratedId = thesis.getId();
		
		assertThat(thesis.getId()).isNotNull();
	}
	
	@Test(priority = 2)
	public void listThesisTest() {
		List<Thesis> thesisList = this.thesisService.getAllThesis();
		LOGGER.info("Thesis list size -> {}", thesisList.size());
		assertThat(thesisList.size()).isGreaterThan(0);
	}
	
	@Test(priority = 3)
	public void updateThesis() {
		Thesis thesis = thesisService.getThesisById(this.thesisGeneratedId);
		thesis.setTitle(this.NEW_THESIS_TITLE);
		thesisService.saveThesis(thesis);
		
		Thesis updatedThesis = thesisService.getThesisById(this.thesisGeneratedId);
		assertThat(updatedThesis.getTitle()).isEqualTo(this.NEW_THESIS_TITLE);
	}
	
	@Test(priority = 5,
			expectedExceptions = {ResourceNotFoundException.class})
	public void deleteThesisTest() {
		this.thesisService.delete(this.thesisGeneratedId);
		this.thesisService.getThesisById(this.thesisGeneratedId);
	}
}
