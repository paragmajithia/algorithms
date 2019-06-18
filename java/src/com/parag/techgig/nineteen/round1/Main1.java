package com.parag.techgig.nineteen.round1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main1 {

	public static void main(String[] args) throws IOException {
		readinput();
		printWinOrLoose();
	}

	public static int noOfTestcases;
	public static List<Game> games = new ArrayList<Main1.Game>();

	public static class Game {
		public int noOfPlayers;
		public List<Integer> strengths = new ArrayList<Integer>();
		public List<Integer> villian = new ArrayList<Integer>();
		
		@Override
		public String toString() {
			return String.format("count:%s, strengths:%s, vilian:%s", noOfPlayers, strengths, villian);
		}
		
		public void printWinOrLoose() {
			Collections.sort(villian, Collections.reverseOrder());
			Collections.sort(strengths, Collections.reverseOrder());
			
			String out = "WIN";
			for (int count = (villian.size() - 1); count >= 0; count-- ) {
				if (strengths.get(count) <= villian.get(count)) {
					out = "LOSE";
					break;
				}
			}
			System.out.println(out);
		}

	}

	public static void readinput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		noOfTestcases = Integer.parseInt(reader.readLine());

		for (int testCount = 0; testCount < noOfTestcases; testCount++) {
			Game game = new Game();
			game.noOfPlayers = Integer.parseInt(reader.readLine());

			// Get villian details
			String[] villian = reader.readLine().trim().split("\\s");
			for (String villianStr : villian) {
				game.villian.add(Integer.parseInt(villianStr));
			}

			// Get player details
			String[] player = reader.readLine().split("\\s");
			for (String playerStr : player) {
				game.strengths.add(Integer.parseInt(playerStr));
			}

			games.add(game);

		}
	}
	
	public static void printWinOrLoose() {
		for (Game game : games) {
			game.printWinOrLoose();
		}
	}
}
