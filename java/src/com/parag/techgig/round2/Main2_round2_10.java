package com.parag.techgig.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 6th is best for now Use string tokenizer for input
 * 
 * @author paragm
 *
 */
public class Main2_round2_10 {

	public static void main(String[] args) throws IOException {

		// Read input
		readinput();

		// Process each test case
		for (TestCase testcase : testcases) {
			// long startTime = System.nanoTime();
			testcase.printOutput();
			// System.out.println("Time taken in milli sec: " + ((System.nanoTime() - startTime)) / 1000000L);
		}
	}

	public static int numberofTestCases;
	public static List<TestCase> testcases = new ArrayList<TestCase>();

	public static class TestCase {
		public int phases;
		public int states;
		private String phaseCond;
		
		private List<Integer> stateList = new ArrayList<Integer>();
		private LinkedList<Integer> phaseList = new LinkedList<Integer>();
		private List<Integer> stateMaximal;
		private int stateSum = 0;
		private int phaseSum = 0;
		
		public boolean isSolved = true;

		public TestCase(int phase, int states, String phaseCond, String stateCond) {
			this.phases = phase;
			this.states = states;
			this.phaseCond = phaseCond;

			// Initialize statelist
			// long startTime = System.nanoTime();
			StringTokenizer st = new StringTokenizer(stateCond, " ");
			Integer stateCondInt;
			while (st.hasMoreTokens()) {
				stateCondInt = Integer.parseInt(st.nextToken());
				stateList.add(stateCondInt);
				stateSum = stateSum + stateCondInt;
			}
			// System.out.println("Time taken to initialize state list milli sec: " + ((System.nanoTime() - startTime)) / 1000000L);
			
			// startTime = System.nanoTime();
			Collections.sort(stateList, Collections.reverseOrder());
			// System.out.println("Time taken to sort state list milli sec: " + ((System.nanoTime() - startTime)) / 1000000L);
			
			// Initialize state maximal
			stateMaximal = new ArrayList<Integer>(Arrays.asList(new Integer[states]));
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

			// long startTime = System.nanoTime();
			StringTokenizer st = new StringTokenizer(this.phaseCond, " ");
			Integer phaseCond;
			while (st.hasMoreTokens()) {
				phaseCond = Integer.parseInt(st.nextToken());
				phaseSum = phaseSum + phaseCond;
				if (phaseCond > 0) {
					phaseList.add(phaseCond);
				}
			}
			// System.out.println("Time taken to create maximal list milli sec: " + ((System.nanoTime() - startTime)) / 1000000L);
			
			// startTime = System.nanoTime();
			Collections.sort(phaseList);
			// System.out.println("Time taken to sort phase list milli sec: " + ((System.nanoTime() - startTime)) / 1000000L);
			
			// Check phase sum and state sum is same
			if (this.phaseSum != this.stateSum) {
				this.isSolved = false;
				return;
			}
			
			// Update Maximal state list
			// startTime = System.nanoTime();
			int index = 0;
			while (phaseList.size() > 0 && index < stateMaximal.size()) {
				stateMaximal.set(index, phaseList.size());
				
				while (!phaseList.isEmpty() && phaseList.get(0) - (index + 1) <= 0) {
					phaseList.remove();
				}
				index++;
			}
			// System.out.println("Time taken to update maximal list: " + ((System.nanoTime() - startTime)) / 1000000L);
			
			// check majorisation condition
			// startTime = System.nanoTime();
			int stateCumSum = 0;
			int maximalCumSum = 0;
			for (int i=0; i<stateMaximal.size(); i++) {
				stateCumSum = stateCumSum + this.stateList.get(i);
				maximalCumSum = maximalCumSum + ((stateMaximal.get(i) == null?0:stateMaximal.get(i)));
				if (maximalCumSum < stateCumSum) {
					this.isSolved = false;
					break;
				}
			}
			// System.out.println("Time taken to check majorisation: " + ((System.nanoTime() - startTime)) / 1000000L);
		}
	}

	public static void readinput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		numberofTestCases = Integer.parseInt(reader.readLine());

		// long startTime = System.nanoTime();
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
		// System.out.println("Time taken to read input: " + ((System.nanoTime() - startTime)) / 1000000L);
	}

}
