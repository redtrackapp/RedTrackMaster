package com.dbs.redtrack.business.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class test {

	public static void main (String[] args) {
		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();
		
		int x=0;
		for (int y=0; y<10; y++){
			System.out.println(">>: " + x++);
		}
		
		
		String test ="SL1";
				System.out.println("substring: "+ test.substring(2) );
//		int temp = 2;
//		if(bankAccNos.contains(bakAccNo)) temp=1;

		List<String> list = new ArrayList<String>(Arrays.asList("SL1", "SL2", "SL3"));
		
		if(list.contains("SL4f")) {
			System.out.println("FOUND!!");
		} else {
			
			System.out.println("Not FOUND!!!");
		}
		
		System.out.println("Cvsafd" + currentTime);
		 String currentTime1    = String.valueOf(System.currentTimeMillis());

			System.out.println("currentTime1 " + currentTime1);
			
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
//			Date dte =  Calendar.getInstance().getTime();
			System.out.println( "currentTime1:::"+ Calendar.getInstance().getTime());
	}
}
