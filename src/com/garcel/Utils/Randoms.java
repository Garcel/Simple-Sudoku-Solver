package com.garcel.Utils;

import java.util.Random;

public class Randoms {
	/**
	     * Return a random number between the min and max provided
	     * 
	     * @param min
	     * @param max
	     * @return the random number between min and max
	     */
	    public static int randomNumber(int min, int max) {
	            return min + (new Random()).nextInt(max-min);
	    }
}

