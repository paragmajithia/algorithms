package com.leetcode;

import java.util.*;
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        CourseSchedule sched = new CourseSchedule();

        System.out.println(Arrays.toString(sched.findOrder(2, new int[][] {{1,0}})));
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        // Indegree as 0
        int[]  inDegree = new int[numCourses];
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        List<Integer> answer = new ArrayList<>();

        // Update indegree
        for (int i=0; i < prerequisites.length; i++) {
            Set<Integer> adjacent = graph.getOrDefault(prerequisites[i][1], new HashSet<Integer>());
            adjacent.add(prerequisites[i][0]);
            graph.put(prerequisites[i][1], adjacent);
            inDegree[prerequisites[i][0]]++;
        }
        System.out.println("Indegree: " + Arrays.toString(inDegree));
        System.out.println("Graph: " + graph);
        Queue<Integer> list = new LinkedList<>();
        // Add all nodes with indegree as 0
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                list.offer(i);
            }
        }

        while (!list.isEmpty()) {
            int course = list.poll();
            answer.add(course);
            if (graph.containsKey(course)) {
                for (int depCourse: graph.get(course)) {
                    inDegree[depCourse] = inDegree[depCourse]- 1;
                    if (inDegree[depCourse] == 0) {
                        list.offer(depCourse);
                    }
                }
            }
        }

        if (answer.size() == numCourses) {
            return answer.stream().mapToInt(i -> i).toArray();
        }

        return new int[0];
    }
}
