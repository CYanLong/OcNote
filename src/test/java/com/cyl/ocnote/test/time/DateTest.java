package com.cyl.ocnote.test.time;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class DateTest {

	@Test
	public void testLocalDate(){
		LocalDate today = LocalDate.now();
		System.out.println(today);
		
		LocalDate today1 = LocalDate.of(2016, 05, 03);
		System.out.println(today1);
		
		LocalDate endOfFeb = LocalDate.parse("2016-05-03");//�ϸ��� ISO yyyy-mm-dd��֤.

	}
	
	@Test
	public void testLocalTime(){
		LocalTime now = LocalTime.now();//15:00:07.821 ����������.
		System.out.println(now);
		
		LocalTime now2 = LocalTime.now().withNano(0);//�������.
		System.out.println(now2);
	}
	
	
}
