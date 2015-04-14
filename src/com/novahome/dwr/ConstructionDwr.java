package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Construction;
import com.novahome.data.service.ConstructionService;


@Scope("prototype")
@Component("construction_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "construction_dwr"), name = "construction_dwr")
public class ConstructionDwr {
	
	@Resource
	private ConstructionService constructionService;
	
	/**
	 * 获取现场施工数量
	 * @return
	 */
	@RemoteMethod
	public String getConstructionTotalCount() 
	{
		return constructionService.getConstructionTotalCount();
	}
	
	/**
	 * 通过展商id获取展商现场施工数量
	 * @return
	 */
	@RemoteMethod
	public String getConstructionCountByEid(String eid) 
	{
		return constructionService.getConstructionCountByEid(eid);
	}
	
	/**
	 * 存储现场施工信息，包括上传的图
	 * @param construction
	 * @return
	 */
	@RemoteMethod
	public String saveConstruction(Construction construction)
	{
		return constructionService.saveConstruction(construction);
	}
	
	/**
	 * 通过公司id查找现场施工
	 * @param eid
	 * @return
	 */
	@RemoteMethod
	public String getConstructionByEid(String eid)
	{
		return constructionService.getConstructionByEid(eid);
	}
	
	/**
	 * 通过id获取现场施工
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getConstructionById(String id)
	{
		return constructionService.getConstructionById(id);
	}
	
	/**
	 * 分页查询现场施工
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getConstructionForPage(int start, int number)
	{
		return constructionService.getConstructionForPage(start, number);
	}
	
	/**
	 * 删除现场施工通过id
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteConstructionById(String id)
	{
		return constructionService.deleteConstructionById(id);
	}
	
	/**
	 * 更新现场施工
	 * @param construction
	 * @return
	 */
	@RemoteMethod
	public boolean updateConstruction(Construction construction)
	{
		return constructionService.updateConstruction(construction);
	}

}
