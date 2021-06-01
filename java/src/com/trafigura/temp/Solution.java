package com.trafigura.temp;
// This is a sandbox environment that allows you to experiment with CoderPad's execution capabilities
// It's a temporary throw-away session only visible to you so you can test out the programming environment.
// Once you select a language, to execute your code simply hit the 'Run' button which will be located in the top left hand of your screen.
//
// To see more information about the language you have selected, hit the 'Info' button beside the language dropdown.
// You'll find what version of the language is running and the packages available for the given language.

/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");

        for (String string : strings) {
            System.out.println(string);
        }

        PriorityQueue<String> queue = new PriorityQueue<String>(new Comparator<String>() {
            public int compare(String test, String test2){
                return -1 * test.compareTo(test2);
            }

        });
        queue.add("Parag");
        queue.add("Majithia");
        queue.add("A");
        queue.add("Z");
        queue.add("B");

        System.out.println("first element: " + queue.poll());
        System.out.println("second elemenet: " + queue.poll());
        System.out.println("third elemenet: " + queue.poll());
        System.out.println("fourth elemenet: " + queue.poll());
        System.out.println("fifth elemenet: " + queue.poll());
        System.out.println("six elemenet: " + queue.poll());


    }
}
