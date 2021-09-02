package com.leetcode.segmenttrees;

import java.util.*;

/**
 * 731. My Calendar II
 * Medium
 *
 * 948
 *
 * 107
 *
 * Add to List
 *
 * Share
 * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
 *
 * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
 *
 * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
 *
 * Implement the MyCalendarTwo class:
 *
 * MyCalendarTwo() Initializes the calendar object.
 * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, true, true, true, false, true, true]
 *
 * Explanation
 * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
 * myCalendarTwo.book(10, 20); // return True, The event can be booked.
 * myCalendarTwo.book(50, 60); // return True, The event can be booked.
 * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
 * myCalendarTwo.book(5, 15);  // return False, The event ca not be booked, because it would result in a triple booking.
 * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
 * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
 *
 *
 * Constraints:
 *
 * 0 <= start < end <= 109
 * At most 1000 calls will be made to book.
 */

public class MyCalendar2 {

    // Store boundary delta counts
    // For meeting from 2 to 3:30 and 3:00 to 4:30
    // Ex: at 2:00 pm => + 1, at 3 pm => +1, 3:30 pm ==> -1, 4:30 pm => -1
    TreeMap<Integer, Integer> map = new TreeMap<>();

    public MyCalendar2() {


    }

    public boolean book(int start, int end) {
        // Increase / Decrease the boundary
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) -1);

        // Check if it crossed the boundary
        // if yes then revert and return false
        int delta = 0;
        for (Integer key :map.keySet()) {
            delta = delta + map.get(key);
            if (delta >= 3) {
                // Found thrice booking
                // Update start
                int newVal = map.get(start) - 1;
                if (newVal == 0) {
                    map.remove(start);
                } else {
                    map.put(start, newVal);
                }

                // Update end boundary
                map.put(end, map.get(end) + 1);

                return false;
            }

        }


        // Default
        return true;


    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */