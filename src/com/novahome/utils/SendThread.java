package com.novahome.utils;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

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


public class SendThread implements Runnable{
	
	private static final Logger logger = Logger.getLogger(SendThread.class);
	
	private LinkedBlockingQueue<MailUser> queue;
	//邮件主题
    private String subject; 
    //邮件正文
    private String content; 
    private String to;
    private String smtpServer;
    private MailUser mailUser = null;
    
	public SendThread(String subject, String content, String to, String server, LinkedBlockingQueue<MailUser> queue)
	{
		this.subject = subject;
		this.content = content;
		this.to = to;
		this.smtpServer = server;
		this.queue = queue;
	}
   
	@Override
	public void run() {
		
		// TODO Auto-generated method stub
		 //创建邮件Session所需的Properties对象
		try {
			mailUser = queue.take();
			logger.info("smtpServer:" + smtpServer + ";user:" + mailUser.getUser());
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
                		return new PasswordAuthentication(mailUser.getUser(),mailUser.getPassword()); 
                	}
            	});
			try
			{
	            //构造MimeMessage并设置相关属性值
	            MimeMessage msg = new MimeMessage(session);
	            //设置发件人
	            msg.setFrom(new InternetAddress(mailUser.getUser()));
	            
	            //设置收件人
	            InternetAddress[] addresses = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO , addresses);
	            logger.debug("from " + mailUser.getUser() + " to " + to);
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
			finally
	        {
	        	queue.put(mailUser);
	        }
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		logger.info("发送邮件成功");

	}

}
