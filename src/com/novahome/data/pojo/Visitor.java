package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 现场证件申请实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Visitor {

	private String id;	//id,系统生成
	private String eid;	//对应展商id
	private String name;	//姓名
	private String sex;		//性别
	private String position;	//职务
	private String phone;	//号码
	private String email;	//电子邮箱
	private String photo;	//上传照片地址
	private int type = 2;	//证件类型，1 = 展商，2=观众， 3= 媒体，缺省为观众
	private int buyer = 0;	//是否为采购商， 1=是采购商，0= 不是采购商，缺省为非采购商
	private String org;	//所属公司名称
	private int state = 0;	//申请状态  state=0 申请；1 =批准； 2=驳回
	private String reason; //驳回原因
	private Date applyTime;	//申请时间，系统生成
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
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBuyer() {
		return buyer;
	}
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
