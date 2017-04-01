package com.ffzx.ffsip.web.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Comment;
import com.ffzx.ffsip.model.CommentExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.service.CommentService;
import com.ffzx.ffsip.service.MemberService;
import com.ffzx.ffsip.service.WxArticleService;
import com.ffzx.ffsip.util.JsonConverter;

/**
 * 
 * @author liujunjun
 * @time：2017年3月29日 下午5:42:43
 * @version 1.0.0
 */
@Controller
@RequestMapping("comment")
public class CommentController extends BaseController<Comment, String, CommentExample> {

	@Resource
	private CommentService service;
	
    @Resource
    private MemberService memberService;
    @Resource
    private WxArticleService wxArticleService;

	@Override
	public CommentService getService() {
		return service;
	}
	
	/**
	 * 评论
	 * @param articleCode
	 * @param commentContent
	 * @return
	 */
	@RequestMapping("addComment")
	@ResponseBody
	public ResultVo addComment(String articleCode, String commentContent){	
        Member loginMember = WebUtils.getSessionAttribute("loginMember"); 
        ResultVo resultVo = new ResultVo();
		if(StringUtils.isEmpty(articleCode) || StringUtils.isEmpty(commentContent)){
			resultVo.setStatus("fail");
			return resultVo;
		}

		if(loginMember == null){
			resultVo.setStatus("fail");
			return resultVo;
		}
		
		//评论数+1
        WxArticle article = wxArticleService.findByCode(articleCode);    
        if (article.getCommentNum() == null || "".equals(article.getCommentNum())) {
            article.setCommentNum(1);
        } else {
            article.setCommentNum(article.getCommentNum() + 1);
        }
        wxArticleService.update(article);
		
		Comment comment = new Comment();
		comment.setArticleCode(articleCode);
		comment.setCommentContent(commentContent);
		comment.setMemberCode(loginMember.getCode());
		comment.setWxNickName(loginMember.getWxNickName());
		resultVo = this.save(comment);
		resultVo.setInfoData(JsonConverter.toJson(comment));
		return resultVo;
	}
}
