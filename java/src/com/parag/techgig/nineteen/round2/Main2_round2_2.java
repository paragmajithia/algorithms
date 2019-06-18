package com.parag.techgig.nineteen.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2_round2_2 {

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

		public int[] phaseCond;
		public int[] stateCond;

		public boolean isSolved = true;

		public TestCase(int phase, int states, int[] phaseCond, int[] stateCond, boolean isMatch) {
			
			if (!isMatch) {
				this.isSolved = false;
				return;
			}
			
			this.phases = phase;
			this.states = states;
			this.phaseCond = phaseCond;
			this.stateCond = stateCond;

			for (int i = 0; i < phases; i++) {
				// if current phase is pending
				if (phaseCond[i] > 0) {
					// Loop through state and decrement it
					for (int j = 0; j < states; j++) {
						// if state is available, then decrement it
						if (stateCond[j] > 0 && phaseCond[i] > 0) {
							phaseCond[i] = phaseCond[i] - 1;
							stateCond[j] = stateCond[j] - 1;
						}

						if (phaseCond[i] <=0 && i != (phases - 1)) {
							break;
						}
						
						// if its last phase then check if state condition is met
						if (i == (phases - 1) && stateCond[j] > 0) {
							this.isSolved = false;
							break;
						}
					}
				}

				// Current phase loop over
				if (phaseCond[i] > 0 || !this.isSolved) {
					this.isSolved = false;
					break;
				}
			}

		}

		public void printOutput() {
			if (this.isSolved) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
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
			int[] phaseCond = new int[phases];
			int[] stateCond = new int[states];

			// Read phases condition
			String[] arr2 = reader.readLine().split(" ");
			String[] arr3 = reader.readLine().split(" ");
			
			int phaseSum = 0;
			for (int j = 0; j < phases; j++) {
				phaseCond[j] = Integer.parseInt(arr2[j]);
				phaseSum = phaseSum + phaseCond[j];
			}

			// Read state conditions
			int stateSum = 0;
			
			for (int j = 0; j < states; j++) {
				stateCond[j] = Integer.parseInt(arr3[j]);
				stateSum = stateSum + stateCond[j];
			}

			boolean isMatch = true;
			if (stateSum != phaseSum) {
				isMatch = false;
			}
			testcases.add(new TestCase(phases, states, phaseCond, stateCond, isMatch));
		}
	}

}
