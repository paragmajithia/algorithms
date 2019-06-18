package com.parag.techgig.nineteen.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main2_round2_4 {

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

		private Queue<Integer> phaseList = new PriorityQueue<Integer>(Collections.reverseOrder());
		private Queue<Integer> stateList = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		public boolean isSolved = true;

		public TestCase(int phase, int states, String[] phaseCond, String[] stateCond) {
			this.phases = phase;
			this.states = states;
			
			// Store phase condition
			int phaseSum = 0;
			for (int i=0; i < this.phases; i++) {
				int phaseNum = Integer.parseInt(phaseCond[i]);
				if (phaseNum > 0) {
					phaseList.add(phaseNum);
					phaseSum = phaseSum + phaseNum;
				}
				
			}
			
			// Store state condition
			int stateSum = 0;
			for (int i=0; i < this.states; i++) {
				int stateNum = Integer.parseInt(stateCond[i]);
				if (stateNum > 0) {
					stateList.add(stateNum);
					stateSum = stateSum + stateNum;
				}
			}
			
			if (phaseSum != stateSum) {
				this.isSolved = false;
			}

		}

		public void printOutput() {
			
			// Check if expert matrix can be created
			if (this.isSolved ) {
				checkExpertMatrix();
			}
				
			if (this.isSolved) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		
		public void checkExpertMatrix() {
			
			// Pop up phase from the list
			while (!phaseList.isEmpty()) {
				Integer phaseCond = phaseList.remove();
				
				// Check if there are states available to serve the phase
				if (stateList.size() < phaseCond) {
					this.isSolved = false;
					break;
				}
				
				// Remove the states from queue and reinsert after decrementing it
				List<Integer> newStateList = new ArrayList<Integer>();
				for (int i =0; i < phaseCond; i++) {
					int newCond = stateList.remove() - 1;
					if (newCond > 0) {
						newStateList.add(newCond);
					}
				}
				stateList.addAll(newStateList);
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
			String[] arr2 = reader.readLine().split(" ");
			String[] arr3 = reader.readLine().split(" ");
			
			testcases.add(new TestCase(phases, states, arr2, arr3));
		}
	}

}
