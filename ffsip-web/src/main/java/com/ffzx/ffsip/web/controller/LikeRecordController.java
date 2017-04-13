package com.ffzx.ffsip.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffzx.common.controller.BaseController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Comment;
import com.ffzx.ffsip.model.LikeRecord;
import com.ffzx.ffsip.model.LikeRecordExample;
import com.ffzx.ffsip.model.Member;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.service.CommentService;
import com.ffzx.ffsip.service.LikeRecordService;
import com.ffzx.ffsip.service.WxArticleService;

/**
 * 点赞记录
 * @author liujunjun
 * @time：2017年4月6日 下午2:41:34
 * @version 1.0.0
 */
@Controller
@RequestMapping("likeRecord")
public class LikeRecordController extends BaseController<LikeRecord, String, LikeRecordExample> {

	@Resource
	private LikeRecordService service;

	@Resource
	private CommentService commentService;

	@Resource
	private WxArticleService wxArticleService;

	@Override
	public LikeRecordService getService() {
		return service;
	}

	/**
	 * 点赞或取消点赞
	 * @param article_or_comment
	 * @param member_code
	 * @param type		1:文章点赞；2：评论点赞
	 * @return
	 */
	@RequestMapping("addLikeRecord")
	@ResponseBody
	public ResultVo addLikeRecord(String article_or_comment,int type){
        Member loginMember = WebUtils.getSessionAttribute("loginMember"); 
        ResultVo resultVo = new ResultVo();

		if(loginMember == null || StringUtils.isEmpty(article_or_comment)){
			resultVo.setStatus("fail");
			return resultVo;
		}
		
		//取消点赞
		LikeRecordExample example = new LikeRecordExample();
		example.createCriteria().andArticleOrCommentEqualTo(article_or_comment).andMemberCodeEqualTo(loginMember.getCode());
		List<LikeRecord> list = service.selectByExample(example);			
		if(list != null && list.size() > 0){
			if(type == 1){		//文章点赞可以取消
				WxArticle article = wxArticleService.findByCode(article_or_comment);
				int num = article.getForwardingNum() > 0 ? (article.getForwardingNum() - 1) : 0;
				article.setForwardingNum(num);
				wxArticleService.update(article);
				
				resultVo = this.delete(list.get(0).getId());
				resultVo.setInfoData(1);
			}
			resultVo.setInfoData(1);
			resultVo.setStatus("success");
			return resultVo;
		}
		
		//新增点赞
		LikeRecord likeRecord = new LikeRecord();
		likeRecord.setArticleOrComment(article_or_comment);
		likeRecord.setMemberCode(loginMember.getCode());
		resultVo = this.save(likeRecord);
		resultVo.setInfoData(0);
		
		//修改文章或评论里的点赞数量
		if(type == 1){
			WxArticle article = wxArticleService.findByCode(article_or_comment);
			if(article.getForwardingNum() == null){
				article.setForwardingNum(1);				
			}else{
				article.setForwardingNum(article.getForwardingNum() + 1);			
			}
			wxArticleService.update(article);
		}else{
			Comment comment = commentService.findByCode(article_or_comment);
			if(comment.getLikeNum() == null){
				comment.setLikeNum(1);				
			}else{
				comment.setLikeNum(comment.getLikeNum() + 1 );				
			}
			commentService.update(comment);
		}		
		
		return resultVo;
	}
}
