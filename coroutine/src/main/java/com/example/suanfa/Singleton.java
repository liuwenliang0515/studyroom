package com.example.suanfa;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class Singleton {

    private static Singleton INSTANCE = null;

    private Singleton() {

    }

    public Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (this) {
                if (INSTANCE == null)
                    INSTANCE = new Singleton();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        HashMap<Model, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(new Model(1), 1);
        }

        System.out.println(map.size());
    }


    static class Model {

        int val;

        Model(int val) {
            this.val = val;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
