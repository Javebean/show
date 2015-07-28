package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Hyzx;
import com.novahome.data.service.HyzxService;



@Scope("prototype")
@Component("hyzx_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "hyzx_dwr"), name = "hyzx_dwr")
public class HyzxDwr {

	@Resource
	private HyzxService hyzxService;
	
	/**
	 * 获取行业咨询新闻数量
	 * @return
	 */
	@RemoteMethod
	public String getHyzxTotalCount() 
	{
		return hyzxService.getHyzxTotalCount();
	}
	
	/**
	 * 存储一条行业咨询新闻
	 * @param Hyzx
	 * @return
	 */
	@RemoteMethod
	public String saveHyzx(Hyzx hyzx)
	{
		return hyzxService.saveHyzx(hyzx);
	}
	
	/**
	 * 通过行业咨询标题查找
	 * @param title
	 * @return
	 */
	@RemoteMethod
	public String getHyzxByTitle(String title)
	{
		return hyzxService.getHyzxByTitle(title);
	}
	
	/**
	 * 通过id查找一条行业咨询新闻
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getHyzxById(String id)
	{
		return hyzxService.getHyzxById(id);
	}
	
	/**
	 * 分页查询行业咨询（所有信息，包含content）（不推荐）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getHyzxForPage(int start, int number)
	{
		return hyzxService.getHyzxForPage(start, number);
	}
	
	/**
	 * 分页查询行业资讯（精简版，不包含content）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getShortHyzxForPage(int start, int number)
	{
		return hyzxService.getShortHyzxForPage(start, number);
	}
	
	/**
	 * 删除一条行业资讯
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteHyzxById(String id)
	{
		return hyzxService.deleteHyzxById(id);
	}
	
	/**
	 * 更新一条行业资讯
	 * @param hyzx
	 * @return
	 */
	@RemoteMethod
	public boolean updateHyzx(Hyzx hyzx)
	{
		return hyzxService.updateHyzx(hyzx);
	}
}
