package com.dgmr.xmen.mutant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_analisis")
public class Analisis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_analisis")
	private Long idAnalisis;
	
	@Column(name="ds_dna_code")
	private String dsDnaCode;
	
	@Column(name="ds_dna")
	private String dsDna;

	@Column(name="fl_mutant")
	private Boolean flMutant;
	
	public Long getIdAnalisis() {
		return idAnalisis;
	}

	public void setIdAnalisis(Long idAnalisis) {
		this.idAnalisis = idAnalisis;
	}

	public String getDsDnaCode() {
		return dsDnaCode;
	}

	public void setDsDnaCode(String dsDnaCode) {
		this.dsDnaCode = dsDnaCode;
	}

	public String getDsDna() {
		return dsDna;
	}

	public void setDsDna(String dsDna) {
		this.dsDna = dsDna;
	}

	public Boolean getFlMutant() {
		return flMutant;
	}

	public void setFlMutant(Boolean flMutant) {
		this.flMutant = flMutant;
	}
	
	
}
