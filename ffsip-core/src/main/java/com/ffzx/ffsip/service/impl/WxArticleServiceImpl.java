package com.ffzx.ffsip.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.ffzx.common.service.impl.BaseServiceImpl;
import com.ffzx.ffsip.mapper.WxArticleMapper;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxArticleExample;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxArticleService;
import com.ffzx.ffsip.vo.ArticleInfo;

/**
 * Created by Administrator on 2017/1/17.
 */
@Service
public class WxArticleServiceImpl extends BaseServiceImpl<WxArticle,String> implements WxArticleService {

    @Resource
    private WxArticleMapper mapper;

    @Resource
    private MemberService memberService;
    
    @Resource
    private CompanyService companyService;

    @Override
    public WxArticleMapper getMapper() {
        return mapper;
    }
    
    /**
     * 获取文章列表
     * @param example
     * @return
     */
    public List<ArticleInfo> findArticleInfo(WxArticleExample example){

        List<WxArticle> articleList = this.selectByExample(example);

    	List<ArticleInfo> list = new ArrayList<ArticleInfo>();
        if(articleList != null && articleList.size() > 0){
        	for(WxArticle article : articleList){
        		ArticleInfo info = new ArticleInfo();
        		info.setWxArticle(article);
        		
        		Member member = memberService.findByCode(article.getPublisher()); //发布者信息
        		Company company = null;									
            	if(member != null){   
            		info.setMember(member);						
        	    	CompanyExample companyExample = new CompanyExample();
        	    	companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
        	    	List<Company> companyList = companyService.selectByExample(companyExample);
        	    	if(companyList != null && companyList.size() > 0){
        	    		company = companyList.get(0);
        	    		info.setCompany(company);									//获取公司信息
        	    	}
            	}
            	list.add(info);
        	}        	
        	return list;
        }else{
        	return null;
        }       	                
    }  

    
    /**
     * 通过粉丝code查询
     * @param params
     * @return
     */
	public List<WxArticle> selectByPageFansCode(Map<String, Object> params){
		return mapper.selectByPage(params);
	}
        
    /**
     * 通过文章列表获取有用户信息的文章列表
     * @param list
     * @return
     */
    public List<ArticleInfo> getArticleInfoByList(List<WxArticle> articleList){
    	List<ArticleInfo> list = new ArrayList<ArticleInfo>();
        if(articleList != null && articleList.size() > 0){
        	for(WxArticle article : articleList){
        		ArticleInfo info = new ArticleInfo();
        		info.setWxArticle(article);
        		
        		Member member = memberService.findByCode(article.getPublisher()); //发布者信息
        		Company company = null;									
            	if(member != null){   
            		info.setMember(member);						
        	    	CompanyExample companyExample = new CompanyExample();
        	    	companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
        	    	List<Company> companyList = companyService.selectByExample(companyExample);
        	    	if(companyList != null && companyList.size() > 0){
        	    		company = companyList.get(0);
        	    		info.setCompany(company);									//获取公司信息
        	    	}
            	}
            	list.add(info);
        	}        	
        	return list;
        }else{
        	return null;
        }       	                
    }

	@Override
	public void convertPublisher(WxArticle article) {
		Company c = companyService.findByMemberCode(article.getPublisher());
		if (c != null) {
			article.setPublisher(c.getName());
		} else {
			Member t = memberService.findByCode(article.getPublisher());
			if (t != null) {

				article.setPublisher(t.getWxNickName());
			}
		}
	}
}
