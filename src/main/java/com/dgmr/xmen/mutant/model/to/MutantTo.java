package com.dgmr.xmen.mutant.model.to;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MutantTo {
	
	@JsonProperty("dna")
	String[] dna;

	public String[] getMutantString() {
		return dna;
	}

	public void setMutantString(String[] dna) {
		this.dna = dna;
	} 
	
}
