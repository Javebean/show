package com.novahome.utils;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;


public class MailUtil {

	private static Properties props;
    //SMTP服务器地址
    private static String smtpServer; 
/*    //登录SMTP服务器的用户名
    private static String username ;
    //登录SMTP服务器的密码
    private static String password ;*/
 
    private static final int MAX_NUM = 1;
    
    private static ExecutorService exec = Executors.newFixedThreadPool(MAX_NUM); 
    
	private static final Logger logger = Logger.getLogger(MailUtil.class);

	private static LinkedBlockingQueue<MailUser> queue = new LinkedBlockingQueue<MailUser>(MAX_NUM) ;
	
	public static void init()
	{
		Resource resource = new ClassPathResource("/mail.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(props != null)
		{
			smtpServer = (String) props.get("mail.smtpServer");
			String password = (String) props.get("mail.password");
			String username = (String) props.get("mail.user");
		/*	
			String user1 = (String) props.get("mail.user1");
			String psw1 = (String) props.get("mail.password1");
			*/
			MailUser mailUser = new MailUser(username, password);
			//MailUser mailUser1 = new MailUser(user1, psw1);
			try {
				queue.put(mailUser);
				//queue.put(mailUser1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		sendMail("540380151@qq.com","test1", "hello");
		/*sendMail("540380151@qq.com","test2", "hello");
		sendMail("540380151@qq.com","test3", "hello");
		sendMail("540380151@qq.com","test4", "hello");
		sendMail("540380151@qq.com","test5", "hello");*/
	}
    
	public static void sendMail(String to, String subject, String content)
	{
		if(props == null)
		{
			synchronized(MailUtil.class)
			{
				if(props == null)
				{
					init();
				}
			}
		}
		exec.execute(new SendThread(subject, content, to, smtpServer, queue));	
	}
	
	
	public static String replaceVariable(String content, String...args)
	{
		int index = 0;
		for(String arg : args){
			content = content.replace("[arg" + index +"]", arg);
			index ++;
		}	
		return content;
	}
	
	
}
