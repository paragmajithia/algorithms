package com.parag.techgig.nineteen.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Main2_round2_7 {

	public static void main(String[] args) throws IOException {

		// Read input
		readinput();

		// Process each test case
		for (TestCase testcase : testcases) {
			testcase.printOutput();
		}
	}

	public static int numberofTestCases;
	public static List<TestCase> testcases = new ArrayList<TestCase>();

	public static class TestCase {
		public int phases;
		public int states;

		private String phaseCond;
		private List<Integer> stateList = new LinkedList<Integer>();

		public boolean isSolved = true;

		public TestCase(int phase, int states, String phaseCond, String stateCond) {
			this.phases = phase;
			this.states = states;
			this.phaseCond = phaseCond;

			// Store state list
			int startPos = 0;
			while (startPos != -1 && startPos <= stateCond.length()) {
				int endPos = stateCond.indexOf(" ", startPos);
				if (endPos < 0) {
					endPos = stateCond.length();
				}
				Integer stateCondInt = Integer.parseInt(stateCond.substring(startPos, endPos));
				startPos = endPos + 1;
				
				if (stateCondInt > 0) {
					stateList.add(stateCondInt);
				}
			}
			
			Collections.sort(stateList, Collections.reverseOrder());
		}

		public void printOutput() {

			// Check if expert matrix can be created
			checkExpertMatrix();

			if (this.isSolved) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

		public void checkExpertMatrix() {

			int startPos = 0;

			while (startPos != -1 && startPos <= this.phaseCond.length()) {
				int endPos = this.phaseCond.indexOf(" ", startPos);
				if (endPos < 0) {
					endPos = this.phaseCond.length();
				}
				Integer phaseCond = Integer.parseInt(this.phaseCond.substring(startPos, endPos));
				startPos = endPos + 1;

				// Check if there are states available to serve the phase
				if (stateList.size() < phaseCond) {
					this.isSolved = false;
					break;
				}

				// Remove the states from queue if its no longer available
				ListIterator<Integer> iterator = stateList.listIterator();
				int lastCount = 0;
				for (int i = 0; i < phaseCond; i++) {
					int newCond = iterator.next() - 1;
					iterator.set(newCond);
					if (newCond <= 0) {
						iterator.remove();
					} else {
						lastCount++;
					}
				}
				
				// Resort the sublist if required
				int startcount = lastCount-1;
				if (lastCount < stateList.size() && stateList.get(lastCount) > stateList.get(startcount)) {
					int updatedNum = stateList.get(startcount);
					int otherNum = stateList.get(lastCount);
					
					// Get start and end pos for sort					
					while ((lastCount+1) < stateList.size() && stateList.get(lastCount+1) == otherNum) {
						lastCount++;
					}
					while ((startcount-1) >=0 && stateList.get(startcount-1) == updatedNum) {
						startcount--;
					}
					Collections.sort(stateList.subList(startcount, (lastCount+1)), Collections.reverseOrder());
				}
			}

			// If state condition is not satisfied then break
			if (!stateList.isEmpty()) {
				this.isSolved = false;
			}
		}

	}

	public static void readinput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		numberofTestCases = Integer.parseInt(reader.readLine());

		for (int i = 0; i < numberofTestCases; i++) {

			// Read phase and states
			String[] arr1 = reader.readLine().split(" ");
			int phases = Integer.parseInt(arr1[0]);
			int states = Integer.parseInt(arr1[1]);

			// Read phases condition
			String arr2 = reader.readLine();
			String arr3 = reader.readLine();

			testcases.add(new TestCase(phases, states, arr2.trim(), arr3.trim()));
		}
	}

}
