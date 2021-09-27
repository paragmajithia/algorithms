package com.leetcode.orderedset;

import java.util.*;

/**
 * 497. Random Point in Non-overlapping Rectangles
 * Medium
 *
 * 356
 *
 * 562
 *
 * Add to List
 *
 * Share
 * You are given an array of non-overlapping axis-aligned rectangles rects where rects[i] = [ai, bi, xi, yi] indicates that (ai, bi) is the bottom-left corner point of the ith rectangle and (xi, yi) is the top-right corner point of the ith rectangle. Design an algorithm to pick a random integer point inside the space covered by one of the given rectangles. A point on the perimeter of a rectangle is included in the space covered by the rectangle.
 *
 * Any integer point inside the space covered by one of the given rectangles should be equally likely to be returned.
 *
 * Note that an integer point is a point that has integer coordinates.
 *
 * Implement the Solution class:
 *
 * Solution(int[][] rects) Initializes the object with the given rectangles rects.
 * int[] pick() Returns a random integer point [u, v] inside the space covered by one of the given rectangles.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["Solution", "pick", "pick", "pick", "pick", "pick"]
 * [[[[-2, -2, 1, 1], [2, 2, 4, 6]]], [], [], [], [], []]
 * Output
 * [null, [1, -2], [1, -1], [-1, -2], [-2, -2], [0, 0]]
 *
 * Explanation
 * Solution solution = new Solution([[-2, -2, 1, 1], [2, 2, 4, 6]]);
 * solution.pick(); // return [1, -2]
 * solution.pick(); // return [1, -1]
 * solution.pick(); // return [-1, -2]
 * solution.pick(); // return [-2, -2]
 * solution.pick(); // return [0, 0]
 *
 *
 * Constraints:
 *
 * 1 <= rects.length <= 100
 * rects[i].length == 4
 * -109 <= ai < xi <= 109
 * -109 <= bi < yi <= 109
 * xi - ai <= 2000
 * yi - bi <= 2000
 * All the rectangles do not overlap.
 * At most 104 calls will be made to pick.
 */

/**
 * ==================== EXPLANATION OF SOLUTION ==============
 * Problem
 * The intuitive solution is randomly pick one rectangle from the rects and then create a random point within it. But this approach wont work. It took me a while to understand, I am trying to explain it:
 *
 * image
 *
 * As shown in the picture above, you have two rectangles. Lets declare S1 to be the set of points within rectanle 1, and S2 to be point set within rectable 2. So, mathematically we have:
 *
 * S1 = {p11, p12, p13, ........................, p1n}
 * S2 = {p21, p22, p23, ......, p2m}
 * n > m
 * Obviously, you can see that rectangle 1 is larger than rectangle 2 and therefore S1 has bigger size (n > m).
 * According to the problem, "randomly and uniformily picks an integer point in the space covered by the rectangles". It is very difficult to understand, lets translated it into another way of expression.
 *
 * It is saying that, now I am providing you a new set S = S1 + S2, where S = {p11, p12, ............, p1n, p21, p22, ........., p2m} , within this new set of points that are merged together, randomly pick a point from it. What do you think of the probability of getting p1i and p2j right now ? They are exactly the same, which is 1 / (n + m).
 *
 * Now, we can answer why the intuitive solution wont work. If you first randomy pick a rectangle, you have 50% to get either S1 or S2, how ever, once you select on rectangle, you have selected S1 as your candidate point set, no matter how many time you try to pick you will never get the points in the second set S2. If size of S1 and S2 are the same, that would be ok, but if S1 is bigger than S2, you will run into a problem.
 *
 * For example, the chance of getting S1 and S2 are both 1 / 2, so far so good. How ever, within S1 and S2, the chance of getting point p1i and p2j are 1 / n and 1 / m. So the final chance of getting p1i and p2j are:
 *
 * probability_of_getting_p1i = 1 / (2 * n)
 * probability_of_getting_p2j = 1 / (2 * m)
 *
 * Where n is the size of S1, and m is size of S2
 * The probability depends on the size of two rectangle.
 *
 * Solution
 * So how can we solve the problem that descibed in previous section ? One way is to really merge all the point set of every rectangle and radnomly pick one from them, but this may not be a good idea since it requires very hight space somplexity. What if we make the chance of getting reactangleS1 higher than rectangle S2 (from rects) base on the size of each of them.
 * for example, for simplification, lets assume:
 *
 * n = size_of_s1 = 70
 * m = size_of_s2 = 30
 *
 * n + m = 100
 * if we can have the chance of getting S1 to be 70 % and chance of getting S2 to be 30 %, the chance of getting p1i from S1 is 1 / 70, and chance of getting p2j from S2 is 1 / 30, we have:
 *
 * probality_of_getting_p1i = (70 / 100) * (1 / 70) = 1 / 100
 *
 * probability_of_getting_p2j = (30 / 100) * (1 / 30) = 1 / 100
 * problem sovled !
 *
 * So, our mission is to implement an algorithm that allows us to have higher chance to pick the larger rectangle, and then generate a random point with it. Lets use code pseudo to make a rough design, still base on the previous example, imagine you have a map:
 *
 *
 * map =
 * {
 * 	30: S1,
 * 	30 + 70: S2
 * }
 *
 * or
 *
 * map =
 * {
 *   30: S1,
 *   100: S2
 * }
 *
 *
 * randomNumer = random(0, 100)
 *
 * if  30 < randomNumber <= 100:
 * 	return S2
 * else
 * 	return S1
 *
 * If we can design an algorithm like that, we will have 70% chance gitting S2, and 30% chance hitting S1. With number of intervals already known, we can simply use if .. else block or switch to implement this, but the problem did not specify how many rects, so the popular solution uses TreeMap .
 */
public class RandomPointNonOverlapRectangles {

    // Rectangles
    int[][] rects;
    // Store rectangles using cumulative area
    // This is to select random rectangles based on size / area
    TreeMap<Integer, Integer> rectAreaMap = new TreeMap<>();
    int totalArea = 0;
    int totalRects;
    Random rand = new Random();


    public RandomPointNonOverlapRectangles(int[][] rects) {
        // Store the rects
        this.rects = rects;
        this.totalRects = rects.length;
        for (int i = 0; i < totalRects; i++) {
            int area = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
            totalArea += area;
            rectAreaMap.put(totalArea, i);
        }
    }

    public int[] pick() {
        // Get number between 0 and max rect area
        // +1 is done as the param passed is exclusive
        int num = rand.nextInt(totalArea)+1;

        // Get the rectangle mapped to given number
        int rectnum = rectAreaMap.get(rectAreaMap.ceilingKey(num));

        // Get the random point within the rectangle
        int randX = rects[rectnum][0] + rand.nextInt(rects[rectnum][2] - rects[rectnum][0] + 1);
        int randY = rects[rectnum][1] + rand.nextInt(rects[rectnum][3] - rects[rectnum][1] + 1);

        return new int[] {randX, randY};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */

/**
 *
 * ====================== BETTER SOLUTION ========================
 class Solution {
 TreeMap<Integer, int[]> map= new TreeMap<>();
 Random rnd= new Random();
 int area= 0;
 public Solution(int[][] rects) {
 for (int[] r: rects){
 int x1=r[0], y1=r[1], x2=r[2], y2=r[3];
 area+=(x2-x1+1)*(y2-y1+1); \\Don't forget to +1 here, because e.g 1~5 has 5 valid numbers
 map.put(area, r);
 }
 }

 public int[] pick() {
 int key= map.ceilingKey(rnd.nextInt(area)+1); \\Don't forget to +1 here, because we need [1,area] while nextInt generates [0,area-1]
 int[] curRec= map.get(key);
 int x1=curRec[0], y1=curRec[1], x2=curRec[2], y2=curRec[3], length=x2-x1, width=y2-y1;
 int x=x1+rnd.nextInt(length+1), y=y1+rnd.nextInt(width+1);
 return new int[]{x, y};
 }
 }

 */