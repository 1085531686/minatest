package minatest;

import org.apache.commons.lang3.ArrayUtils;

public class ArraySubTest {

	public static void main(String[] args) {
		
		Integer[] bs = new Integer[10];
		bs[1]=99;
		sub( bs);
		System.out.println(bs[1]);
		System.out.println(bs.length);
	}
	
	public static void sub(Integer[] bs){
		bs = ArrayUtils.subarray(bs, 3,  bs.length);
//		System.out.println(bs[1]);
//		bs[1]=88;
//		System.out.println(bs[1]);
		System.out.println(bs.length);
	}
}
