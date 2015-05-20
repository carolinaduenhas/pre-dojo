package br.com.pre.dojo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

import br.com.pre.dojo.modelo.Game;
import br.com.pre.dojo.modelo.Gun;
import br.com.pre.dojo.modelo.Player;

public class RankingService {
	Queue<Game> games = new LinkedList<Game>();

	private Game currentGame = null;
	private static final String START = "New match ";
	private static final String END = " has started";
	private static final String NO_SPACE = "";
	private static final String KEY_WORD = " using";
	private static final String KILLED = " killed";
	private static final String GAME_OVER = "has ended";

	public boolean read(String fileName) throws ParseException,
			FileNotFoundException {
		boolean ok = false;

		File f = new File(fileName);
		if (!f.exists()) {
			return false;
		}
		FileReader file = new FileReader(fileName);

		Scanner scnr = new Scanner(file);

		while (scnr.hasNextLine()) {
			String line = scnr.nextLine();
			if (!line.equals("")) {
				startCalculation(line);
			}

		}
		ok = true;
		scnr.close();

		return ok;

	}

	private void startCalculation(String line) throws ParseException {

		if (line.contains(RankingService.START)) {
			String gameId = (line.substring(22, line.length()).replaceAll(
					RankingService.START, RankingService.NO_SPACE).replaceAll(
					RankingService.END, RankingService.NO_SPACE));

			currentGame = new Game(gameId);

		} else {
			calculateKillerData(line);
		}

	}

	private void calculateKillerData(String line) throws ParseException {

		if (line.contains(RankingService.KEY_WORD)) {
			String time = line.substring(0, 19);
			String validInfo = (line.substring(22, line.length()).replaceAll(
					RankingService.KILLED, RankingService.NO_SPACE).replaceAll(
					RankingService.KEY_WORD, RankingService.NO_SPACE));
			String valids[] = validInfo.split(" ");
			Map<String, Player> players = currentGame.getRankingPlayers();
			players = calculateMurder(valids[0], valids[2], players, time);
			players = calculateDead(valids[1], players, time);

		} else if (line.contains(RankingService.GAME_OVER)) {
			games.add(currentGame);

		}
	}

	private Map<String, Player> calculateMurder(String murder, String sgun,
			Map<String, Player> players, String datetime) throws ParseException {
		Player player = currentGame.getRankingPlayers().get(murder);
		players.remove(murder);
		if (player == null) {
			player = new Player();

		}
		player.setName(murder);
		player.addMurders();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		Date date = simpleDateFormat.parse(datetime);
		player.addLogMulders(date);
		Map<String, Gun> guns = player.getFavouritesGuns();
		Gun gun = guns.get(sgun);
		guns.remove(sgun);
		if (gun == null) {
			gun = new Gun();
		}
		gun.setName(sgun);
		gun.add();
		guns.put(sgun, gun);
		player.setFavouritesGuns(guns);
		players.put(murder, player);
		return players;
	}

	public Queue<Game> getGames() {
		return games;
	}

	private Map<String, Player> calculateDead(String dead,
			Map<String, Player> players, String datetime) throws ParseException {
		Player player = currentGame.getRankingPlayers().get(dead);
		players.remove(dead);
		if (player == null) {
			player = new Player();

		}
		player.setName(dead);
		player.addDeads();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM/dd/yyyy HH:mm:ss");
		Date date = simpleDateFormat.parse(datetime);

		player.addLogDeads(date);
		players.put(dead, player);
		return players;
	}

}
