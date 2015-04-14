package com.novahome.dwr;

import javax.annotation.Resource;

import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.novahome.data.pojo.Transportation;
import com.novahome.data.service.TransportationService;

@Scope("prototype")
@Component("transportation_dwr")
@RemoteProxy(creator = SpringCreator.class, creatorParams = @Param(name = "beanName", value = "transportation_dwr"), name = "transportation_dwr")
public class TransportationDwr {

	@Resource
	private TransportationService transportationService;
	
	/**
	 * 获取运输服务申请数量
	 * @return
	 */
	@RemoteMethod
	public String getTransportationTotalCount() 
	{
		return transportationService.getTransportationTotalCount();
	}
	
	/**
	 * 通过展商id获取运输服务申请数量
	 * @return
	 */
	@RemoteMethod
	public String getTransportationCountByEid(String eid) 
	{
		return transportationService.getTransportationCountByEid(eid);
	}
	
	/**
	 * 存储运输服务申请信息
	 * @param sceneServ
	 * @return
	 */
	@RemoteMethod
	public String saveTransportation(Transportation transportation)
	{
		return transportationService.saveTransportation(transportation);
	}
	
	/**
	 * 通过公司id查找运输服务申请
	 * @param eid
	 * @return
	 */
	@RemoteMethod
	public String getTransportationByEid(String eid)
	{
		return transportationService.getTransportationByEid(eid);
	}
	
	/**
	 * 通过id获取运输服务申请
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public String getTransportationById(String id)
	{
		return transportationService.getTransportationById(id);
	}
	
	/**
	 * 分页查询运输服务申请
	 * @param start
	 * @param number
	 * @return
	 */
	@RemoteMethod
	public String getTransportationForPage(int start, int number)
	{
		return transportationService.getTransportationForPage(start, number);
	}
	
	/**
	 * 删除运输服务申请通过id
	 * @param id
	 * @return
	 */
	@RemoteMethod
	public long deleteTransportationById(String id)
	{
		return transportationService.deleteTransportationById(id);
	}
	
	/**
	 * 更新现场服务申请
	 * @param transportation
	 * @return
	 */
	@RemoteMethod
	public boolean updateTransportation(Transportation transportation)
	{
		return transportationService.updateTransportation(transportation);
	}

}
