package com.novahome.data.service;


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
		
		test.setId("11");
		test.setContent("dfsdfdsfffffffffffffffffffff");
		test.setName("dfdf");
		testClobDao.saveTestClob(test);
		JSONObject jo = new JSONObject(test);
		return jo.toString();
	}
	
	
	
	
}
