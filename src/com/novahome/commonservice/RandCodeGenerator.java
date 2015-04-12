package com.novahome.commonservice;

import java.util.Random;
import java.util.UUID;

public class RandCodeGenerator {

	
	private static String[] exhibtChars =  { "a", "b", "c", "d", "e", "f"};
	
	private static String[] userChars = {"u", "v", "w", "x", "y", "z"};
		
	public static String generateExhibitUser()
	{
		StringBuffer shortBuffer = new StringBuffer();
		Random rand = new Random();
		shortBuffer.append(exhibtChars[rand.nextInt(6)]);
		return generateShortUUID(shortBuffer).toString();
	}
	
	/*public static void main(String[] args)
	{
		System.out.println(RandCodeGenerator.generateExhibitUser());
		System.out.println(RandCodeGenerator.generateAudiUser());
		System.out.println(RandCodeGenerator.generatePwd());
	}*/
	
	
	public static String generatePwd()
	{
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 6; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(x%10);
		}
		return shortBuffer.toString();
		
	}
	
	public static String generateAudiUser()
	{
		StringBuffer shortBuffer = new StringBuffer();
		Random rand = new Random();
		shortBuffer.append(userChars[rand.nextInt(6)]);
		return generateShortUUID(shortBuffer).toString();
	}
	
	private static StringBuffer generateShortUUID(StringBuffer shortBuffer)
	{
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(x%10);
		}
		return shortBuffer;
	}
}
