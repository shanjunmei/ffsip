package com.ffzx.ffsip.vo;

import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;

/**
 * 文章详情
 * @author liujunjun
 * @time：2017年3月9日 下午2:02:44
 * @version 1.0.0
 */
public class ArticleInfo {
	private WxArticle wxArticle;			//文章信息
	private Member member;					//发表者信息
	private Company company;				//发布公司信息
	
	public WxArticle getWxArticle() {
		return wxArticle;
	}
	public void setWxArticle(WxArticle wxArticle) {
		this.wxArticle = wxArticle;
	}
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
	
	
}
