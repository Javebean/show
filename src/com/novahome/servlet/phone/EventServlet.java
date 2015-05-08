package com.novahome.servlet.phone;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.novahome.data.pojo.Event;
import com.novahome.data.service.EventService;
import com.novahome.utils.HtmlParser;

public class EventServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] METHOD_NAMES = {"apply","pagelist","getbypeople","details"};
	private EventService eventService;
	
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 eventService = (EventService) ctx.getBean("eventService");    		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException
	{
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
			Event event = processEventParams(request);
			response.getWriter().write(eventService.saveEvent(event));
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
			
			response.getWriter().write(eventService.getEventForPage(start, number));
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			String aid = request.getParameter("aid");
			response.getWriter().write(eventService.getEventByPeople(aid));
		}
		else if(path.equals(METHOD_NAMES[3]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(eventService.getPeopleEventById(id));
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
			
	}
	
	private Event processEventParams(HttpServletRequest request)
	{
		Event event = new Event();
		Map<String, String[]>map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,String[]>entry = (Entry<String, String[]>) it.next();
			String key = entry.getKey();
			String[]str = entry.getValue();
			Method method = getMehthods(Event.class, key);
			if(method != null)
			{
				try {
					String value = new String(str[0].getBytes("ISO-8859-1"),"utf-8");
					method.invoke(event, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return event;
	}
	
	private Method getMehthods(Class clz, String name)
	{
		Method method = null;
		String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		try {
			method = clz.getDeclaredMethod(methodName, String.class);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return method;
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}
