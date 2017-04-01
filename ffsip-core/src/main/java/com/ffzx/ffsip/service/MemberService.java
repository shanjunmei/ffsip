package com.ffzx.ffsip.service;

import java.util.List;

import com.ffzx.common.service.BaseService;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.vo.UserInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface MemberService extends BaseService<Member,String>{


    public Member findByOpenId(String opoenid);
    
    /**
	 * 通过粉丝列表获取用户信息
	 * @param fansList			粉丝列表
	 * @param loginMember		当前登录用户
	 * @param type				获取类型：1获取粉丝信息；0获取被关注者信息
	 * @return
	 */
	public List<UserInfo> getUserInfoByFans(List<Fans> fansList,Member loginMember,int type);
    /**
     * 模糊查询用户
     * @param K
     * @param loginMember
     * @return
     */
    public List<UserInfo> likeUserInfo(String K,Member loginMember);
	/**
	 * 通过用户信息获取用户对象信息
	 * @param companyList
	 * @param loginMember
	 * @return
	 */
	public List<UserInfo> getUserInfo(List<Member> memberList,Member loginMember);

}
