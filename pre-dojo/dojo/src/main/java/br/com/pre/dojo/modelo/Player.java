package br.com.pre.dojo.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Comparable<Player> {

	private int deads;
	private int murders;
	private String name;
	private Map<String, Gun> favouritesGuns=new HashMap<String, Gun>();
	private List <Date> logDeads= new ArrayList<Date>();
	private List <Date> logMulders= new ArrayList<Date>();

	public List<Date> getLogDeads() {
		return logDeads;
	}

	public List<Date> getLogMulders() {
		return logMulders;
	}

	public int getDeads() {
		return deads;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addDeads() {
		++this.deads;
	}

	public int getMurders() {
		return murders;
	}

	public void addMurders() {
		++this.murders;
	}

	public void addLogDeads(Date datetime){
		logDeads.add(datetime);
	}
	
	public void addLogMulders(Date datetime){
		logMulders.add(datetime);
	}
	
	public Map<String, Gun> getFavouritesGuns() {
		return favouritesGuns;
	}

	public void setFavouritesGuns(Map<String, Gun> favouritesGuns) {
		this.favouritesGuns = favouritesGuns;
	}
	

	public void calculate() {

	}

	public int compareTo(Player o) {
		int number = ((murders * 10 - deads) - (o.murders * 10 - o.deads));

		if (number > 0) {
			return 1;
		} else {
			return -1;
		}

		
	}
}
