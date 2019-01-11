package com.depi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jury {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Professor president;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Professor secretary;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Professor vocal;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Professor alternateVocal;
	
	public Jury() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Professor getPresident() {
		return president;
	}

	public void setPresident(Professor president) {
		this.president = president;
	}

	public Professor getSecretary() {
		return secretary;
	}

	public void setSecretary(Professor secretary) {
		this.secretary = secretary;
	}

	public Professor getVocal() {
		return vocal;
	}

	public void setVocal(Professor vocal) {
		this.vocal = vocal;
	}

	public Professor getAlternateVocal() {
		return alternateVocal;
	}

	public void setAlternateVocal(Professor alternateVocal) {
		this.alternateVocal = alternateVocal;
	}
}
