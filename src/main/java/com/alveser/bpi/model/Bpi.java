package com.alveser.bpi.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Bpi implements Model{
	
	
	private Map <Date,Double> map = new HashMap <>();
	
	private String disclaimer;
	
	private TimeModel timeModel;

	public Map<Date, Double> getMap() {
		return map;
	}

	public void setMap(Map<Date, Double> map) {
		this.map = map;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public TimeModel getTimeModel() {
		return timeModel;
	}

	public void setTimeModel(TimeModel timeModel) {
		this.timeModel = timeModel;
	}

	@Override
	public String toString() {
		return "Bpi [map=" + map + ", disclaimer=" + disclaimer + ", timeModel=" + timeModel + "]";
	}
	
	
	
	
	
}
