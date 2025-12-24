package com;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Test {
	
	public static int length(String s) {
		
		return s.length();
	}
	
	public static boolean findA(String s) {
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == 'a' || s.charAt(i) == 'A') {
				return true;
			}
			 
		}
		return false;
	}
	
	public static void main(String[] args) {
		List<String> subjects = Arrays.asList("Java","Php","Android","Node");
		
		List<Integer> ls = subjects.stream().map(ele->length(ele)).collect(Collectors.toList());
		
		System.out.println(ls);
		
		List<String> al = subjects.stream().filter(ele->findA(ele)).collect(Collectors.toList());
		System.out.println(al);
		
		List<Integer> l = Arrays.asList(10,25,4,12,49,9,64,32,45);
		
		List<Integer> fl = l.stream().filter(ele->(Math.sqrt(ele) ==(int) Math.sqrt(ele))).collect(Collectors.toList());
		
		System.out.println(fl);
	}
}
