package com.novahome.data.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novahome.data.dao.TestClobDao;

import com.novahome.data.pojo.TestClob;

@Service("testClobService")
@Transactional(readOnly = false)
@Repository
public class TestClobService {

	@Resource(name = "testClobDao")
	private TestClobDao testClobDao;
	
	public String saveTestClob(TestClob test) {
		
		test.setId("12");
		test.setContent("哈哈ffffffffffff哈哈fffffff");
		test.setName("d大幅度df");
		
		String dateStr = "2008-08-08 23:59:59"; 
		SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date;
		try {
			date = myformat.parse(dateStr);
			test.setPublish(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		testClobDao.saveTestClob(test);
		JSONObject jo = new JSONObject(test);
		return jo.toString();
	}
	
	
	
	
}
