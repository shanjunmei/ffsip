package com.ffzx.ffsip.service;

import com.ffzx.common.service.BaseService;
import com.ffzx.ffsip.model.Member;

/**
 * Created by Administrator on 2017/1/17.
 */
public interface MemberService extends BaseService<Member,String>{


    public Member findByOpenId(String opoenid);

}
