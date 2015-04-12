package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.Zlzx;
import com.novahome.data.service.ZlzxService;


@Scope("prototype")
@Component("zlzx_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "zlzx_dwr"), name = "zlzx_dwr")
public class ZlzxDwr {

	@Resource
	private ZlzxService zlzxService;
	
	/**
	 * 获取展览咨询新闻数量
	 * @return
	 */
	@RemoteMethod
	public String getZlzxTotalCount() 
	{
		return zlzxService.getZlzxTotalCount();
	}
	
	/**
	 * 存储一条展览咨询新闻
	 * @param zlzx
	 * @return
	 */
	@RemoteMethod
	public String saveZlzx(Zlzx zlzx)
	{
		return zlzxService.saveZlzx(zlzx);
	}
	
	/**
	 * 通过展览咨询标题查找
	 * @param title
	 * @return
	 */
	@RemoteMethod
	public String getZlzxByTitle(String title)
	{
		return zlzxService.getZlzxByTitle(title);
	}
	
	/**
	 * 通过id查找一条展览咨询新闻
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getZlzxById(String id)
	{
		return zlzxService.getZlzxById(id);
	}
	
	/**
	 * 分页查询展览咨询（所有信息，包含content）（不推荐）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getZlzxForPage(int start, int number)
	{
		return zlzxService.getZlzxForPage(start, number);
	}
	
	/**
	 * 分页查询展览资讯（精简版，不包含content）
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getShortZlzxForPage(int start, int number)
	{
		return zlzxService.getShortZlzxForPage(start, number);
	}
	
	/**
	 * 删除一条展览资讯
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteZlzxById(String id)
	{
		return zlzxService.deleteZlzxById(id);
	}
	
	/**
	 * 更新一条展览资讯
	 * @param zlzx
	 * @return
	 */
	@RemoteMethod
	public boolean updateZlzx(Zlzx zlzx)
	{
		return zlzxService.updateZlzx(zlzx);
	}
}
