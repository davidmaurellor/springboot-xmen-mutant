package com.dgmr.xmen.mutant.http;

import org.springframework.web.bind.annotation.RestController;

import com.dgmr.xmen.mutant.model.Analisis;
import com.dgmr.xmen.mutant.model.to.MutantTo;
import com.dgmr.xmen.mutant.model.to.StatsTo;
import com.dgmr.xmen.mutant.service.IAnalisisService;
import com.dgmr.xmen.mutant.service.IDnaService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MutantController {
	
	@Autowired
	private IDnaService mutantService;
	
	@Autowired
	private IAnalisisService analisisService;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@RequestMapping(value = {"/stats","/stats/"}, method = RequestMethod.GET)
	public ResponseEntity<JsonNode> getStats() throws IOException{
		
		List<StatsTo> stats = analisisService.getStats();
		
		Map<String, Long> map = new HashMap<>();
		map.put("count_mutant_dna", 0L);
		map.put("count_human_dna", 0L);
		
		if(stats == null)
			return ResponseEntity
				.status(HttpStatus.PRECONDITION_FAILED)
				.body(new ObjectMapper().readTree("{\"count_mutant_dna\":0, \"count_human_dna\":0: \"ratio\":0}"));
		else {
			for (StatsTo s:stats) {
				if(s.getFlMutant())
					map.put("count_mutant_dna", s.getTotal());
				else
					map.put("count_human_dna", s.getTotal());
			}
		}
		Float ratio = (map.get("count_human_dna") == 0L)?-1L:(float) map.get("count_mutant_dna") / map.get("count_human_dna");
		
			return ResponseEntity
					.status(HttpStatus.PRECONDITION_FAILED)
					.body(new ObjectMapper().readTree(
							String.format(
									"{\"count_mutant_dna\":%d , \"count_human_dna\":%d, \"ratio\": %s }", 
									map.get("count_mutant_dna"), 
									map.get("count_human_dna"), 
									ratio.toString()
								)
							)
						);
			
		
	}
	
	@RequestMapping(value = {"/mutant","/mutant/"}, method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> isMutant(@RequestBody String dna)  {
		Map<String, String> message = new HashMap<>();
		try { 
			MutantTo mutant = objectMapper.readValue(dna, MutantTo.class);
			
			if(mutantService.isValidDna(mutant.getMutantString())) {
				message.put("message", "DNA inválido para análisis, solo cadenas con (A,T,C,G) y matrices NxN, por favor compruebe su estructura!");
				return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);
			}
			else {
				String dnaString = String.join(",", mutant.getMutantString());
				
				Analisis analisis = analisisService.getDnaAnalisis(dnaString);
				
				if(analisis == null) {
					analisis = new Analisis();
					analisis.setDsDna(dnaString);
					analisis.setFlMutant(mutantService.isMutant(mutant.getMutantString()));
					analisisService.saveAnalisis(analisis);
				}
				
				if(analisis.getFlMutant()) {
					message.put("message", "Es Muntante !");
					return ResponseEntity.ok(message);
				}
				else {
					message.put("message", "No es Muntante !");
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
				}
			}
		}
		catch (Exception e) {
			message.put("message", "Error in structire of DNA!");
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(message);

		}
	}
}
