package br.com.pre.dojo.modelo;

import java.util.HashMap;
import java.util.Map;

public class Game {
	
	private String id;
	
	private Map<String,Player> rankingPlayers= new HashMap<String, Player>();

	public Map<String,Player> getRankingPlayers() {
		return rankingPlayers;
	}

	public void setRankingPlayers(Map<String,Player> rankingPlayers) {
		this.rankingPlayers = rankingPlayers;
	}
	public Game(String id){
		this.id=id;
	}

	public String getId() {
		return id;
	}
	
}
