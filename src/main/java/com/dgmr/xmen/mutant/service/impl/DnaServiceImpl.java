package com.dgmr.xmen.mutant.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dgmr.xmen.mutant.service.IDnaService;

@Service
public class DnaServiceImpl implements IDnaService{
	
	private final static String REGEX = "(\\w)\\1{3}";
	
	@Cacheable(cacheNames = "dnaValid", key = "#dna")
	public Boolean isValidDna(String[] dna) {
		
		String[] dnaInvalid= Arrays.stream(dna)
			.filter(str -> str.length() != dna.length || !str.matches("[ATCG]+"))
			.toArray(String[]::new);
		
		if(dnaInvalid.length>0)
			return true;
		else
			return false;
		
	}
	
	public Boolean isMutant(String[] dna) {
		
		Boolean isMutant = false;
		
		//SEGUNDA FUENTE COLUMNAS
	    Map<Integer, String> mapCol = new HashMap<>();
		IntStream.range(0, dna.length).forEach( i -> 
			IntStream.range(0, dna.length).forEach( 
				j -> mapCol.put(j, mapCol.getOrDefault(j, "")+String.valueOf(dna[i].charAt(j)))
		));
		
		//TERCERA FUENTE DIAGONALES PRINCIPALES
	    Map<Integer, String> mapDiag = new HashMap<>();
		IntStream.range(0, dna.length).forEach( i -> 
			IntStream.range(0+i, i<(dna.length-3)?(i+(dna.length-3)):dna.length).forEach( 
				j -> mapDiag.put(j-i, mapDiag.getOrDefault(j-i, "")+String.valueOf(dna[i].charAt(j)))
		));
		
		IntStream.range(1, dna.length).forEach( i -> 
			IntStream.range((dna.length-2)-i<0?0:(dna.length-2)-i, dna.length-i==1?1:dna.length-i).forEach( 
				j -> mapDiag.put(j+i-1, mapDiag.getOrDefault(j+i-1, "")+String.valueOf(dna[dna.length -i ].charAt(j)))
		));
		
		//CUARTA FUENTE DIAGONALES SECUNDARIAS
	    Map<Integer, String> mapDiagSec = new HashMap<>();
		IntStream.range(0, dna.length).forEach( i -> 
			IntStream.range(0+i, i<(dna.length-3)?(i+(dna.length-3)):dna.length).forEach( 
				j -> mapDiagSec.put(j-i, mapDiagSec.getOrDefault(j-i, "")+String.valueOf(dna[dna.length - i-1].charAt(j)))
		));
		
		IntStream.range(1, dna.length).forEach( i -> 
		IntStream.range((dna.length-2)-i<0?0:(dna.length-2)-i, dna.length-i==1?1:dna.length-i).forEach( 
			j -> mapDiagSec.put(j+i-1, mapDiagSec.getOrDefault(j+i-1, "")+String.valueOf(dna[i-1].charAt(j)))
		));
	
		
		String[] matches = Pattern
				.compile(REGEX)
				.matcher(
						String.format("%s,%s,%s,%s",
							String.join(",", dna) ,  				//Filas
							String.join(",", mapCol.values()), 		//Columnas
							String.join(",", mapDiag.values()),		//Diagonales Principales
							String.join(",", mapDiagSec.values())	//Diagonales Secundarias
						)	
				)
				.results()
                .map(MatchResult::group)
                .toArray(String[]::new);
		
		if(matches.length > 1) {
			isMutant = true;
		}
		
		return isMutant;
	}
}
