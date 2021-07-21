package com.trafigura.temp;

import java.util.*;
public class RoughWorkMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("Parag", "Majithia");
        map.putIfAbsent("Rashmi", "Nagrecha");

        for(String key: map.keySet()) {
            System.out.println("Key: " + key + ", value:" + map.get(key));
        }

        for(Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", value:" + entry.getValue());
        }



    }
}
