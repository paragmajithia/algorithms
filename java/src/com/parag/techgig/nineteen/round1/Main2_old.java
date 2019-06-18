package com.parag.techgig.nineteen.round1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2_old {
	public static void main(String[] args) throws IOException {
		readinput();
		processHomes();
	}

	public static int noOfTestcases;
	public static List<Home> homes = new ArrayList<Home>();

	public static class Home {
		public int noOftickets;
		public List<Integer> tickets = new ArrayList<Integer>();

		@Override
		public String toString() {
			return String.format("count:%s, strengths:%s, vilian:%s", noOftickets, tickets);
		}

		public void processTickets() {

			// List with previous included
			List<Integer> includePrevList = new ArrayList<Integer>();
			int inPrevious = tickets.get(0);
			includePrevList.add(inPrevious);

			// List with previous excluded
			int exPrevious = Integer.MIN_VALUE;
			List<Integer> excludePrevList = new ArrayList<Integer>();
			List<Integer> temp;
			int temp2;

			for (int count = 1; count < tickets.size(); count++) {

				// Get ticket number
				int ticket = tickets.get(count);

				// Scenario -- 0 ticket found Or ticket not increasing sum
				if (ticket == 0 || ((inPrevious + ticket) <= inPrevious) && inPrevious > 0) {
					// Reset to max value
					if (inPrevious > exPrevious) {
						exPrevious = inPrevious;
						excludePrevList = new ArrayList<Integer>(includePrevList);
					}
					continue;
				}

				// Exchange the list
				temp = includePrevList;
				includePrevList = excludePrevList;
				excludePrevList = temp;

				// Store new exclude & include
				temp2 = inPrevious;
				inPrevious = exPrevious;
				exPrevious = temp2;

				// Scenario -- including current ticket
				if (inPrevious < 0 && ticket > inPrevious) {
					inPrevious = ticket;
					includePrevList.clear();
					includePrevList.add(ticket);
				} else if ((inPrevious + ticket) > inPrevious) {
					inPrevious = inPrevious + ticket;
					includePrevList.add(ticket);
				}
			}

			// Print output
			if (inPrevious > exPrevious) {
				printOutput(includePrevList);
			} else if (inPrevious < exPrevious) {
				printOutput(excludePrevList);
			} else {
				int inclCount = includePrevList.size() - 1;
				int exclCount = excludePrevList.size() - 1;
				boolean isPrinted = false;
				for (int i = 0; i < Math.min(includePrevList.size(), excludePrevList.size()); i++) {
					if (includePrevList.get(inclCount) > excludePrevList.get(exclCount)) {
						printOutput(includePrevList);
						isPrinted = true;
						break;
					} else if (includePrevList.get(inclCount) < excludePrevList.get(exclCount)) {
						printOutput(excludePrevList);
						isPrinted = true;
						break;
					}
					inclCount--;
					exclCount--;
				}
				if (!isPrinted) {
					printOutput(includePrevList);
				}
			}
		}

		private void printOutput(List<Integer> intList) {
			for (int i = (intList.size() - 1); i >= 0; i--) {
				System.out.print(intList.get(i));
			}
		}
	}

	public static void readinput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		noOfTestcases = Integer.parseInt(reader.readLine());

		for (int testCount = 0; testCount < noOfTestcases; testCount++) {
			Home home = new Home();
			home.noOftickets = Integer.parseInt(reader.readLine());

			// Get ticket details
			String[] tickets = reader.readLine().trim().split("\\s");
			for (String ticketStr : tickets) {
				home.tickets.add(Integer.parseInt(ticketStr));
			}

			homes.add(home);
		}
	}

	private static void processHomes() {
		for (Home home : homes) {
			home.processTickets();
			System.out.println();
		}
	}
}
