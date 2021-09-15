package com.trafigura.temp;

import java.util.*;

public class RoughWork {

    public static void main(String[] args) {
        String[] strArr = new String[] {"Parag", "Majithia", "Test", "another"};
        Arrays.sort(strArr, (a, b) ->
            a.compareToIgnoreCase(b)
        );
        System.out.println("Array: " + Arrays.toString(strArr));
        List<Integer> iList = new ArrayList<>(Arrays.asList(new Integer[] {6,2,7,4,5, 1}));
        iList.sort(Integer::compareTo);
        System.out.println("Ascending: " + iList);

        Comparator<Integer> comp = (Integer::compareTo);
        // iList.sort(Comparator.reverseOrder());
        iList.sort(comp.reversed());
        System.out.println("Reverse:" + iList);

    }
}
