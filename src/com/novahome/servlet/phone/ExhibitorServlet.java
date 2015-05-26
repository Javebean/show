package com.novahome.servlet.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novahome.commonservice.Constants;
import com.novahome.data.service.ExhibitorsService;
import com.novahome.utils.HtmlParser;

public class ExhibitorServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private ExhibitorsService exhibitorsService;
	private static final String[] METHOD_NAMES = {"pagelist","details","totaldetails","login","logout"};
	
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 exhibitorsService = (ExhibitorsService) ctx.getBean("exhibitorsService");    		
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
			response.getWriter().write(exhibitorsService.getShortExhibitorForPageByState(start, number,1, null));
		}
		else if(path.equals(METHOD_NAMES[1]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(exhibitorsService.getExhibitorByIdWithServlet(id));
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(exhibitorsService.getTotalExhibitInfoByIdWithServlet(id));
		}
		else if(path.equals(METHOD_NAMES[3]))
		{
			String userName = request.getParameter("user");
			String password = request.getParameter("password");
			response.getWriter().write(exhibitorsService.loginByServlet(userName, password));
		}
		else if(path.equals(METHOD_NAMES[4]))
		{
			HttpSession session  = request.getSession();
			session.removeAttribute(Constants.SESSION_SHOW_NAME);
			session.removeAttribute(Constants.SESSION_SHOW_TYPE);
			response.getWriter().write("{\"logout\":" + true + "}");
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
