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
	 * 分页查询现场证件申请信息
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPage(int start, int number, String name)
	{
		return visitorService.getVisitorForPage(start, number, name);
	}
	
	/**
	 * 根据证件状态分页查询现场证件申请信息
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPageByState(int start, int number, int state, String name)
	{
		return visitorService.getVisitorForPageByState(start, number, state, name);
	}
	
	/**
	 * 根据证件状态分页查询现场证件申请信息
	 * 增加根据证件类型等添加查询
	 * 2015年8月27日穆东成提出需求
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPageByStateMutipleCon(int start, int number, int state, String name, int type)
	{
		return visitorService.getVisitorForPageByStateMutipleCon(start, number, state, name, type);
	}
	
	/**
	 * 根据证件状态分页查询现场证件申请信息,初审证件
	 * 增加根据证件类型等添加查询
	 * 2015年9月9日穆东成提出需求
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPageByMutipleConFirstAudit(int start, int number, int firstState, int type, String... con)
	{
		return visitorService.getVisitorForPageByMutipleConFirstAudit(start, number, firstState, type, con);
	}
	
	/**
	 * 根据证件状态分页查询现场证件申请信息，终审证件
	 * 增加根据证件类型等添加查询
	 * 2015年9月9日穆东成提出需求
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getVisitorForPageByMutipleConFinal(int start, int number, int state, int type, String... con)
	{
		return visitorService.getVisitorForPageByMutipleConFinal(start, number, state, type, con);
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
	 * 终极审批，更新现场证件申请状态
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitorState(String id, int state)
	{
		return visitorService.updateVisitorState(id, state);
	}
	
	/**
	 * 终极审批，更新现场证件申请状态和驳回原因
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitorStateReason(String id, int state, String reason)
	{
		return visitorService.updateVisitorStateReason(id, state, reason);
	}
	
	/**
	 * 一级审批，更新现场证件申请状态
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitorFirstState(String id, int firstState)
	{
		return visitorService.updateVisitorFirstState(id, firstState);
	}
	
	/**
	 * 一级审批，更新现场证件申请状态和驳回原因
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateVisitorFirstStateReason(String id, int firstState, String reason)
	{
		return visitorService.updateVisitorFirstStateReason(id, firstState, reason);
	}
	
}
