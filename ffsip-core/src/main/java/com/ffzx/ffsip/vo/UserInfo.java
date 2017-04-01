package com.ffzx.ffsip.vo;

import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.Member;

/**
 * 用户信息
 * @author liujunjun
 * @time：2017年3月30日 下午2:02:57
 * @version 1.0.0
 */
public class UserInfo {
	private Member member;					//发表者信息
	private Company company;				//发布公司信息
	
	private String isFans;					//是否关注

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getIsFans() {
		return isFans;
	}

	public void setIsFans(String isFans) {
		this.isFans = isFans;
	}
	
	

}
