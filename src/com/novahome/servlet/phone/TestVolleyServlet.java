package com.novahome.servlet.phone;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class TestVolleyServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*String user = request.getParameter("j");
	    String name = request.getParameter("name");
	    String password = request.getParameter("password");*/
		String str = request.getParameter("test");
		System.out.println("test:" + str);
	    //JSON对象
	    JSONObject jsonObject = new JSONObject();
	    try {
			jsonObject.accumulate("name","giot").accumulate("job", "diaosi");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   // response.setContentType("application/json");
	   
		response.setContentType("text/plain;charset=UTF-8");
		String ret = jsonObject.toString();
	   // String ret = "dfhdf";
	   // response.getWriter().write(jsonObject.toString());
	 //   String ret = "{'returncode':0,'message':'ok','result':{'fctlist':[{'name':'法拉利','serieslist':[{'id':676,'name':'California','levelname':'跑车','price':'308.80万'},{'id':889,'name':'法拉利458','levelname':'跑车','price':'388.00-448.00万'}}]}]}}";
	    response.getWriter().write(ret);
	}

	/*@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}*/

}
