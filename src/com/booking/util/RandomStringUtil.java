package com.booking.util;

import java.util.Random;

public class RandomStringUtil {

	public static String randomStr(int length) {
		
		int leftLimit = 65; // letter 'A'
	    int rightLimit = 90; // letter 'Z'
	    int targetStringLength = length;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    
	    return buffer.toString();

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
