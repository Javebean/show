package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 观众专题活动申请实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Event {

	private String id;	//id,系统生成
	private String aid;	//申请观众的id
	private String eventName;	//专题活动名称
	private Date startTime;	//活动开始时间，第一版本可以忽略此字段
	private Date endTime;	//活动结束时间，第一版本可以忽略此字段
	private String place;	//活动地点
	private String type;	//活动类型
	private String note;	//活动备注说明
	private String invitee;	//活动邀请对象
	private Date applyTime;	//活动申请时间，系统生成
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
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getInvitee() {
		return invitee;
	}
	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", aid=" + aid + ", eventName=" + eventName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", place=" + place + ", type=" + type + ", note=" + note
				+ ", invitee=" + invitee + ", applyTime=" + applyTime + "]";
	}
	
	
}
