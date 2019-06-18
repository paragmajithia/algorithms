package com.parag.techgig.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2_round2_3 {

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
		public Integer[] stateCond;

		public boolean isSolved = true;

		public TestCase(int phase, int states, String[] phaseCond, String[] stateCond) {
			
			this.phases = phase;
			this.states = states;
			this.phaseCond = new int[phase];
			this.stateCond = new Integer[states];

			for (int i = 0; i < phases; i++) {
				this.phaseCond[i] = Integer.parseInt(phaseCond[i]);
				
				// if current phase is pending
				if (this.phaseCond[i] > 0) {
					
					// Loop through state and decrement it
					for (int j = 0; j < states; j++) {
						
						if (this.stateCond[j] == null) {
							this.stateCond[j] = Integer.parseInt(stateCond[j]);
						}
						
						// if state is available, then decrement it
						if (this.stateCond[j] > 0 && this.phaseCond[i] > 0) {
							this.phaseCond[i] = this.phaseCond[i] - 1;
							this.stateCond[j] = this.stateCond[j] - 1;
						}

						if (this.phaseCond[i] <=0 && i != (phases - 1)) {
							break;
						}
						
						// if its last phase then check if state condition is met
						if (i == (phases - 1) && this.stateCond[j] > 0) {
							this.isSolved = false;
							break;
						}
					}
				}

				// Current phase loop over
				if (this.phaseCond[i] > 0 || !this.isSolved) {
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

			// Read phases condition
			String[] arr2 = reader.readLine().split(" ");
			String[] arr3 = reader.readLine().split(" ");
			
			testcases.add(new TestCase(phases, states, arr2, arr3));
		}
	}

}
