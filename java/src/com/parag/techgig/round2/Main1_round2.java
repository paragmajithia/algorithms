package com.parag.techgig.round2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main1_round2 {

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
		public int noOfBoxes;
		public int[] numbers;
		public Map<Integer, String> bitMap = new HashMap<Integer, String>();

		public TestCase(int noOfBoxes, String[] numbers) {
			super();
			this.noOfBoxes = noOfBoxes;
			this.numbers = new int[noOfBoxes];

			for (int i = 0; i < noOfBoxes; i++) {
				this.numbers[i] = Integer.parseInt(numbers[i]);
				if (!bitMap.containsKey(this.numbers[i])) {
					bitMap.put(this.numbers[i], getbitMask(this.numbers[i]));
				}
			}
		}

		public void printOutput() {
			// Generate maximum
			int maximum = getMaxima(0, "0000000000", 0);
			System.out.println(maximum);
		}

		private String getbitMask(int number) {
			StringBuilder bitMask = new StringBuilder("0000000000");
			for (char digitChar : String.valueOf(number).toCharArray()) {
				bitMask.setCharAt(9 - Character.getNumericValue(digitChar), '1');
			}
			return bitMask.toString();
		}
		
		private String getAnd(String str1, String str2) {
			StringBuilder bitMask = new StringBuilder("0000000000");
			for (int i=0; i < str1.length(); i++) {
				if (str1.charAt(i) == '1' && str2.charAt(i) == '1') {
					bitMask.setCharAt(i, '1');
				}
			}
			return bitMask.toString();
		}
		
		private String getOr(String str1, String str2) {
			StringBuilder bitMask = new StringBuilder("0000000000");
			for (int i=0; i < str1.length(); i++) {
				if (str1.charAt(i) == '1' || str2.charAt(i) == '1') {
					bitMask.setCharAt(i, '1');
				}
			}
			return bitMask.toString();
		}

		public int getMaxima(int index, String bitmask, int currentSum) {

			// Maximum if current element included
			int max1 = 0, max2 = 0;
			String andbitMask = getAnd(bitmask,bitMap.get(numbers[index]));
			String orbitMask = getOr(bitmask, bitMap.get(numbers[index]));
			
			// (if there is no overlap (and))
			if (!andbitMask.contains("1")) {
				if (index < (numbers.length -1)) {
					max1 = getMaxima((index+1), orbitMask, (currentSum + numbers[index]));
				} else {
					max1 = currentSum + numbers[index];
				}
			}
			
			// Maximum if current element excluded
			if (index < (numbers.length -1)) {
				max2 = getMaxima((index+1), bitmask, (currentSum));
			} else {
				max2 = currentSum;
			}
			
			return Math.max(max1, max2);
		}
	}

	public static void readinput() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		numberofTestCases = Integer.parseInt(reader.readLine());

		for (int i = 0; i < numberofTestCases; i++) {

			// Read number of boxes
			int noOfBox = Integer.parseInt(reader.readLine());
			String[] arr = reader.readLine().split(" ");
			testcases.add(new TestCase(noOfBox, arr));
		}
	}
}
