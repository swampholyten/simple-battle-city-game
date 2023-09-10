package com.swamp.tank;

/**
 * Director
 */
public class Director {

    private static Director instance = new Director();

    private Director() {}

    public static Director getInstance() {
        return instance;
    }
}