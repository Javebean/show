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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.novahome.commonservice.Constants;
import com.novahome.commonservice.PeopleState;
import com.novahome.data.pojo.Audience;
import com.novahome.data.service.AudienceService;
import com.novahome.data.service.DisplayItemService;
import com.novahome.data.service.ZytzService;
import com.novahome.utils.HtmlParser;

public class AudienceServlet extends HttpServlet{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] METHOD_NAMES = {"login","logout","register"};
	private AudienceService audienceService;
	private static final Logger logger = Logger.getLogger(AudienceServlet.class);
	@Override
	public void init()
	{
		 ApplicationContext ctx = WebApplicationContextUtils
		    		.getRequiredWebApplicationContext(this.getServletConfig()
		    				.getServletContext());
		 audienceService = (AudienceService) ctx.getBean("audienceService");
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
			path = uri.substring(subStart+1,subEnd);
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		if(path.equals(METHOD_NAMES[0]))
		{
			String userName = request.getParameter("user");
			String password = request.getParameter("password");
			String ret = audienceService.loginByServlet(userName,password);
			JSONObject obj = null;
			try {
				obj = new JSONObject(ret);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean isLogin = (Boolean) obj.get("result");
			if(isLogin)
			{
				HttpSession session  = request.getSession();
				session.setAttribute(Constants.SESSION_SHOW_NAME, obj.get("username"));
				session.setAttribute(Constants.SESSION_SHOW_TYPE, PeopleState.AUDIENCE);
			}
			response.getWriter().write(obj.toString());
		}
		else if(path.equals(METHOD_NAMES[1]))
		{
			HttpSession session  = request.getSession();
			session.removeAttribute(Constants.SESSION_SHOW_NAME);
			session.removeAttribute(Constants.SESSION_SHOW_TYPE);
			response.getWriter().write("{\"logout\":" + true + "}");
		}
		else if(path.equals(METHOD_NAMES[2]))
		{
			Audience audience = processAudienceParams(request);
			response.getWriter().write(audienceService.saveAudience(audience));
		}
		else
		{
			response.getWriter().write(HtmlParser.NO_FOUND_MSG);
			return;
		}
	}

	private Audience processAudienceParams(HttpServletRequest request)
	{
		Audience au = new Audience();
		Map<String, String[]>map = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<String,String[]>entry = (Entry<String, String[]>) it.next();
			String key = entry.getKey();
			String[]str = entry.getValue();
			if(key.equals("buyer"))
			{
				continue;
			}
			Method method = getMehthods(Audience.class, key);
			if(method != null)
			{
				try {
					String value = str[0];
					method.invoke(au, value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
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
		au.setBuyer(buyer);
		return au;
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


	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
