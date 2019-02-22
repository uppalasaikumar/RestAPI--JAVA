package com.interview.sol;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Driver {
	static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "abcxbdca";
		
		for(int i =0;i<s.length();i++) {
			if(s.indexOf(s.substring(i,i+1)) == s.lastIndexOf(s.substring(i,i+1))) {
				System.out.println(s.substring(i,i+1));
				break;
			}
			
		}
		

		permutation("",s);
		System.out.println(count);
		
		
	}

	private static void permutation(String temp, String word) {
		
		if(word.isEmpty()) {
			count++;
			System.out.println(temp);
		}
		
		for(int i =0; i<word.length();i++) {
			
			permutation(temp+word.charAt(i), word.substring(0, i)+word.substring(i+1,word.length()));
		}
	}

}
