package com.novahome.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
 
/**
 * 获取spring容器，以访问容器中定义的其他bean
 * <a href="http://my.oschina.net/arthor" class="referer" target="_blank">@author</a>  lyltiger
 * <a href="http://my.oschina.net/u/266547" class="referer" target="_blank">@since</a>  MOSTsView 3.0 2009-11-16
 */
public class SpringContextUtil implements ApplicationContextAware{
 
    private static ApplicationContext   applicationContext;
 
   
    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    public void setApplicationContext(ApplicationContext applicationContext){
        SpringContextUtil.applicationContext = applicationContext;
    }
 
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
 
   
    public static Object getBean(String name) throws BeansException{
        return applicationContext.getBean(name);
    }
    
   
}