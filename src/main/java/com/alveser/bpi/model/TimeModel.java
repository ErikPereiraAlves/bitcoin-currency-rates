package com.alveser.bpi.model;

public class TimeModel implements Model {
	
	//"time":{"updated":"Aug 21, 2019 00:03:00 UTC","updatedISO":"2019-08-21T00:03:00+00:00"}}

	private String updated;
	
	private String updatedISO;

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getUpdatedISO() {
		return updatedISO;
	}

	public void setUpdatedISO(String updatedISO) {
		this.updatedISO = updatedISO;
	}

	@Override
	public String toString() {
		return "TimeModel [updated=" + updated + ", updatedISO=" + updatedISO + "]";
	}
	
	
	
	

}
