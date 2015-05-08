package com.novahome.utils;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.novahome.data.service.ExhibitorsService;
public class MailUtil {

	private static Properties props;
    //SMTP服务器地址
    private static String smtpServer; 
    //登录SMTP服务器的用户名
    private static String username ;
    //登录SMTP服务器的密码
    private static String password ;
 
    private static ExecutorService exec = Executors.newFixedThreadPool(5); 
    
	private static final Logger logger = Logger.getLogger(MailUtil.class);

	
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
			password = (String) props.get("mail.password");
			username = (String) props.get("mail.user");
		}
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
					logger.debug("smtpServer:" + smtpServer + ";user:" + username);
				}
			}
		}
		exec.execute(new SendThread(subject, content, to));	
	}
	
	private static class SendThread implements Runnable
	{	
		 //邮件主题
	    private String subject; 
	    //邮件正文
	    private String content; 
	    
	    private String to;
		public SendThread(String subject, String content, String to)
		{
			this.subject = subject;
			this.content = content;
			this.to = to;
		}
	   
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 //创建邮件Session所需的Properties对象
			logger.info("smtpServer:" + smtpServer + ";user:" + username);
	        Properties props = new Properties();
	        props.put("mail.smtp.host" , smtpServer);
	        props.put("mail.smtp.auth" , "true");
	        //创建Session对象
	        Session session = Session.getDefaultInstance(props
	            //以匿名内部类的形式创建登录服务器的认证对象
	            , new Authenticator()
	            {
	                public PasswordAuthentication getPasswordAuthentication()
	                {
	                    return new PasswordAuthentication(username,password); 
	                }
	            });
	        try
	        {
	            //构造MimeMessage并设置相关属性值
	            MimeMessage msg = new MimeMessage(session);
	            //设置发件人
	            msg.setFrom(new InternetAddress(username));
	            //设置收件人
	            InternetAddress[] addresses = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO , addresses);
	            //设置邮件主题
	           // subject = transferChinese(subject);
	            msg.setSubject(subject);    
	            //构造Multipart
	            Multipart mp = new MimeMultipart();
	            //向Multipart添加正文
	            MimeBodyPart mbpContent = new MimeBodyPart();
	            //mbpContent.setText(content);
	            mbpContent.setContent(content, "text/html;charset=utf-8");

	            //将BodyPart添加到MultiPart中
	            mp.addBodyPart(mbpContent);
	            //向Multipart添加附件
	            //遍历附件列表，将所有文件添加到邮件消息里
	            
	            //向Multipart添加MimeMessage
	            msg.setContent(mp);
	            //设置发送日期
	            msg.setSentDate(new Date());
	            //发送邮件
	            Transport.send(msg);
	        }
	        catch (MessagingException mex)
	        {
	            mex.printStackTrace();
	        }
			logger.info("发送邮件成功");

		}
	}
	
}
