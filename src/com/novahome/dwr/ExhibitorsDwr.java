package com.novahome.dwr;

import java.util.List;
import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Construction;
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.pojo.Exhibitors;
import com.novahome.data.pojo.SceneServ;
import com.novahome.data.pojo.Transportation;
import com.novahome.data.pojo.Visitor;
import com.novahome.data.service.ExhibitorsService;

@Scope("prototype")
@Component("exhibitor_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "exhibitor_dwr"), name = "exhibito_dwr")
public class ExhibitorsDwr {

	@Resource
	private ExhibitorsService exhibitorsService;
	
	/**
	 * 获取展商数目
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsTotalCount()
	{
		return exhibitorsService.getExhibitorsTotalCount();
	}
	
	/**
	 * 获取审批过的展商数目 2015.4.17新增
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsCountByState(int state)
	{
		return exhibitorsService.getExhibitorsCountByState(state);
	}
	
	/**
	 * 通过展商id获取展商
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorById(String id)
	{
		
		return exhibitorsService.getExhibitorById(id);
	}
	
	/**
	 * 通过展商id获取展商相关所有信息，包括参会人员、展览产品、申请的运输、现场、施工服务
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getTotalExhibitInfoById(String id)
	{
		return exhibitorsService.getTotalExhibitInfoById(id);
	}
	
	/**
	 * 通过展商username获取展商信息
	 * @param username
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorByUserName(String username)
	{
		return exhibitorsService.getExhibitorByUserName(username);
	}
	
	/**
	 * 通过展商username获取展商信息
	 * @param username
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorByUserNamePure(String username)
	{
		return exhibitorsService.getExhibitorByUserNamePure(username);
	}
	
	/**
	 * 分页查询展商简略信息id, orgName,industryType, region, phone, username, applyTime;
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getShortExhibitorsForPage(int start, int number, String orgName)
	{
		return exhibitorsService.getShortExhibitorsForPageFinalAudit(start, number, orgName);
	}
	
	/**
	 * 分页查询已通过初次审核的展商简略信息id, industryType,orgName, region, phone, username, applyTime;
	 * 如果state 为 负数，查看所有已通过初次审核的展商
	 * 
	 * 
	 * 2015.4.17新增
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPageByState(int start, int number, int state, String orgName)
	{
		return exhibitorsService.getShortExhibitorForPageByStateFinalAudit(start, number,state, orgName);
	}
	
	
	/**
	 * 分页查询已通过初次审核的展商简略信息id, industryType,orgName, region, phone, username, applyTime;
	 * 如果state 为 负数，查看所有已通过初次审核的展商
	 * 
	 * 
	 * 2015.8.26新增(根据穆东成2015年8月26日早上电话需要增加）
	 * 在原来过滤添加 展商状态state、公司名称orgName基础上增加新的过滤条件
	 * 招展引荐单位:recommender
	 * 
	 * 后续如果继续增加添加，改变此接口，string改为 string...
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPageByStateMutipleCon(int start, int number, int state, String orgName,String recommender)
	{
		return exhibitorsService.getShortExhibitorForPageByStateFinalAuditMutipleCon(start, number,state, orgName,recommender);
	}
	
	/**
	 * 分页查询刚刚申请的展商简略信息id, industryType,orgName, region, phone, username, applyTime;
	 * 
	 * 2015.7.2新增
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPageByStateFirst(int start, int number, int state, String orgName)
	{
		return exhibitorsService.getShortExhibitorForPageByStateFirst(start, number,state, orgName);
	}
	
	
	/**
	 * 门户网站所用方法
	 * 根据logo顺序查询
	 * 2015.6.1新增
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPageByStateLogoOrder(int start, int number, int state, String orgName)
	{
		return exhibitorsService.getShortExhibitorForPageByStateLogoOrder(start, number,state, orgName);
	}
	/**
	 * 分页查询展商信息
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPage(int start, int number)
	{
		return exhibitorsService.getExhibitorForPage(start, number);
	}
	
	/**
	 * 存储展商信息
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public String saveExhibitor(Exhibitors exhibitor)
	{
		return exhibitorsService.saveExhibitor(exhibitor);
	}
	 
	/**
	 * 存储展商全部信息，包括展品、参会人员、现场服务申请、施工服务申请、运输服务申请
	 * @param exhibitor
	 * @param construction
	 * @param transportation
	 * @param sceneServ
	 * @param visitor
	 * @param displayItem
	 * @return
	 */
	@RemoteMethod
	public String saveTotalExhibitInfo(Exhibitors exhibitor, List<Construction>construction,
			List<Transportation>transportation, List<SceneServ>sceneServ, List<Visitor>visitor, List<DisplayItem>displayItem)
	{
		return exhibitorsService.saveTotalExhibitInfo(exhibitor, construction, transportation, sceneServ, visitor, displayItem);
	}
	
	/**
	 * 通过id删除展商信息
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteExhibitorById(String id)
	{
		return exhibitorsService.deleteExhibitorById(id);
	}
	
	/**
	 * 更新展商信息
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public String updateExhibitor(Exhibitors exhibitor)
	{
		return exhibitorsService.updateExhibitor(exhibitor);
	}
	
	/**
	 * 更新展商状态信息
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public boolean updateExhibitorState(String id, int state)
	{
		return exhibitorsService.updateExhibitorState(id, state);
	}

	/**
	 * 更新展商状态信息和设置驳回原因
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public boolean updateExhibitorStateReason(String id, int state, String reason)
	{
		return exhibitorsService.updateExhibitorStateReason(id, state, reason);
	}
	
	/**
	 * 更新展商状态信息,初次审批
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public boolean updateExhibitorFirstState(String id, int state)
	{
		return exhibitorsService.updateExhibitorFirstState(id, state);
	}

	/**
	 * 更新展商状态信息和设置驳回原因
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public boolean updateExhibitorFirstStateReason(String id, int state, String reason)
	{
		return exhibitorsService.updateExhibitorFirstStateReason(id, state, reason);
	}
	
	
	/**
	 * 变更展商登录密码
	 * @param exhibitor
	 * @return
	 */
	@RemoteMethod
	public String updateExhibitorPsw(String id, String psw)
	{
		return exhibitorsService.updateExhibitorPsw(id, psw);
	}
	
	/**
	 * 展商登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	@RemoteMethod
	public String login(String userName, String password)
	{
		return exhibitorsService.login(userName, password);
	}
	
	/**
	 * 展商登出
	 * 
	 * @return
	 */
	@RemoteMethod
	public boolean logout()
	{
		return exhibitorsService.logout();
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String resetPwd(String id)
	{
		return exhibitorsService.resetPsw(id);
	}
	
	
	/**
	 * 获取展商引荐单位及相关引荐数量
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getRecommenderStat()
	{
		return exhibitorsService.getRecommenderStat();
	}
	
	
	/**
	 * 获取展商地区及相关数量
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getRegionStat()
	{
		return exhibitorsService.getRegionStat();
	}
	
	/**
	 * 获取展商地区及相关数量
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getRegionStatByGroup()
	{
		return exhibitorsService.getRegionStatByGroup();
	}
	
}
