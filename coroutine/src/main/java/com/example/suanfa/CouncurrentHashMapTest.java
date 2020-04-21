package com.example.suanfa;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class CouncurrentHashMapTest {
    public static void main(String[] args) {

        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>(16);

        for (int i = 0; i <= 1000; i++) {
            map.put(i, "test");
        }


    }
}
