package com.novahome.dwr;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.TestClob;
import com.novahome.data.service.TestClobService;

@Scope("prototype")
@Component("testclob_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "testclob_dwr"), name = "testclob_dwr")
public class TestClobDwr {
	private static final Logger logger = Logger.getLogger(TestClobDwr.class);
	@Resource
	private TestClobService testClobService;
	
	@RemoteMethod
	public String saveTestClob(TestClob test) {
		HttpSession s=  WebContextFactory.get().getSession();
		logger.info("session id:" + s.getId());
		return testClobService.saveTestClob(test);
	}

}
