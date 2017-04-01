package com.ffzx.ffsip.service;

import java.util.List;

import com.ffzx.common.service.BaseService;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.Menu;
import com.ffzx.ffsip.vo.UserInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface CompanyService extends BaseService<Company,String>{

    public Company findByMemberCode(String memberCode);


    /**
     * 模糊查询公司
     * @param K
     * @param loginMember
     * @return
     */
    public List<UserInfo> likeUserInfo(String K,Member loginMember);

	/**
	 * 通过公司信息获取用户对象信息
	 * @param companyList
	 * @param loginMember
	 * @return
	 */
	public List<UserInfo> getUserInfo(List<Company> companyList,Member loginMember);
}
