package com.ffzx.ffsip.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxArticleExample;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxArticleService;
import com.ffzx.ffsip.vo.ArticleInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 文章
 * Created by Administrator on 2017/3/7.
 */
@Controller
@RequestMapping("WxArticle")
public class WxArticleController extends BaseController<WxArticle,String,WxArticleExample> {

    @Resource
    private WxArticleService service;    

    @Resource
    private MemberService memberService;
    
    @Resource
    private CompanyService companyService;

    @Override
    public WxArticleService getService() {
        return service;
    }

    /**
     * 进入个人主页
     * @param memberCode
     * @param modelMap
     * @return
     */
    @RequestMapping("list")
    public String toList(String memberCode, ModelMap modelMap){
    	modelMap = selectPublisher(memberCode, modelMap); //获取发表者信息
    	Member member = (Member)modelMap.get("member");
    	if(member == null){ 
    		return getBasePath() + "/list";
    	}
    	              
        /*分页信息begin*/
		String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
		String sizeStr = "10";
		Page<WxArticle> page = null;
		int pageTotal = 0;
		if (StringUtils.isNotBlank(indexStr)) {
			page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
		}/*分页信息end*/

        WxArticleExample example = new WxArticleExample();
    	example.createCriteria().andPublisherEqualTo(member.getCode());	
    	example.setOrderByClause("last_update_date desc");
        List<WxArticle> list = getService().selectByExample(example);

        pageTotal = (int) page.getPages();
		modelMap.put("pageTotal", pageTotal);
        modelMap.put("memberCode", memberCode);			//当前查询用户信息
        modelMap.put("list", list);				//文章列表
        return getBasePath() + "/list";
    }
    

    /**
     * 异步进入个人主页
     * @param memberCode
     * @param modelMap
     * @return
     */
    @RequestMapping("listLoad")
	@ResponseBody
    public Map<String, Object> toListLoad(String memberCode, ModelMap modelMap){
    	Map<String, Object> map = new HashMap<String, Object>();
    	modelMap = selectPublisher(memberCode, modelMap); //获取发表者信息
    	Member member = (Member)modelMap.get("member");
    	map.put("member", member);
    	map.put("company", modelMap.get("company"));
    	if(member == null){ 
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

        WxArticleExample example = new WxArticleExample();
    	example.createCriteria().andPublisherEqualTo(member.getCode());	
    	example.setOrderByClause("last_update_date desc");
        List<WxArticle> list = getService().selectByExample(example);

        pageTotal = (int) page.getPages();
        map.put("pageTotal", pageTotal);
        map.put("memberCode", memberCode);			//当前查询用户信息
        map.put("list", list);				//文章列表
        return map;
    }

    /**
     * 获取文章详情
     * @param articleCode
     * @param modelMap
     * @return
     */
    @RequestMapping("detail")
    public String toDetail(String articleCode, ModelMap modelMap){
    		
        WxArticle article = getService().findByCode(articleCode);
        if(article != null){
        	modelMap = selectPublisher(article.getPublisher() , modelMap); //获取发表者信息 
        	modelMap.put("article", article);        	       	
        }
        
        return getBasePath() + "/detail";
    }

    /**
     * 获取首页
     * @param modelMap
     * @return
     */
    @RequestMapping("index")
    public String toIndex(ModelMap modelMap){
    	 /*分页信息begin*/
		String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
		String sizeStr = "10";
		Page<ArticleInfo> page = null;
		int pageTotal = 0;
		if (StringUtils.isNotBlank(indexStr)) {
			page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
		}/*分页信息end*/

        WxArticleExample example = new WxArticleExample();
    	example.setOrderByClause("last_update_date desc");
        List<ArticleInfo> list = getService().findArticleInfo(example);

        pageTotal = (int) page.getPages();
		modelMap.put("pageTotal", pageTotal);
        modelMap.put("list", list);				//文章列表
        
        return "/index";
    }

    /**
     * 异步获取首页
     * @param modelMap
     * @return
     */
    @RequestMapping("indexLoad")
	@ResponseBody
    public Map<String, Object> toIndexLoad(ModelMap modelMap){
    	Map<String, Object> map = new HashMap<String, Object>();
    	 /*分页信息begin*/
		String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
		String sizeStr = "10";
		Page<ArticleInfo> page = null;
		int pageTotal = 0;
		if (StringUtils.isNotBlank(indexStr)) {
			page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
		}/*分页信息end*/

        WxArticleExample example = new WxArticleExample();
    	example.setOrderByClause("last_update_date desc");
        List<ArticleInfo> list = getService().findArticleInfo(example);

        pageTotal = (int) page.getPages();
        map.put("pageTotal", pageTotal);
        map.put("list", list);				//文章列表
        
        return map;
    }
    
    /**
     * 获取发布者信息
     * @param memberCode
     * @param modelMap
     * @return
     */
    public ModelMap selectPublisher(String memberCode, ModelMap modelMap){
    	Member member = memberService.findByCode(memberCode);   //发布者信息
    	Company company = null;									//获取公司信息
    	if(member != null){    		    	
	    	CompanyExample companyExample = new CompanyExample();
	    	companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
	    	List<Company> list = companyService.selectByExample(companyExample);
	    	if(list != null && list.size() > 0){
	    		company = list.get(0);
		        modelMap.put("company", company);
	    	}
	        modelMap.put("member", member);	
    	}
    	return modelMap;
    }
}
