package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.novahome.data.pojo.SceneServ;

import com.novahome.data.service.SceneServService;

@Scope("prototype")
@Component("sceneServ_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "sceneServ_dwr"), name = "sceneServ_dwr")
public class SceneServDwr {

	@Resource
	private SceneServService sceneServService;
	
	/**
	 * 获取现场服务申请数量
	 * @return
	 */
	@RemoteMethod
	public String getSceneServTotalCount() 
	{
		return sceneServService.getSceneServTotalCount();
	}
	
	/**
	 * 通过展商id获取现场服务申请数量
	 * @return
	 */
	@RemoteMethod
	public String getSceneServCountByEid(String eid) 
	{
		return sceneServService.getSceneServCountByEid(eid);
	}
	
	/**
	 * 存储现场服务申请信息
	 * @param sceneServ
	 * @return
	 */
	@RemoteMethod
	public String saveSceneServ(SceneServ sceneServ)
	{
		return sceneServService.saveSceneServ(sceneServ);
	}
	
	/**
	 * 通过公司id查找现场服务申请
	 * @param eid
	 * @return
	 */
	@RemoteMethod
	public String getSceneServByEid(String eid)
	{
		return sceneServService.getSceneServByEid(eid);
	}
	
	/**
	 * 通过id获取现场服务申请
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getSceneServById(String id)
	{
		return sceneServService.getSceneServById(id);
	}
	
	/**
	 * 分页查询现场服务申请
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getSceneServForPage(int start, int number)
	{
		return sceneServService.getSceneServForPage(start, number);
	}
	
	/**
	 * 删除现场服务申请通过id
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteSceneServById(String id)
	{
		return sceneServService.deleteSceneServById(id);
	}
	
	/**
	 * 更新现场服务申请
	 * @param sceneServ
	 * @return
	 */
	@RemoteMethod
	public boolean updateSceneServ(SceneServ sceneServ)
	{
		return sceneServService.updateSceneServ(sceneServ);
	}

	/**
	 * 获取现场服务统计信息
	 * @param sceneServ
	 * @return
	 */
	@RemoteMethod
	public String getSceneServStat()
	{
		return sceneServService.getSceneServStat();
	}
	
	
}
