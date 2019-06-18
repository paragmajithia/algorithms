package com.parag.techgig.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2_round2 {

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

		public int[][] matrix;

		public TestCase(int phase, int states, int[] phaseCond, int[] stateCond) {
			this.phases = phase;
			this.states = states;
			this.phaseCond = phaseCond;
			this.stateCond = stateCond;
			this.matrix = new int[this.phases][this.states];
		}

		public void printOutput() {
			boolean isSolExists = recurse(0, 0, phaseCond, stateCond);
			if (isSolExists) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}

		public boolean recurse(int phase, int state, int[] remPhaseCond, int[] remStateCond) {

			// Next solution
			int nextPhase = phase;
			int nextState = state;
			if (state < (this.states - 1)) {
				nextState = nextState + 1;
			} else if (phase < (this.phases - 1)) {
				nextPhase = nextPhase + 1;
				nextState = 0;
			} else {
				nextPhase = -1;
			}
			
			// Save backup before changing array
			int[] origPhaseCond = new int[remPhaseCond.length];
			int[] origStateCond = new int[remStateCond.length];
			System.arraycopy(remPhaseCond, 0, origPhaseCond, 0, remPhaseCond.length);
			System.arraycopy(remStateCond, 0, origStateCond, 0, remStateCond.length);


			// if current phase / state can NOT be set to win then proceed to next
			if (remPhaseCond[phase] <= 0 || remStateCond[state] <= 0) {

				// If phase changes -- check if condition is met
				if (nextPhase != phase && remPhaseCond[phase] > 0) {
					return false;
				}

				// if state changes and if its last phase
				if (nextState != state && (phase + 1) == remPhaseCond.length && remStateCond[state] > 0) {
					return false;
				}

				if (nextPhase == -1) {
					return true;
				} else {
					return recurse(nextPhase, nextState, remPhaseCond, remStateCond);
				}

			}

			if (nextPhase == -1) {
				return false;
			}
			
			// Set current phase and check if solution exists
			remPhaseCond[phase] = remPhaseCond[phase] - 1;
			remStateCond[state] = remStateCond[state] - 1;
			
			// If phase changes -- check if condition is met
			if (nextPhase != phase && remPhaseCond[phase] > 0) {
				return false;
			}

			// if state changes and if its last phase
			if (nextState != state && (phase + 1) == remPhaseCond.length && remStateCond[state] > 0) {
				return false;
			}
			
			boolean isSolExists = recurse(nextPhase, nextState, remPhaseCond, remStateCond);

			if (isSolExists) {
				return true;
			} else {
				
				// If phase changes -- check if condition is met
				if (nextPhase != phase && origPhaseCond[phase] > 0) {
					return false;
				}

				// if state changes and if its last phase
				if (nextState != state && (phase + 1) == origPhaseCond.length && origStateCond[state] > 0) {
					return false;
				}

				// Try without setting current selection
				return recurse(nextPhase, nextState, origPhaseCond, origStateCond);
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
			for (int j = 0; j < phases; j++) {
				phaseCond[j] = Integer.parseInt(arr2[j]);
			}

			String[] arr3 = reader.readLine().split(" ");
			for (int j = 0; j < states; j++) {
				stateCond[j] = Integer.parseInt(arr3[j]);
			}

			testcases.add(new TestCase(phases, states, phaseCond, stateCond));
		}
	}

}
