package com.novahome.utils;

import java.util.ArrayList;

public class VisitorQueryCreator extends QueryCreator{
	
	static
	{
		conditionKeys = new ArrayList<ConBean>();
		ConBean bean1 = new ConBean("name", "like");
		ConBean bean2 = new ConBean("recommender", "like");
		ConBean bean3 = new ConBean("org", "like");
		conditionKeys.add(bean1);
		conditionKeys.add(bean2);
		conditionKeys.add(bean3);
	}
	
	
	public VisitorQueryCreator(String...con) {
		// TODO Auto-generated constructor stub
		super(con);
	}


	public static void main(String[] args)
	{
		VisitorQueryCreator v = new VisitorQueryCreator("hha", "dfdf");
		System.out.println(v.createStatement());
	}

}
