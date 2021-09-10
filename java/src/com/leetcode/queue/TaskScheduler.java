package com.leetcode.queue;

/**
 * 621. Task Scheduler
 * Medium
 *
 * 5504
 *
 * 1009
 *
 * Add to List
 *
 * Share
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation:
 * A -> B -> idle -> A -> B -> idle -> A -> B
 * There is at least 2 units of time between any two same tasks.
 * Example 2:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 0
 * Output: 6
 * Explanation: On this case any permutation of size 6 would work since n = 0.
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * And so on.
 * Example 3:
 *
 * Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * Output: 16
 * Explanation:
 * One possible solution is
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 *
 *
 * Constraints:
 *
 * 1 <= task.length <= 104
 * tasks[i] is upper-case English letter.
 * The integer n is in the range [0, 100].
 */

import java.util.*;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {

        // Get count of each task
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }

        // Priority Queue with high count of task first
        Comparator<Map.Entry<Character, Integer>> comp = (a,b) -> (b.getValue() - a.getValue());
        PriorityQueue<Map.Entry<Character, Integer>> que = new PriorityQueue<>(comp);
        que.addAll(map.entrySet());

        // Get each task from que with highest frequency first
        int count = 0;
        List<Map.Entry<Character, Integer>> tempList = new ArrayList();
        int interval = n + 1;
        while (!que.isEmpty() || tempList.size() > 0) {

            // Start new interval if queue is empty
            if (que.isEmpty()) {
                // Add count of idle slots from previous interval
                count = count + interval;
                // Reset the interval
                interval = n + 1;

                // Add all the task of previous interval
                que.addAll(tempList);
                tempList.clear();
            }

            // Get task and store in Temp list to store task
            Map.Entry<Character, Integer> currEntry = que.poll();
            count++;
            currEntry.setValue(currEntry.getValue() - 1);
            interval--;
            if (currEntry.getValue() > 0) {
                tempList.add(currEntry);
            }

            // Start the new interval
            if (interval <= 0) {
                que.addAll(tempList);
                tempList.clear();
                // Reset the interval
                interval = n + 1;
            }

        }

        return count;

    }
}