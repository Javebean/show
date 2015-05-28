package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 展商实体类
 * @author xiaohaizhe
 *
 */
@Entity
public class Exhibitors {

	private String id;	//id,系统生成
	private String orgName;	//公司名称
	private String orgEng;	//公司英文名称
	private String president;	//公司法人
	private String address;	//公司地址
	private String site;	//公司网址
	private String zipcode;	//邮政编码
	private String region;	//国家、地区
	private String orgType;	//企业性质， 下拉框
	private String industryType;	//行业类别
	private String scale;	//行业规模
	private String contact;	//联系人
	private String contactPosition;	//联系人职务
	private String phone;	//手机号码
	private String email;	//电子邮箱
	private String telephone;	//固定电话
	private String fax;	//传真
	private String username;	//展商登陆名（系统生成）
	private String password;	//展商登陆密码（系统生成）
	private int btsl;	//申请标摊数量
	private int tytzzs;	//是否申请统一特装展示  是=1， 否=0
	private int swzs;	//是否室外展示， 是 =1， 否=0
	private String jgzxsb;	//进馆所需装卸设备
	private String tzfw;	//是否需要特装服务，如果需要，填写内容
	private String tssm;	//其他要求说明
	private String orgIntro;	//单位及产品简介（限定500字以内，超过不准写）
	
	private String booth;	//展位号
	private int state;		 //默认正常状态，state=0 申请；1 =批准； 2=驳回
	private String logo;	//企业logo
	private Date applyTime;	//申请时间（系统生成）
	private String recommender;
	
	private String reason; //驳回原因
	@Id
	@Column(length = 64)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgEng() {
		return orgEng;
	}
	public void setOrgEng(String orgEng) {
		this.orgEng = orgEng;
	}
	public String getPresident() {
		return president;
	}
	public void setPresident(String president) {
		this.president = president;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactPosition() {
		return contactPosition;
	}
	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public int getBtsl() {
		return btsl;
	}
	public void setBtsl(int btsl) {
		this.btsl = btsl;
	}
	public int getTytzzs() {
		return tytzzs;
	}
	public void setTytzzs(int tytzzs) {
		this.tytzzs = tytzzs;
	}
	public int getSwzs() {
		return swzs;
	}
	public void setSwzs(int swzs) {
		this.swzs = swzs;
	}
	public String getJgzxsb() {
		return jgzxsb;
	}
	public void setJgzxsb(String jgzxsb) {
		this.jgzxsb = jgzxsb;
	}
	public String getTzfw() {
		return tzfw;
	}
	public void setTzfw(String tzfw) {
		this.tzfw = tzfw;
	}
	public String getTssm() {
		return tssm;
	}
	public void setTssm(String tssm) {
		this.tssm = tssm;
	}
	public String getOrgIntro() {
		return orgIntro;
	}
	public void setOrgIntro(String orgIntro) {
		this.orgIntro = orgIntro;
	}
	
	public String getBooth() {
		return booth;
	}
	public void setBooth(String booth) {
		this.booth = booth;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
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
	public String getRecommender() {
		return recommender;
	}
	public void setRecommender(String recommender) {
		this.recommender = recommender;
	}
	
}
