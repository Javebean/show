package com.novahome.servlet.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novahome.data.service.ZlzxService;
import com.novahome.utils.HtmlParser;


public class ZlzyServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ZlzxService zlzxService;
	private static final String[] METHOD_NAMES = {"count","pagelist","details"};
	
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 zlzxService = (ZlzxService) ctx.getBean("zlzxService");    		
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
			response.getWriter().write(zlzxService.getZlzxTotalCount());
		}
		else if(path.equals(METHOD_NAMES[1]))
		{
			int start,number;
			String startInt = request.getParameter("start");
			String numberInt = request.getParameter("number");
			if(startInt != null && !startInt.isEmpty())
				 start = Integer.parseInt(startInt);
			else
			{
				response.getWriter().write("{\"error\":\"start参数缺失\"}");
				return;
			}
			if(numberInt != null && !numberInt.isEmpty())
				 number = Integer.parseInt(numberInt);
			else
			{
				response.getWriter().write("{\"error\":\"number参数缺失\"}");
				return;
			}
			response.getWriter().write(zlzxService.getShortZlzxForPhonePage(start, number));
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(zlzxService.getZlzxById(id));
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
		
	}
		
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}
}
