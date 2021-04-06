package com.dgmr.xmen.mutant.service;

import java.util.List;

import com.dgmr.xmen.mutant.model.Analisis;
import com.dgmr.xmen.mutant.model.to.StatsTo;

public interface IAnalisisService {
	public Analisis getDnaAnalisis( String dna );
	
	public void saveAnalisis( Analisis analisis );
	
	public List<StatsTo> getStats();
}
