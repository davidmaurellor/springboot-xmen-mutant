package com.dgmr.xmen.mutant.model.to;

public class StatsTo {
	
	private Boolean flMutant;
	private Long total;
	
	public StatsTo(Boolean flMutant, Long total ) {
		this.flMutant = flMutant;
		this.total = total;
	}
	public Boolean getFlMutant() {
		return flMutant;
	}
	public void setFlMutant(Boolean flMutant) {
		this.flMutant = flMutant;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
