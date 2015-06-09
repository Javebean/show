package com.novahome.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.novahome.commonservice.Constants;

public class ConfigUtils {

	private static String prj= "ROOT";    
	private static String remote= "http://121.40.87.226/";    
	private static Properties props;
	
	public static void init()
	{
		Resource resource = new ClassPathResource("/config.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(props != null)
		{
			String prjTmp = (String) props.get("prjname");
			if(prjTmp != null && !prjTmp.isEmpty() && !prjTmp.trim().isEmpty())
				prj = prjTmp;
			String remotTmp = (String) props.get("remote");
			if(remotTmp != null && !remotTmp.isEmpty() && !remotTmp.trim().isEmpty())
				remote = remotTmp;
		}
	}
	
	public static String getPrj()
	{
		if(props == null)
		{
			synchronized(ConfigUtils.class)
			{
				if(props == null)
				{
					init();
				}
			}
		}
		return prj;
	}
	
	public static String getRemote()
	{
		if(props == null)
		{
			synchronized(ConfigUtils.class)
			{
				if(props == null)
				{
					init();
				}
			}
		}
		return remote;
	}
	
	public static void main(String[] args)
	{
		System.out.println(ConfigUtils.getPrj());
		System.out.println(ConfigUtils.getRemote());
		String imgSrc = ConfigUtils.getRemote() + ConfigUtils.getPrj() + Constants.BARCODE_MID_STR + "df.png";
		System.out.println(imgSrc);
		System.out.println(MailUtil.replaceVariable(Constants.VISITOR_APPROVED, imgSrc));
	}
	
}
