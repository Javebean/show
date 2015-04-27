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
	 * 分页查询展商简略信息id, orgName,industryType, region, phone, username, applyTime;
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getShortExhibitorsForPage(int start, int number)
	{
		return exhibitorsService.getShortExhibitorsForPage(start, number);
	}
	
	/**
	 * 分页查询已通过审核的展商简略信息id, industryType,orgName, region, phone, username, applyTime;
	 * 2015.4.17新增
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getExhibitorsForPageByState(int start, int number, int state)
	{
		return exhibitorsService.getShortExhibitorForPageByState(start, number,state);
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
	public boolean updateExhibitor(Exhibitors exhibitor)
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
}
