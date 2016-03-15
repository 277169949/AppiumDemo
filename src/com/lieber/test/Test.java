package com.lieber.test;

import com.lieber.tools.LieberTools;

public class Test {
	public static void main(String[] args) {
		int a[] = new int[] { 1, 4, 7, 8, 9 };
		String str = LieberTools.CalculateScreenPoint(a, 44, 676, 640, 1272);
		System.out.println(str);
	}
}
