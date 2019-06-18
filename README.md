Preparation Links:  
=====================
* Codecheck certification:  
https://www.codechef.com/certification/data-structures-and-algorithms/prepare

* Competitive programming algos:
https://cp-algorithms.com/

* Graph algorithms: (BFS & DFS, Connectivity)  
	* [Articulation points / Cut vertices in graph](https://www.hackerearth.com/practice/notes/nj/)
* Divide & Conquer 
	* Example: Closest Pair of Points | O(nlogn) Implementation
	http://www.geeksforgeeks.org/closest-pair-of-points-onlogn-implementation/		
* Matrix Usage: 
	* Example: Longest Palindrome & comparsion of recursive and dynamic programming implementation (matrix)  
	http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
	http://www.geeksforgeeks.org/?p=15553
	* Partition problem  
	http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
* Permutation and combinations:   
	* [Combination with repetiontions](http://www.geeksforgeeks.org/combinations-with-repetitions/)  
* Backtracking
	* Example: n queens problem 
	https://www.youtube.com/watch?v=p4_QnaTIxkQ 
	https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
* Dynamic programming (a method for solving a complex problem by breaking it down into a collection of simpler subproblems, solving each of those subproblems just once, and storing their solutions)
	* Example: [Subset sum problem](http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/)
	* Example: Number of coins problem
	https://www.youtube.com/watch?v=GafjS0FfAC0
	```
	Given coins of value v1,v2,..vn..find min no of coins to get sum P 
	C(P) = min(C(P-v1), c(P-v2).. c(P-vn) + 1
	```
	* Example: Find longest increasing sub sequence (ex: array A --(2,7,3,4,9,8,12) has longest subsequence as 2,3,4,9,12)  
	```
	start from i=0
	LS(i) = 1 + max(LS(j)) where j < n && A[i] > A[j]
	  = 1 (if no such j found)
	```	  
	* Example:  
	http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/  
 	Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given 		sum.
	Examples: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
	Output:  True  //There is a subset (4, 5) with sum 9.
	```
	Recursive formula:
	isSubsetSum(set, n, sum) = isSubsetSum(set, n-1, sum) || 
	                           isSubsetSum(set, n-1, sum-set[n-1])
	```					   
	* Example: 0/1 Knapsack problem where you either select the item or drop it.   
	Given list of items with weight and value, you have to select the number of items such that you end up with maximum value but 		total weight as less than given value. (If you were allowed to split, you could have sorted items based on ratio val/wt in 		descending order and pick the items accordingly)
	0/1 Knapsack problem:
	items 
	wt | val
	1 | 1
	3 | 4
	4 | 5
	5 | 7
		
		* Soln: Create matrix T with columns as weight and rows as items starting from minimum weight. Start from min weight
		https://www.youtube.com/watch?v=8LusJS5-AGo

		```
		val/Wt	0	1	2	3	4	5	6	7
		(1) 1	0	1	1	1	1	1	1	1	
		(4) 3	0	1	1	4	5	5	5	5
		(5) 4	0	1	1	4	5	6	6	9
		(7) 5	0	1	1	4	5	7	8	9
		
		if (j < wt[i]) {
			T[i][j] = T[i-1][j]
		} else {
			T[i][j] = max of --> val[i] + T[i-1][j-wt[i]]
							--> T[i-1][j]	
		}		
		```
		* Above works only for weights which are int. For floats check below:  
		http://www.geeksforgeeks.org/branch-and-bound-set-1-introduction-with-01-knapsack/		
		http://www.geeksforgeeks.org/branch-and-bound-set-2-implementation-of-01-knapsack/

		* Implementation for non fraction:  
		http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
		http://www.geeksforgeeks.org/branch-and-bound-set-2-implementation-of-01-knapsack/	

	* Example: Min cost to reach destination. The distance from i to j is given in cost[i][j]
	http://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-direction/

	* Example: Min number of platforms required for railway/bus station-is-connected-in-one-direction/
	sort all events of arrival and departure. Keep counter which tracks arrival and departure. The max value of counter is same as 		no of station required
	http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/

	```
	Ex: 
 	arr[]  = {9:00,  9:40, 9:50,  11:00, 15:00, 18:00}
 	dep[]  = {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
	
	All events sorted by time.
	Total platforms at any time can be obtained by subtracting total 
	departures from total arrivals by that time.
	 Time     Event Type     Total Platforms Needed at this Time                               
 	9:00       Arrival                  1
 	9:10       Departure                0
 	9:40       Arrival                  1
	9:50       Arrival                  2
 	11:00      Arrival                  3 
 	11:20      Departure                2
 	11:30      Departure                1
 	12:00      Departure                0
 	15:00      Arrival                  1
 	18:00      Arrival                  2 
 	19:00      Departure                1
 	20:00      Departure                0
	
	Minimum Platforms needed on railway station = Maximum platforms 
                                              needed at any time 
                                           = 3 
	```
* Job sequencing problem:
```
Input: Four Jobs with following deadlines and profits
  JobID    Deadline      Profit
    a        4            20   
    b        1            10
    c        1            40  
    d        1            30
Output: Following is maximum profit sequence of jobs
        c, a   
		
Greedy: http://www.geeksforgeeks.org/job-sequencing-problem-set-1-greedy-algorithm/
	1) Sort all jobs in decreasing order of profit.
	2) Initialize array a) to store resulting sequence of job b) To keep track of free time slot (both with n elements)
	3) Sort all jobs according to decreasing order of prfit.
	4) Iterate through all given jobs. Find a free slot for this job (Note that we start
       from the last possible slot-- that is minimum of n (no of jobs) OR current jobs deadline). 
	   Keep on checking by decreminting the slot number to see if slot is available
	   If the current job can fit in the current result sequence 
          without missing the deadline, add current job to the result.
          Else ignore the current job.	
	3) Do this for remaining n-1 jobs
	 
Using Disjoint Set: http://www.geeksforgeeks.org/job-sequencing-using-disjoint-set-union/
	1) Sort all jobs in decreasing order of profit.
	2) Find the maximum deadline among all jobs (ex: m)
	3) We create m+1 individual sets to start. {int parent[] = new int[m + 1];}. Every node is a parent of itself initially {parent[i] = i;}
	4) Iterate through all jobs. Find the maximum available free slot "availableFreeSlot" for this job. 
		a. Get "availableFreeSlot" by calling recursive fn by passing deadline. if (s == parent[s]) else find parent[s] (recursive)
		b. if "availableFreeSlot" > 0 then slot available. update the parent of avaialble slot id by finding availalbe slot of (availalbelSlot - 1). 	 

```

* Polygon property (Check if point is within polygon)  
OR check the orientation of triplet point (colinear, clockwise, anticlocck wise)

```
{
	Slope of line segment (p1, p2): σ = (y2 - y1)/(x2 - x1)
	Slope of line segment (p2, p3): τ = (y3 - y2)/(x3 - x2)
	
	If  σ < τ, the orientation is counterclockwise (left turn)
	If  σ = τ, the orientation is collinear
	If  σ > τ, the orientation is clockwise (right turn)
	
	Using above values of σ and τ, we can conclude that, 
	the orientation depends on sign of  below expression: 
	
	(y2 - y1)*(x3 - x2) - (y3 - y2)*(x2 - x1)

	Above expression is negative when σ < τ, i.e., counterclockwise
	Above expression is 0 when σ = τ, i.e., collinear
	Above expression is positive when σ > τ, i.e., clockwise
}
http://www.geeksforgeeks.org/orientation-3-ordered-points/
http://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/ 

```
