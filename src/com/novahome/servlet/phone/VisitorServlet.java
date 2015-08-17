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

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.novahome.data.pojo.Visitor;
import com.novahome.data.service.VisitorService;
import com.novahome.utils.HtmlParser;

public class VisitorServlet extends HttpServlet{

private static final long serialVersionUID = 1L;

	private VisitorService visitorService;
	private static final String[] METHOD_NAMES = {"apply","details","getbyorgid","getbyorgname","getbyid","applyByIos"};
	private static final Logger logger = Logger.getLogger(VisitorServlet.class);
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 visitorService = (VisitorService) ctx.getBean("visitorService");
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
		//java.net.URLDecoder.decode("http://120.26.207.77:8080/show/phone/visitor/apply.do?name=%E5%BC%A0%E5%AE%89&org=%E5%85%AC%E5%8F%B8&phone=09923&position=&email=poo@163.com&idNo=2209&sex=%E7%94%B7&type=2&photo=&idFont=&idBack=&idType=3","UTF-8");

		if(subStart < subEnd)
		{
			path = uri.substring(subStart+1,subEnd);
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
		logger.info("request visitor phone servlet:" + path);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		if(path.equals(METHOD_NAMES[0]))
		{
			Visitor visitor = processVisitorParams(request);
			response.getWriter().write(visitorService.saveVisitor(visitor,null));
		}
		else if(path.equals(METHOD_NAMES[1]))
		{
			String id = request.getParameter("id");
			response.getWriter().write(visitorService.getVisitorById(id));
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			String eid = request.getParameter("eid");
			response.getWriter().write(visitorService.getVisitorByEid(eid));
		}
		else if(path.equals(METHOD_NAMES[3]))
		{
			String org;
			String orgWild = request.getParameter("org");
			if(orgWild != null && !orgWild.isEmpty())
				org = new String(orgWild.getBytes("ISO-8859-1"),"utf-8");
			else
			{
				response.getWriter().write("{\"error\":\"org参数缺失\"}");
				return;
			}
			response.getWriter().write(visitorService.getVisitorByOrg(org));
		}
		else if(path.equals(METHOD_NAMES[4]))
		{
			String id;
			String idWild = request.getParameter("id");
			if(idWild != null && !idWild.isEmpty())
				id = new String(idWild.getBytes("ISO-8859-1"),"utf-8");
			else
			{
				response.getWriter().write("{\"error\":\"id参数缺失\"}");
				return;
			}
			response.getWriter().write(visitorService.getVisitorById(id));
		}
		else if(path.equals(METHOD_NAMES[5]))
		{
			Visitor visitor = processVisitorParamsByIos(request);
			response.getWriter().write(visitorService.saveVisitor(visitor,null));
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}

	}

	private Visitor processVisitorParams(HttpServletRequest request)
	{
		Visitor visitor = new Visitor();
		Map<String, String[]>map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,String[]>entry = (Entry<String, String[]>) it.next();
			String key = entry.getKey();
			String[]str = entry.getValue();
			if(key.equals("buyer") || key.equals("type")|| key.equals("idType"))
			{
				continue;
			}
			Method method = getMehthods(Visitor.class, key);
			if(method != null)
			{
				try {
				//	String value = new String(str[0].getBytes("ISO-8859-1"),"utf-8");
					String value = str[0];
					//System.out.println("before transfer:" + value);

		            try {  
		            	value = new String(value.getBytes("UTF-8"),  "utf-8");
						//System.out.println("after transfer:" + value);

		            } catch (UnsupportedEncodingException e) {  
		                e.printStackTrace();  
		                  
		                value = "decode error";  
		            }  
 
					method.invoke(visitor, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} /*catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		}
		String buyerStr = request.getParameter("buyer");
		int buyer ;
		if(buyerStr == null || buyerStr.isEmpty())
			buyer = 0;
		else
			buyer = Integer.parseInt(buyerStr);
		visitor.setBuyer(buyer);

		String typeStr = request.getParameter("type");
		int type ;
		if(typeStr == null || typeStr.isEmpty())
			type = 2;
		else
			type = Integer.parseInt(typeStr);
		visitor.setType(type);

		String idTypeStr = request.getParameter("idType");
		int idType ;
		if(idTypeStr == null || idTypeStr.isEmpty())
			idType = 0;
		else
			idType = Integer.parseInt(idTypeStr);
		visitor.setIdType(idType);

		return visitor;
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
			logger.warn("no such method for "+ methodName);
		}
		return method;
	}


	private Visitor processVisitorParamsByIos(HttpServletRequest request)
	{
		Visitor visitor = new Visitor();
		Map<String, String[]>map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,String[]>entry = (Entry<String, String[]>) it.next();
			String key = entry.getKey();
			String[]str = entry.getValue();
			if(key.equals("buyer") || key.equals("type")|| key.equals("idType"))
			{
				continue;
			}
			Method method = getMehthods(Visitor.class, key);
			if(method != null)
			{
				try {
					
					String value = new String(str[0].getBytes("ISO-8859-1"),"utf-8");
					
					method.invoke(visitor, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} /*catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/ catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		String buyerStr = request.getParameter("buyer");
		int buyer ;
		if(buyerStr == null || buyerStr.isEmpty())
			buyer = 0;
		else
			buyer = Integer.parseInt(buyerStr);
		visitor.setBuyer(buyer);

		String typeStr = request.getParameter("type");
		int type ;
		if(typeStr == null || typeStr.isEmpty())
			type = 2;
		else
			type = Integer.parseInt(typeStr);
		visitor.setType(type);

		String idTypeStr = request.getParameter("idType");
		int idType ;
		if(idTypeStr == null || idTypeStr.isEmpty())
			idType = 0;
		else
			idType = Integer.parseInt(idTypeStr);
		visitor.setIdType(idType);

		return visitor;
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
