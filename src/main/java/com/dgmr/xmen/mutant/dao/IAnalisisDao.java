package com.dgmr.xmen.mutant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dgmr.xmen.mutant.model.Analisis;
import com.dgmr.xmen.mutant.model.to.StatsTo;

public interface IAnalisisDao extends CrudRepository<Analisis, Long>{

	@Query(value = "SELECT * FROM tb_analisis WHERE ds_dna_code = SHA1(?1) ", nativeQuery = true)
	public Analisis findAllByDsDnaCode(String code);
	
	@Query("SELECT new com.dgmr.mutant.api.model.to.StatsTo(a.flMutant, COUNT(a.flMutant)) "
			  + "FROM Analisis AS a GROUP BY a.flMutant ORDER BY a.flMutant DESC")
	public List<StatsTo> getStats();
	
	
}
