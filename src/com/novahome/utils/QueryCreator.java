package com.novahome.utils;

import java.util.List;

public class QueryCreator {

	protected String[] condition ;
	
	protected static List<ConBean> conditionKeys ;
	
	public QueryCreator(String... condition)
	{
		this.condition = condition;
	}
	
	public String createStatement()
	{
		StringBuffer content = new StringBuffer("");
		for(int i=0; i< condition.length; i++)
		{
			String value = condition[i];
			if(value == null || value.isEmpty() || value.trim().isEmpty() || value.equals("-1") )
				continue;
			else
			{
				if(i != 0 && content.length() > 0)
					content.append(" and ");
				content.append(conditionKeys.get(i).toString());
				if(conditionKeys.get(i).getOperator().equals("like"))
				{
					content.append("'%");
					content.append(value);
					content.append("%'");
				}
				else
				{
					content.append(value);
				}
			}
		}
		return content.toString();
	}
	
}
