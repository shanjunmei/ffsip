package com.ffzx.ffsip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.MemberMapper;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.FansExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.MemberExample;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.FansService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.vo.UserInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class MemberServiceImpl extends BaseServiceImpl<Member, String> implements MemberService {

	@Resource
	private MemberMapper mapper;

	@Resource
	private CompanyService companyService;
	
	@Resource
	private FansService fansService;

	@Override
	public MemberMapper getMapper() {
		return mapper;
	}

	@Override
	public Member findByOpenId(String opoenid) {
		MemberExample example = new MemberExample();
		example.createCriteria().andWxOpenidEqualTo(opoenid);
		List<Member> dataList = mapper.selectByExample(example);
		if (dataList != null && dataList.size() > 0) {
			return dataList.get(0);
		}
		return null;
	}

	/**
	 * 通过粉丝列表获取用户信息
	 * @param fansList			粉丝列表
	 * @param loginMember		当前登录用户
	 * @param type				获取类型：1获取粉丝信息；0获取被关注者信息
	 * @return
	 */
	public List<UserInfo> getUserInfoByFans(List<Fans> fansList,Member loginMember,int type) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		for (Fans fans : fansList) {
			UserInfo user = new UserInfo();
			Member member = new Member();
			if(type == 1){
				member = this.findByCode(fans.getFansCode()); // 获取粉丝				
			}else if(type == 0){
				member = this.findByCode(fans.getSubscribeCode()); // 获取被关注者	
			}
			Company company = null; // 获取公司信息
			if (member != null) {
				CompanyExample companyExample = new CompanyExample();
				companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
				List<Company> companyList = companyService.selectByExample(companyExample);
				if (companyList != null && companyList.size() > 0) {
					company = companyList.get(0);

					user.setCompany(company);
				}
				user.setMember(member);
				
				//是否关注
				user.setIsFans("0");
	            if(loginMember != null){
	    	        FansExample example = new FansExample();
	    			example.createCriteria().andSubscribeCodeEqualTo(member.getCode()).andFansCodeEqualTo(loginMember.getCode());
	    			List<Fans> fanss = fansService.selectByExample(example);		
	    			if(fanss != null && fanss.size() > 0){
	    				user.setIsFans("1");
	    			}
	    		}
	            list.add(user);
			}			
		}

		return list;
	}
	

    /**
     * 模糊查询用户
     */
    public List<UserInfo> likeUserInfo(String K,Member loginMember){
    	if(StringUtils.isEmpty(K)){
    		return null;
    	}

    	MemberExample example = new MemberExample();
    	example.createCriteria().andWxNickNameLike("%"+K+"%");
    	List<Member> memberList = selectByExample(example);
    	return getUserInfo(memberList,loginMember);
    }
    

	/**
	 * 通过memberList获取用户对象信息
	 */
	public List<UserInfo> getUserInfo(List<Member> memberList,Member loginMember) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		for (Member member : memberList) {
			UserInfo user = new UserInfo();
			user.setMember(member);
			
			Company company = null; // 获取公司信息
			CompanyExample companyExample = new CompanyExample();
			companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
			List<Company> companyList = companyService.selectByExample(companyExample);
			if (companyList != null && companyList.size() > 0) {
				company = companyList.get(0);

				user.setCompany(company);
			}
						
			//是否关注
			user.setIsFans("0");
            if(loginMember != null){
    	        FansExample example = new FansExample();
    			example.createCriteria().andSubscribeCodeEqualTo(member.getCode()).andFansCodeEqualTo(loginMember.getCode());
    			List<Fans> fanss = fansService.selectByExample(example);		
    			if(fanss != null && fanss.size() > 0){
    				user.setIsFans("1");
    			}
    		}
            list.add(user);		
		}

		return list;
	}
}
