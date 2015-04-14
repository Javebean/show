package com.novahome.data.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Exhibitors {

	private String id;
	private String orgName;
	private String orgEng;
	private String president;
	private String address;
	private String site;
	private String zipcode;
	private String region;
	private String orgType;
	private String industryType;
	private String scale;
	private String contact;
	private String contactPosition;
	private String phone;
	private String email;
	private String telephone;
	private String fax;
	private String username;
	private String password;
	private int btsl;
	private int tytzzs;
	private int swzs;
	private String jgzxsb;
	private String tzfw;
	private String tssm;
	private String orgIntro;
	private String inviter;
	private String booth;
	private int state;
	private String logo;
	private Date applyTime;
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
	public String getInviter() {
		return inviter;
	}
	public void setInviter(String inviter) {
		this.inviter = inviter;
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
	
}
