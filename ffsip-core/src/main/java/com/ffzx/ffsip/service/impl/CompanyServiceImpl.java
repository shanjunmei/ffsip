package com.ffzx.ffsip.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.CompanyMapper;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.FansExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.FansService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.vo.UserInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, String> implements CompanyService {

    @Resource
    private CompanyMapper mapper;

	@Resource
	private MemberService memberService;
	
	@Resource
	private FansService fansService;

    @Override
    public CompanyMapper getMapper() {
        return mapper;
    }

    @Override
    public Company findByMemberCode(String memberCode) {
        Company company = null;									//获取公司信息

            CompanyExample companyExample = new CompanyExample();
            companyExample.createCriteria().andMemberCodeEqualTo(memberCode);
            List<Company> list = selectByExample(companyExample);
            if(list != null && list.size() > 0){
                company = list.get(0);

            }

        return company;
    }
    
    /**
     * 模糊查询公司
     * @param K
     * @param loginMember
     * @return
     */
    public List<UserInfo> likeUserInfo(String K,Member loginMember){
    	if(StringUtils.isEmpty(K)){
    		return null;
    	}

    	CompanyExample example = new CompanyExample();
    	example.createCriteria().andNameLike("%"+K+"%");
    	List<Company> companyList = selectByExample(example);
    	return getUserInfo(companyList,loginMember);
    }
    

	/**
	 * 通过公司信息获取用户对象信息
	 * @param companyList
	 * @param loginMember
	 * @return
	 */
	public List<UserInfo> getUserInfo(List<Company> companyList,Member loginMember) {
		List<UserInfo> list = new ArrayList<UserInfo>();

		for (Company company : companyList) {
			UserInfo user = new UserInfo();
			Member member = memberService.findByCode(company.getMemberCode());	
			user.setCompany(company);
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

		return list;
	}
}
