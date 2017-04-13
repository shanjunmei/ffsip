package com.ffzx.ffsip.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.FansExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.service.FansService;
import com.ffzx.ffsip.service.MemberService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 关注controller
 * @author liujunjun
 * @time：2017年3月29日 上午9:31:05
 * @version 1.0.0
 */
@Controller
@RequestMapping("fans")
public class FansController extends BaseController<Fans, String, FansExample> {

	@Resource
	private FansService service;
	
    @Resource
    private MemberService memberService;

	@Override
	public FansService getService() {
		return service;
	}
	
	/**
	 * 关注
	 * @param subscribeCode
	 * @param fansCode
	 * @return
	 */
	@RequestMapping("addFans")
	@ResponseBody
	public ResultVo addFans(String subscribeCode, String fansCode){		
        ResultVo resultVo = new ResultVo();
		if(StringUtils.isEmpty(subscribeCode) || StringUtils.isEmpty(fansCode)){
			resultVo.setStatus("fail");
			return resultVo;
		}
		
		if(subscribeCode.equals(fansCode)){		//不能关注自己
			resultVo.setStatus("isSelf");
			return resultVo;			
		}
		
		FansExample example = new FansExample();
		example.createCriteria().andSubscribeCodeEqualTo(subscribeCode).andFansCodeEqualTo(fansCode);
		List<Fans> list = service.selectByExample(example);			
		if(list != null && list.size() > 0){
			resultVo.setStatus("fail");
			return resultVo;
		}
		
		Fans fans = new Fans();
		fans.setSubscribeCode(subscribeCode);
		fans.setFansCode(fansCode);
		this.updateFansNum( subscribeCode,  fansCode, 1);
		return this.save(fans);
	}
	
	/**
	 * 取消关注
	 * @param subscribeCode
	 * @param fansCode
	 * @return
	 */
	@RequestMapping("removeFans")
	@ResponseBody
	public ResultVo removeFans(String subscribeCode, String fansCode){
        ResultVo resultVo = new ResultVo();
		if(StringUtils.isEmpty(subscribeCode) || StringUtils.isEmpty(fansCode)){
			resultVo.setStatus("fail");
			return resultVo;
		}

		FansExample example = new FansExample();
		example.createCriteria().andSubscribeCodeEqualTo(subscribeCode).andFansCodeEqualTo(fansCode);
		List<Fans> list = service.selectByExample(example);		
		Fans fans = new Fans();
		
		if(list != null && list.size() > 0){
			fans = list.get(0);
		}else{
			resultVo.setStatus("fail");
			return resultVo;
		}
		this.updateFansNum( subscribeCode,  fansCode, -1);
		return this.delete(fans.getId());
	}
	
	/**
	 * 修改被关注人的粉丝数
	 * 和粉丝的关注数
	 * @param subscribeCode
	 * @param fansCode
	 * @param num
	 */
	public void updateFansNum(String subscribeCode, String fansCode,int num){
		Member subscribeMember =  memberService.findByCode(subscribeCode);			//被关注人  +粉丝数
		Member fansMember =  memberService.findByCode(fansCode);					//粉丝	 +关注数
		
		if (subscribeMember.getFansNum() == null || "".equals(subscribeMember.getFansNum())) {
			subscribeMember.setFansNum(1);
        } else {
    		subscribeMember.setFansNum(subscribeMember.getFansNum()+num);
        }
		if (fansMember.getSubscribeNum() == null || "".equals(fansMember.getSubscribeNum())) {
			fansMember.setSubscribeNum(1);
        } else {
        	fansMember.setSubscribeNum(fansMember.getSubscribeNum()+num);
        }		
		memberService.updateSelective(subscribeMember);
		memberService.updateSelective(fansMember);

        WebUtils.setSessionAttribute("loginMember", fansMember);
	}

    /**
     * 异步加载粉丝信息
     * @param memberCode
     * @param modelMap
     * @param 获取类型：1获取粉丝信息；0获取被关注者信息		
     * @return
     */
    @RequestMapping("fansLoad")
    @ResponseBody
    public Map<String, Object> fansLoad(String memberCode, ModelMap modelMap,int type) {
        Map<String, Object> map = new HashMap<String, Object>();
        Member member = memberService.findByCode(memberCode); 
        Member loginMember = WebUtils.getSessionAttribute("loginMember");
        if (member == null) {
            return null;
        }
    	              
        /*分页信息begin*/
        String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
        String sizeStr = "10";
        Page<WxArticle> page = null;
        int pageTotal = 0;
        if (StringUtils.isNotBlank(indexStr)) {
            page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
        }/*分页信息end*/


        //获取关注用户
        FansExample fansExample = new FansExample();

		if(type == 1){
			fansExample.createCriteria().andSubscribeCodeEqualTo(member.getCode());	
		}else if(type == 0){
	        fansExample.createCriteria().andFansCodeEqualTo(member.getCode());
		}
        fansExample.setOrderByClause("create_date desc");
    	List<Fans> fansList = getService().selectByExample(fansExample);		
    	if(fansList != null && fansList.size() > 0){
    		map.put("fansList", memberService.getUserInfoByFans(fansList,loginMember,type)); 
    	} 

        pageTotal = (int) page.getPages();
        map.put("pageTotal", pageTotal);
        
        return map;
    }
}
