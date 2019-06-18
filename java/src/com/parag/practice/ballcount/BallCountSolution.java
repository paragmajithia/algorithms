package com.parag.practice.ballcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BallCountSolution {

	public static void main(String[] args) throws Exception {
		
		// Read input
		readinput();
		
		// Recursively check for possible combinations
		checkConstraint(0, 0, 0);
		System.out.print(minBalls);
	}

	public static List<Integer> boxes;
	public static int noOfBox;
	public static int boxToSelect;
	public static int multiple;
	public static int minBalls = -1;

	
	public static int checkConstraint(int boxIndex, int currentBoxCount, int currentSum) {

		// Check if we reached last condition -- all box selected Or box indexed crossed
		if (currentBoxCount == boxToSelect || boxIndex >= noOfBox) {
			if ((currentBoxCount == boxToSelect) && currentSum % multiple == 0 && (minBalls == -1 || (currentSum < minBalls))) {
				// Update min balls 
				minBalls = currentSum;
			}
			return currentSum;
		}

		int newBoxCount = currentBoxCount + 1;
		int newSum = currentSum + boxes.get(boxIndex);
		int minima;
		
		// Get the minimum balls from either including the current box or excluding the current box
		if (minBalls != -1 && newSum > minBalls) {
			minima = checkConstraint((boxIndex+1), currentBoxCount, currentSum);
		} else {
			minima = Math.min(checkConstraint((boxIndex+1), newBoxCount, newSum), checkConstraint((boxIndex+1), currentBoxCount, currentSum));
		}
		
		return minima;
	}

	public static void readinput() throws NumberFormatException, IOException {
		
		// Read total number of box
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		noOfBox = Integer.parseInt(reader.readLine());

		// Read number of balls in each box and add it to the list
		boxes = IntStream.range(0, noOfBox).mapToObj(i -> {
			try {
				return Integer.parseInt(reader.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Integer.MIN_VALUE;
		}).collect(Collectors.toList());

		// Read the expected multiples of balls & the total number of balls to select
		multiple = Integer.parseInt(reader.readLine());
		boxToSelect = Integer.parseInt(reader.readLine());
	}
}
