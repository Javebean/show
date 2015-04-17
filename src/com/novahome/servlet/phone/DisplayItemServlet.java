package com.novahome.servlet.phone;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.service.DisplayItemService;
import com.novahome.utils.HtmlParser;


public class DisplayItemServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private DisplayItemService displayItemService;
	private static final String[] METHOD_NAMES = {"additem","getbyid","getbyorgid"};
	
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 displayItemService = (DisplayItemService) ctx.getBean("displayItemService");  
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		int subStart = uri.lastIndexOf("/");
		int subEnd = uri.lastIndexOf(".");
		String path = "";
		if(subStart < subEnd)
		{
			path = uri.substring(subStart+1,subEnd);
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
			
		response.setContentType("text/plain;charset=UTF-8");
		if(path.equals(METHOD_NAMES[0]))
		{
			DisplayItem item = processItemParams(request);
			response.getWriter().write(displayItemService.saveDisplayItem(item));
		}
		else if(path.equals(METHOD_NAMES[1]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(displayItemService.getDisplayItemById(id));
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			String eid = request.getParameter("eid");
			response.getWriter().write(displayItemService.getDisplayItemByEid(eid));
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
		
	}
	
	private DisplayItem processItemParams(HttpServletRequest request)
	{
		DisplayItem item = new DisplayItem();
		String eid = request.getParameter("eid");
		String nameWild = request.getParameter("name");
		String name = "";
		if(nameWild!= null)
			try {
				name = new String(nameWild.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String lenStr = request.getParameter("length");
		String widStr = request.getParameter("width");
		String hgtStr = request.getParameter("height");
		String wgtStr = request.getParameter("weight");
		String numStr = request.getParameter("number");
		String versionWild = request.getParameter("version");
		String version = "";
		if(versionWild!= null)
			try {
				version = new String(versionWild.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		double length,width, height,weight;
		int number;
		length = processTransfer(lenStr);
		width = processTransfer(widStr);
		height = processTransfer(hgtStr);
		weight = processTransfer(wgtStr);
		if(numStr != null || !numStr.isEmpty())
		{
			number = Integer.parseInt(numStr);
		}
		else
			number = 1;
		item.setEid(eid);
		item.setName(name);
		item.setLength(length);
		item.setWidth(width);
		item.setHeight(height);
		item.setWeight(weight);
		item.setNumber(number);
		item.setVersion(version);
		return item;
	}
	
	private double processTransfer(String param)
	{
		double ret;
		if(param != null || !param.isEmpty())
		{
			ret = Double.parseDouble(param);
		}
		else
			ret = 0.0;
		return ret;
	}
}
