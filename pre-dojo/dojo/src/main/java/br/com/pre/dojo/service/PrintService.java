package br.com.pre.dojo.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import javax.swing.JTextArea;

import br.com.pre.dojo.modelo.Game;
import br.com.pre.dojo.modelo.Gun;
import br.com.pre.dojo.modelo.Player;
import br.com.pre.dojo.utils.Utils;

public class PrintService {

	private static final String ESCAPE = "\n";
	private String fileName;
	private JTextArea textArea;

	public PrintService(String fileName, JTextArea textArea) {
		this.fileName = fileName;
		this.textArea = textArea;
	}

	public void printResult() {
		boolean ok = false;
		RankingService service = new RankingService();
		try {

			ok = service.read(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			print("                                                        ARQUIVO INVALIDO/INEXISTENTE!");
			return;
		}

		Queue<Game> games = service.getGames();
		if (games.size() == 0 || !ok) {
			print("                                                        ARQUIVO INVALIDO/INEXISTENTE!");
			return;
		}
		print("Ranking por jogo");
		for (Game g : games) {
			print("");
			print("Ranking do jogo " + g.getId());
			print("---------------------------------------");
			print("");
			print("                                                        Ordem dos Jogadores ");
			print("");
			printBody(g);

		}

	}

	public void printBody(Game g) {
		Map<String, Player> players = g.getRankingPlayers();
		Set<Player> set = Utils.sortByValue(players);

		int i = 0;
		for (Player p : set) {
			boolean award = calculateTimeOfMulders(p.getLogMulders());
			StringBuilder s = new StringBuilder();
			s.append(p.getName() + " assassinou " + p.getMurders()
					+ " pessoa(s) ");
			if (award) {
				s.append(" em menos de 1 minuto e ganhou um award ");
			}
			if (p.getDeads() > 0) {
				s.append(", morreu " + p.getDeads() + " vez(es).");
			} else {
				s.append(" e tem um award pois não morreu na partida. ");

			}

			if (i == 0) {
				s.append(favoriteGun(p.getFavouritesGuns()));
			}
			print(s.toString());
			i++;
			// s.append(PrintService.ESCAPE);

		}

		print("");
	}

	public void print(String s) {
		textArea.append(s + PrintService.ESCAPE);
	}

	private String favoriteGun(Map<String, Gun> g) {

		Set<Gun> guns = Utils.sortByValue(g);
		Iterator<Gun> it = guns.iterator();
		String s = "";
		if (it.hasNext()) {
			Gun gun = it.next();
			s = " A pistola preferida foi " + gun.getName() + ".";

		}
		return s;

	}

	private boolean calculateTimeOfMulders(List<Date> mulders) {

		int size = mulders.size();
		if (size > 4) {
			int first = 0;
			int last = (mulders.size() - 1) - first;

			for (int i = 0; i < 5; i++) {
				Date date1 = mulders.get(i);
				for (int j = 5; j < size; j++) {
					Date dateL = mulders.get(j);
					long remain = (date1.getTime() - dateL.getTime()) / 1000;
					if (remain < 60) {
						return true;

					}
				}
			}

		}
		return false;
	}

}
