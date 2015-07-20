package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 展览资讯实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Zlzx {

	private String id; //id,系统生成 
	private String soruce;	//新闻来源
	private Date publishTime;	//发布时间
	private String title;	//新闻标题
	private String content;	//新闻内容
	private String abs;		//摘要
	private int state = 0; //默认正常状态，state=0 ；1 驳回；
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSoruce() {
		return soruce;
	}
	public void setSoruce(String soruce) {
		this.soruce = soruce;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getAbs() {
		return abs;
	}
	public void setAbs(String abs) {
		this.abs = abs;
	}
	public Zlzx() {
	}
	public Zlzx(String soruce, Date publishTime, String title, String content,
			String abs, int state) {
		this.soruce = soruce;
		this.publishTime = publishTime;
		this.title = title;
		this.content = content;
		this.abs = abs;
		this.state = state;
	}
	
	
}
