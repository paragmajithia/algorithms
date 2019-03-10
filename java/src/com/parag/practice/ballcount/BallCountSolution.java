package com.parag.practice.ballcount;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BallCountSolution {

	public static void main(String[] args) throws Exception {
		readinput();
		checkConstraint(0, 0, 0);
		System.out.print(minBalls);
	}

	public static List<Integer> boxes;
	public static int noOfBox;
	public static int boxToSelect;
	public static int multiple;
	public static int minBalls = -1;

	public static int checkConstraint(int boxIndex, int currentBoxCount, int currentSum) {

		if (currentBoxCount == boxToSelect || boxIndex >= noOfBox) {
			if ((currentBoxCount == boxToSelect) && currentSum % multiple == 0 && (minBalls == -1 || (currentSum < minBalls))) {
				minBalls = currentSum;
			}
			return currentSum;
		}

		int newBoxCount = currentBoxCount + 1;
		int newSum = currentSum + boxes.get(boxIndex);
		int minima;
		
		if (minBalls != -1 && newSum > minBalls) {
			minima = checkConstraint((boxIndex+1), currentBoxCount, currentSum);
		} else {
			minima = Math.min(checkConstraint((boxIndex+1), newBoxCount, newSum), checkConstraint((boxIndex+1), currentBoxCount, currentSum));
		}
		
		return minima;
	}

	public static void readinput() throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		noOfBox = Integer.parseInt(reader.readLine());

		boxes = IntStream.range(0, noOfBox).mapToObj(i -> {
			try {
				return Integer.parseInt(reader.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return Integer.MIN_VALUE;
		}).collect(Collectors.toList());

		multiple = Integer.parseInt(reader.readLine());
		boxToSelect = Integer.parseInt(reader.readLine());
	}
}
