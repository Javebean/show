package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 重要通知实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Zytz {

	private String id;	//id，系统生成
	private String soruce;	//信息来源
	private Date publishTime;	//新闻发布时间
	private String title;	//新闻 标题
	private String content;	//新闻内容
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
	
	
}
