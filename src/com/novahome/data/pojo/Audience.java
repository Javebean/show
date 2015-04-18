package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
/**
 * 观众实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Audience {

	private String id;	//id，系统生成id
	private String name;	//名字
	private String sex; //性别，sex = 0 男；1女
	private String position; //职位
	private String phone;	//电话号码
	private String email;	//电子邮件
	private int buyer = 0; //默认不是buyer
	private String inviter;	//邀请人
	private String infoSource;	//消息来源
	private String org;		//公司名称
	private String username;	//用户名（系统生成）
	private String password;	//用户密码（系统生成）
	private String address;		//地址
	private String target;		//参会目的
	private int state =0;		//默认正常状态，state=0 申请；1 =批准； 2=驳回
	private Date applyTime;		//申请时间（系统生成）
	
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
	public int getBuyer() {
		return buyer;
	}
	public void setBuyer(int buyer) {
		this.buyer = buyer;
	}
	public String getInviter() {
		return inviter;
	}
	public void setInviter(String inviter) {
		this.inviter = inviter;
	}
	public String getInfoSource() {
		return infoSource;
	}
	public void setInfoSource(String infoSource) {
		this.infoSource = infoSource;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	@Override
	public String toString() {
		return "Audience [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", position=" + position + ", phone=" + phone + ", email="
				+ email + ", buyer=" + buyer + ", inviter=" + inviter
				+ ", infoSource=" + infoSource + ", org=" + org + ", username="
				+ username + ", password=" + password + ", address=" + address
				+ ", target=" + target + ", state=" + state + ", applyTime="
				+ applyTime + ", getId()=" + getId() + ", getName()="
				+ getName() + ", getSex()=" + getSex() + ", getPosition()="
				+ getPosition() + ", getPhone()=" + getPhone()
				+ ", getEmail()=" + getEmail() + ", getBuyer()=" + getBuyer()
				+ ", getInviter()=" + getInviter() + ", getInfoSource()="
				+ getInfoSource() + ", getOrg()=" + getOrg()
				+ ", getUsername()=" + getUsername() + ", getPassword()="
				+ getPassword() + ", getAddress()=" + getAddress()
				+ ", getTarget()=" + getTarget() + ", getState()=" + getState()
				+ ", getApplyTime()=" + getApplyTime() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
