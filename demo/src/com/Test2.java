package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test2 {
	public static void main(String[] args) {
		ArrayList<Integer> al = new ArrayList<>();
		al.add(10);
		al.add(25);
		al.add(72);
		al.add(65);
		al.add(36);
		
		List<Integer> ls = al.stream().map(ele->(int)Math.sqrt(ele)).collect(Collectors.toList());
		
		System.out.println(ls);
		
		List<String> abc = Arrays.asList("abc","xyz","ffd","gfd","dfdd");
		
		List<String> op = abc.stream().map(ele->ele.toUpperCase()).collect(Collectors.toList());
		System.out.println(op);
		
		List<String> rs = abc.stream().filter(ele->ele.endsWith("d")).collect(Collectors.toList());
		
		System.out.println(rs);
	}
	
}
