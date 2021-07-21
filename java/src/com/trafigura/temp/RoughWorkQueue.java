package com.trafigura.temp;

import java.util.*;

public class RoughWorkQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Parag");
        queue.offer("Majithia");
        System.out.println("Peeked: " + queue.peek());

        while (!queue.isEmpty()) {
            System.out.println("Polled: " + queue.poll());
        }
    }
}
