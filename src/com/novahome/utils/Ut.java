package com.novahome.utils;

import java.text.SimpleDateFormat;


public class Ut {
	//精准date format
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	//用于显示在新闻栏目后面，只精确到新闻日期
	public static SimpleDateFormat newsDf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
	//Method for print line
	public static void pt(Object text) {
		System.out.println(text);
	}
	
	
}
