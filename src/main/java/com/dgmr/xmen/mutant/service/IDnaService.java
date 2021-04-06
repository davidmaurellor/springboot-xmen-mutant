package com.dgmr.xmen.mutant.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface IDnaService {
	
	public Boolean isMutant(String[] dna)  throws NoSuchAlgorithmException, UnsupportedEncodingException ;
	public Boolean isValidDna(String[] dna) ;
}
