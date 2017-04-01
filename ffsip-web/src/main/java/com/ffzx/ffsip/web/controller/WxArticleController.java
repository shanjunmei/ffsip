package com.ffzx.ffsip.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ffzx.ffsip.search.IndexService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Comment;
import com.ffzx.ffsip.model.CommentExample;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.model.CompanyExample;
import com.ffzx.ffsip.model.Fans;
import com.ffzx.ffsip.model.FansExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.model.WxArticleExample;
import com.ffzx.ffsip.model.WxArticleExample.Criteria;
import com.ffzx.ffsip.model.WxEditArticle;
import com.ffzx.ffsip.service.CommentService;
import com.ffzx.ffsip.service.CompanyService;
import com.ffzx.ffsip.service.FansService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxArticleService;
import com.ffzx.ffsip.service.WxEditArticleService;
import com.ffzx.ffsip.vo.ArticleInfo;
import com.ffzx.ffsip.wechat.WechatApiService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * 文章
 * Created by Administrator on 2017/3/7.
 */
@Controller
@RequestMapping("WxArticle")
public class WxArticleController extends BaseController<WxArticle, String, WxArticleExample> {

    @Resource
    private WxArticleService service;

    @Resource
    private MemberService memberService;

    @Resource
    private CompanyService companyService;

    @Resource
    private WxEditArticleService wxEditArticleService;

    @Resource
    private WxArticleService wxArticleService;

    @Resource
    private WechatApiService wechatApiService;

	@Resource
	private FansService fansService;
	
	@Resource
	private CommentService commentService;

    @Resource
    private IndexService indexService;
	

    @Override
    public WxArticleService getService() {
        return service;
    }

    /**
     * 进入个人主页
     *
     * @param memberCode
     * @param modelMap
     * @return
     */
    @RequestMapping("list")
    public String toList(String memberCode, ModelMap modelMap) {
        Member member = WebUtils.getSessionAttribute("loginMember");
        if (StringUtils.isEmpty(memberCode) && member != null) {                //如果没传用户信息，则进入用户列表
            Company company = WebUtils.getSessionAttribute("loginCompany");

            modelMap.put("member", member);
            modelMap.put("company", company);
        }

        if (StringUtils.isNotEmpty(memberCode)) {                                //获取发表者信息
            modelMap = selectPublisher(memberCode, modelMap);
            member = (Member) modelMap.get("member");
        }

        if (member == null) {
            return "redirect:" + getBasePath() + "/index.do";
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
        example.setOrderByClause("create_date desc");
        List<WxArticle> list = getService().selectByExample(example);
         
        pageTotal = (int) page.getPages();
        modelMap.put("pageTotal", pageTotal);
        modelMap.put("memberCode", member.getCode());            //当前查询用户信息
        modelMap.put("list", list);                //文章列表
        return getBasePath() + "/list";
    }


    /**
     * 异步进入个人主页
     *
     * @param memberCode
     * @param modelMap
     * @return
     */
    @RequestMapping("listLoad")
    @ResponseBody
    public Map<String, Object> toListLoad(String memberCode, ModelMap modelMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        modelMap = selectPublisher(memberCode, modelMap); //获取发表者信息
        Member member = (Member) modelMap.get("member");
        map.put("member", member);
        map.put("company", modelMap.get("company"));
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

        WxArticleExample example = new WxArticleExample();
        example.createCriteria().andPublisherEqualTo(member.getCode());
        example.setOrderByClause("create_date desc");
        List<WxArticle> list = getService().selectByExample(example);

        pageTotal = (int) page.getPages();
        map.put("pageTotal", pageTotal);
        map.put("memberCode", memberCode);            //当前查询用户信息
        map.put("list", list);                //文章列表
        return map;
    }

    /**
     * 获取文章详情
     *
     * @param articleCode
     * @param modelMap
     * @return
     */
    @RequestMapping("detail")
    public String toDetail(String articleCode, ModelMap modelMap) {
        WxArticle article = getService().findByCode(articleCode);
        if (article != null) {
            modelMap = selectPublisher(article.getPublisher(), modelMap); //获取发表者信息
            modelMap.put("article", article);
            if (article.getReadingNum() == null || "".equals(article.getReadingNum())) {
                article.setReadingNum(1);
            } else {
                article.setReadingNum(article.getReadingNum() + 1);
            }
            getService().update(article);
        }
        
        //获取评论信息        
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_date desc");
        commentExample.createCriteria().andArticleCodeEqualTo(articleCode);
        List<Comment> commentList = commentService.selectByExample(commentExample);
        modelMap.put("commentList", commentList);

        return getBasePath() + "/detail";
    }

    /**
     * 获取首页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("index")
    public String toIndex(ModelMap modelMap) {
    	 /*分页信息begin*/
        String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
        String sizeStr = "10";
        Page<ArticleInfo> page = null;
        int pageTotal = 0;
        if (StringUtils.isNotBlank(indexStr)) {
            page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
        }/*分页信息end*/

        WxArticleExample example = new WxArticleExample();
        example.createCriteria().andIndexSortIsNotNull();
        example.setOrderByClause("index_sort desc, last_update_date desc");
        List<ArticleInfo> list = getService().findArticleInfo(example);

        pageTotal = (int) page.getPages();
        modelMap.put("pageTotal", pageTotal);
        modelMap.put("list", list);                //文章列表
        modelMap.put("homeFocus", 1);
        return "/index";
    }

    /**
     * 异步获取首页
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("indexLoad")
    @ResponseBody
    public Map<String, Object> toIndexLoad(ModelMap modelMap) {
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
        example.createCriteria().andIndexSortIsNotNull();
        example.setOrderByClause("index_sort desc, last_update_date desc");
        List<ArticleInfo> list = getService().findArticleInfo(example);

        pageTotal = (int) page.getPages();
        map.put("pageTotal", pageTotal);
        map.put("list", list);                //文章列表

        return map;
    }

    /**
     * 获取发布者信息
     *
     * @param memberCode
     * @param modelMap
     * @return
     */
    public ModelMap selectPublisher(String memberCode, ModelMap modelMap) {
    	
        Member member = memberService.findByCode(memberCode);   //发布者信息
        Company company = null;                                    //获取公司信息
        if (member != null) {
            CompanyExample companyExample = new CompanyExample();
            companyExample.createCriteria().andMemberCodeEqualTo(member.getCode());
            List<Company> list = companyService.selectByExample(companyExample);
            if (list != null && list.size() > 0) {
                company = list.get(0);
                modelMap.put("company", company);
            }
            modelMap.put("member", member);            

            //查询当前用户是否已关注
            Member loginMember = WebUtils.getSessionAttribute("loginMember");
    		modelMap.put("fans", "0");
            if(loginMember != null){
    	        FansExample example = new FansExample();
    			example.createCriteria().andSubscribeCodeEqualTo(member.getCode()).andFansCodeEqualTo(loginMember.getCode());
    			List<Fans> fansList = fansService.selectByExample(example);		
    			if(fansList != null && fansList.size() > 0){
    				modelMap.put("fans", "1");
    			}
    		}
        }
        return modelMap;
    }


    @RequestMapping("addArticle")
    @ResponseBody
    public Object addArticle(WxArticle article, HttpServletRequest request) {
        Document doc = Jsoup.parse(article.getContent());

        String sourceCode = request.getParameter("sourceCode");
        String previewPic = request.getParameter("previewPic");
        WxEditArticle wxEditArticle = wxEditArticleService.findByCode(sourceCode);
        Document sourceDoc = Jsoup.parse(wxEditArticle.getSourceContent());
        doc.head().html(sourceDoc.head().html());
        doc.title(article.getTitle());
        //logger.info("doc:{}",doc.toString());
        article.setContent(doc.toString());
        article.setPublisher(wxEditArticle.getCreateBy());
        article.setCreateBy(wxEditArticle.getCreateBy());
        article.setCoverImg(previewPic);
       /* if(doc.select("img")!=null){
            article.setCoverImg(doc.select("img").get(0).attr("src"));
        }*/

        int i = wxArticleService.add(article);


        Map<String, String> ret = new HashMap<>();
        if (i > 0) {
            indexService.buildIndex(article);
            String url = "http://ffsip.ffzxnet.com/ffsip-web/WxArticle/detail.do?articleCode=" + article.getCode();
            ret.put("msg", "success");
            ret.put("returnStr", url);
            Member member = memberService.findByCode(article.getCreateBy());
            Integer articleNum=member.getArticleNum();
            if(articleNum==null){
                articleNum=0;
            }
            articleNum=articleNum+1;
            member.setArticleNum(articleNum);
            memberService.updateSelective(member);
            wechatApiService.sendMsg(member.getWxOpenid(), "<a href=\"" + url + "\">" + article.getTitle() + "</a>");
        } else {
            ret.put("msg", "发布失败");
        }
        return ret;
    }


    @RequestMapping(value = "editArticle", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object editArticle(String code) {
        WxEditArticle editArticle = wxEditArticleService.findByCode(code);
        return editArticle.getContent();
    }

    @RequestMapping(value = "article", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object article(String code) {
        WxArticle editArticle = wxArticleService.findByCode(code);
        return editArticle.getContent();
    }
    


    /**
     * 获取关注的文章
     * @param modelMap
     * @return
     */
    @RequestMapping("homeFocus")
    public String homeFocus(ModelMap modelMap) {
        Member member = WebUtils.getSessionAttribute("loginMember");
        if(member == null){
            return "redirect:" + getBasePath() + "/index.do";
        }
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
        modelMap.put("list", list);                //文章列表
        modelMap.put("homeFocus", 2);
        modelMap.put("memberCode", member.getCode());            //当前查询用户信息
        return "/home-focus";
    }

    /**
     * 获取关注的文章
     * @param modelMap
     * @return
     */
    @RequestMapping("homeFocusLoad")
    @ResponseBody
    public Map<String, Object> homeFocusLoad(ModelMap modelMap) {
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
        map.put("list", list);                //文章列表        
        return map;
    }
    
    /**
     * 搜索
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("search")
    public String search(String valueK,ModelMap modelMap) throws UnsupportedEncodingException{
//    	valueK = new String(valueK.getBytes("iso8859-1"),"UTF-8");
    	if(StringUtils.isEmpty(valueK)){
    		valueK = WebUtils.getSessionAttribute("valueK");
    		if(StringUtils.isEmpty(valueK)){
    			return "/search";    		
    		}
    	}else{
            WebUtils.setSessionAttribute("valueK", valueK);
    	}
    	
    	 /*分页信息begin*/
        String indexStr = (String) ((getParameter("pageIndex") == null) ? "1" : getParameter("pageIndex"));
        String sizeStr = "10";
        Page<ArticleInfo> page = null;
        int pageTotal = 0;
        if (StringUtils.isNotBlank(indexStr)) {
            page = PageHelper.startPage(Integer.valueOf(indexStr), Integer.valueOf(sizeStr));
        }/*分页信息end*/

        WxArticleExample example = new WxArticleExample();
        example.createCriteria().andTitleLike("%"+valueK+"%");
        example.setOrderByClause("last_update_date desc");
        List<ArticleInfo> list = getService().findArticleInfo(example);

        Member member = WebUtils.getSessionAttribute("loginMember");

        modelMap.put("companyList", companyService.likeUserInfo(valueK, member));
        modelMap.put("memberList", memberService.likeUserInfo(valueK, member));
        
        pageTotal = (int) page.getPages();
        modelMap.put("pageTotal", pageTotal);
        modelMap.put("list", list);                //文章列表
        modelMap.put("valueK", valueK);

        return "/search";
    }
}
