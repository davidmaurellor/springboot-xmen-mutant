package com.dgmr.xmen.mutant.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dgmr.xmen.mutant.dao.IAnalisisDao;
import com.dgmr.xmen.mutant.model.Analisis;
import com.dgmr.xmen.mutant.model.to.StatsTo;
import com.dgmr.xmen.mutant.service.IAnalisisService;

@Service
public class AnalisisServiceImpl implements IAnalisisService{

	@Autowired
	private IAnalisisDao analisisDao;
	
	@Transactional
	@Cacheable(cacheNames = "dnaData", key = "#dna")
	public Analisis getDnaAnalisis( String dna ){
		return analisisDao.findAllByDsDnaCode(dna);
	}
	
	@Transactional
	public void saveAnalisis( Analisis analisis ) {
		analisisDao.save(analisis);
	}

	@Transactional
	public List<StatsTo> getStats(){
		return analisisDao.getStats();
	}
}
