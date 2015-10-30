package com.novahome.utils;

public class ConBean {

	private String key;
	
	private String operator;
	
	public ConBean(String key, String operator)
	{
		this.key = key;
		this.operator = operator;
	}
	
	
	public String getKey() {
		return key;
	}


	public void setKey(String key) {
		this.key = key;
	}


	public String getOperator() {
		return operator;
	}


	public void setOperator(String operator) {
		this.operator = operator;
	}


	public String toString()
	{
		StringBuffer buf = new StringBuffer(key);
		buf.append(" ");
		buf.append(operator);
		buf.append(" ");
		return buf.toString();
	}
}
