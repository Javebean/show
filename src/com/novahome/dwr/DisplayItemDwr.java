package com.novahome.dwr;

import java.util.List;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.DisplayItem;
import com.novahome.data.service.DisplayItemService;

@Scope("prototype")
@Component("displayItem_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "displayItem_dwr"), name = "displayItem_dwr")
public class DisplayItemDwr {

	@Resource
	private DisplayItemService displayItemService;
	
	/**
	 * 获取展品类型数量
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemTotalCount() 
	{
		return displayItemService.getDisplayItemTotalCount();
	}
	
	/**
	 * 通过展商id获取展商现场施工数量
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemCountByEid(String eid) 
	{
		return displayItemService.getDisplayItemCountByEid(eid);
	}
	
	/**
	 * 存储展品
	 * @param displayItem
	 * @return
	 */
	@RemoteMethod
	public String saveDisplayItem(DisplayItem displayItem)
	{
		return displayItemService.saveDisplayItem(displayItem);
	}
	
	/**
	 * 通过公司username查找展品
	 * @param username
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemByUsername(String username)
	{
		return displayItemService.getDisplayItemByUsername(username);
	}
	
	/**
	 * 更新公司展品通过username
	 * @param username
	 * @return
	 */
	@RemoteMethod
	public boolean updateDisplayItemList(String username, List<DisplayItem>list)
	{
		return displayItemService.updateDisplayItemList(username, list);
	}
	
	/**
	 * 通过公司id查找展品
	 * @param eid
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemByEid(String eid)
	{
		return displayItemService.getDisplayItemByEid(eid);
	}
	
	/**
	 * 通过id获取展品
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemById(String id)
	{
		return displayItemService.getDisplayItemById(id);
	}
	
	/**
	 * 分页查询展品
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getDisplayItemForPage(int start, int number)
	{
		return displayItemService.getDisplayItemForPage(start, number);
	}
	
	/**
	 * 删除展品通过id
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteDisplayItemById(String id)
	{
		return displayItemService.deleteDisplayItemById(id);
	}
	
	/**
	 * 更新展品
	 * @param zytz
	 * @return
	 */
	@RemoteMethod
	public boolean updateDisplayItem(DisplayItem DisplayItem)
	{
		return displayItemService.updateDisplayItem(DisplayItem);
	}

}
