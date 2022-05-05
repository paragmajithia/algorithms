Preparation Links:  
=====================
* General Preparation links:
	* Codecheck certification:  
	https://www.codechef.com/certification/data-structures-and-algorithms/prepare  
	* Competitive programming algos:  
	https://cp-algorithms.com/  

* Tree traversal  
	* Morris Traversal [https://www.educative.io/edpresso/what-is-morris-traversal]  
	
* Graph algorithms: (BFS & DFS, Connectivity)  
	* Djikstra's shortest path (for non negative edges)  
	https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
	* Bellman Ford (for negative edges)  
	https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/  
	* [Articulation points / Cut vertices in graph]  
	(https://www.hackerearth.com/practice/notes/nj/)  
	https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/  
	* Max flow  
	
* Divide & Conquer  
	* Example: Closest Pair of Points | O(nlogn) Implementation  
	http://www.geeksforgeeks.org/closest-pair-of-points-onlogn-implementation/  
	  
* Matrix Usage: 
	* Example: Longest Palindrome & comparsion of recursive and dynamic programming implementation (matrix)  
	http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
	http://www.geeksforgeeks.org/?p=15553
	* Partition problem  
	http://www.geeksforgeeks.org/dynamic-programming-set-18-partition-problem/
* Combinatorics: Permutation and combinations:   
	* [Combination with repetiontions](http://www.geeksforgeeks.org/combinations-with-repetitions/)  
	* [Combinations with repetitions example] (https://www.superprof.co.uk/resources/academic/maths/probability/combinatorics/combinations-with-repetition.html)  
	* [Calculate combinations and permutation](https://sciencing.com/calculate-combinations-permutations-4466533.html)  
	* [Navigate grid using combinations and permutations](https://betterexplained.com/articles/navigate-a-grid-using-combinations-and-permutations/)  
* Backtracking
	* Example: n queens problem 
	https://www.youtube.com/watch?v=p4_QnaTIxkQ 
	https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
* String algortihms:  
	* KMP algorithm for pattern searching (Prefix table)  
	https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/
	* RabinKarp  
	https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
	https://www.baeldung.com/cs/rabin-karp-algorithm
	* Trie / Suffix tree / Suffix array  
	https://www.hackerearth.com/practice/notes/trie-suffix-tree-suffix-array/  
	https://www.geeksforgeeks.org/auto-complete-feature-using-trie/  
	https://www.cs.cmu.edu/~ckingsf/bioinfo-lectures/suffixtrees.pdf  
	https://www.baeldung.com/java-pattern-matching-suffix-tree
	* General String methods  
	https://www.geeksforgeeks.org/base-conversion-in-java/  
	https://www.codetab.org/post/java-unicode-basics/  
	https://www.baeldung.com/java-number-of-digits-in-int  
	
* Interval Trees
	* https://www.tutorialcup.com/interview/tree/interval-tree.htm
	* https://iq.opengenus.org/interval-tree/

* Segment Trees:  
	* https://cp-algorithms.com/data_structures/segment_tree.html
	* https://www.hackerearth.com/practice/data-structures/advanced-data-structures/segment-trees/tutorial/
	* Sum of given range  
	https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/  
	https://www.geeksforgeeks.org/lazy-propagation-in-segment-tree/
	
* Disjoint sets:
	* Disjoint set union (Union find data structure)
	https://cp-algorithms.com/data_structures/disjoint_set_union.html#toc-tgt-5
	
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
	
	* Soln: Create matrix T with columns as weight and rows as items starting from minimum weight. Start from min weight
	https://www.youtube.com/watch?v=8LusJS5-AGo

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

* Job sequencing problems:
	* Greedy: http://www.geeksforgeeks.org/job-sequencing-problem-set-1-greedy-algorithm/  
	* Using Disjoint Set: http://www.geeksforgeeks.org/job-sequencing-using-disjoint-set-union/

* Geometry Problems:
	* Polygon property (Check if point is within polygon)  
	http://www.geeksforgeeks.org/orientation-3-ordered-points/  
	http://www.geeksforgeeks.org/how-to-check-if-a-given-point-lies-inside-a-polygon/  
	* Co-ordinate geometry formulas  
	https://byjus.com/maths/coordinate-geometry-formulas/


