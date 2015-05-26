package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Visitor;
import com.novahome.data.service.VisitorService;


@Scope("prototype")
@Component("visitor_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "visitor_dwr"), name = "visitor_dwr")
public class VisitorDwr {

	@Resource
	private VisitorService visitorService;
	
	/**
	 * 获取现场证件申请数量
	 * @return
	 */
	@RemoteMethod
	public String getVisitorTotalCount() 
	{
		return visitorService.getVisitorTotalCount();
	}
	
	/**
	 * 获取不同类型的现场证件申请数量 1=展商， 2=观众（采购商），3=媒体
	 * @return
	 */
	@RemoteMethod
	public String getVisitorCountByType(int type) 
	{
		return visitorService.getVisitorCountByType(type);
	}
	
	/**
	 * 获取不同申请状态的现场证件申请数量 
	 * @return
	 */
	@RemoteMethod
	public String getVisitorCountByState(int state) 
	{
		return visitorService.getVisitorCountByState(state);
	}
	
	/**
	 * 存储现场证件申请信息，包括证件图片地址
	 * @param sceneServ
	 * @return
	 */
	@RemoteMethod
	public String saveVisitor(Visitor Visitor, String cutIndex)
	{
		return visitorService.saveVisitor(Visitor, cutIndex);
	}
	
	/**
	 * 通过展商id查找现场证件（只针对展商的现场证件）
	 * @param eid
	 * @return
	 */
	@RemoteMethod
	public String getVisitorByEid(String eid)
	{
		return visitorService.getVisitorByEid(eid);
	}
	
	/**
	 * 通过id获取现场证件信息
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getVisitorById(String id)
	{
		return visitorService.getVisitorById(id);
	}
	
	/**
	 * 通过公司名称获取现场证件
	 * @param org
	 * @return
	 */
	@RemoteMethod
	public String getVisitorByOrg(String org)
	{
		return visitorService.getVisitorByOrg(org);
	}
	
	/**
	 * 通过人员名称获取现场证件
	 * @param name
	 * @return
	 */
	@RemoteMethod
	public String getVisitorByName(String name)
	{
		return visitorService.getVisitorByName(name);
	}

	
	/**
	 * 分页查询现场证件申请信息
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPage(int start, int number)
	{
		return visitorService.getVisitorForPage(start, number);
	}
	
	/**
	 * 根据证件状态分页查询现场证件申请信息
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPageByState(int start, int number, int state)
	{
		return visitorService.getVisitorForPageByState(start, number, state);
	}
	
	/**
	 * 删除现场证件申请通过id
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteVisitorById(String id)
	{
		return visitorService.deleteVisitorById(id);
	}
	
	/**
	 * 更新现场证件申请信息
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitor(Visitor visitor)
	{
		return visitorService.updateVisitor(visitor);
	}
	
	/**
	 * 更新现场证件申请信息
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitorState(String id, int state)
	{
		return visitorService.updateVisitorState(id, state);
	}
}
