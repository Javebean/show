package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.PictureTopic;
import com.novahome.data.pojo.Text;
import com.novahome.data.service.PictureTopicService;
import com.novahome.data.service.TextService;

@Scope("prototype")
@Component("pt_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "pt_dwr"), name = "pt_dwr")
public class PictureTopicDwr {
	@Resource
	private PictureTopicService ptService;
	
	@RemoteMethod
	public String savePT(PictureTopic pt) {
		return ptService.savePT(pt);
	}
	
	@RemoteMethod
	public Boolean updatePT(PictureTopic pt) {
		return ptService.updatePT(pt);
	}
	
	@RemoteMethod
	public Boolean deletePTById(String id) {
		String basePath = WebContextFactory.get().getServletContext().getRealPath(
				"/resources/topicimages/");
		return ptService.deletePTById(id,basePath);
	}
	
	@RemoteMethod
	public String getPTsByType(String type) {
		return ptService.getPTsByType(type);
	}
	
	@RemoteMethod
	public String getPTById(String id) {
		return ptService.getPTById(id);
	}
}
