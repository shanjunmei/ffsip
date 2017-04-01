package com.ffzx.ffsip.web.controller;

import com.ffzx.common.controller.CoreController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.search.IndexService;
import com.ffzx.ffsip.search.PageHolder;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/31.
 */
@Controller
@RequestMapping("Search")
public class SearchController extends CoreController {

    @Resource
    private IndexService indexService;

    @RequestMapping(value = "toSearch")
    public String toSearch(String keyWords, ModelMap modelMap) {

        if(StringUtils.isBlank(keyWords)){
            return "/articleSearch";
        }
        logger.info("keyWord:{}", keyWords);

        String indexStr = getParameter("pageIndex");
        String sizeStr = getParameter("pageSize");

        int pageIndex = 1;
        int pageSize = 10;
        if (StringUtils.isNotBlank(indexStr)) {
            pageIndex = Integer.valueOf(indexStr);
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            pageSize = Integer.valueOf(sizeStr);
        }

        int total = 0;
        String[] name = new String[]{"title", "content", "publisher"};
        List<WxArticle> dataList = indexService.query(name, keyWords, pageIndex, pageSize);
        total = PageHolder.getTotal();
        modelMap.put("pageTotal", total);
        modelMap.put("list", dataList);
        modelMap.put("keyWords", keyWords);
        return "/articleSearch";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("queryData")
    @ResponseBody
    public ResultVo query(String keyWords) {


        ResultVo resultVo = new ResultVo();
        String indexStr = getParameter("pageIndex");
        String sizeStr = getParameter("pageSize");

        int pageIndex = 1;
        int pageSize = 10;
        if (StringUtils.isNotBlank(indexStr)) {
            pageIndex = Integer.valueOf(indexStr);
        }
        if (StringUtils.isNotBlank(sizeStr)) {
            pageSize = Integer.valueOf(sizeStr);
        }

        int total = 0;
        String[] name = new String[]{"title", "content", "publisher"};
        List<WxArticle> dataList = indexService.query(name, keyWords, pageIndex, pageSize);
        total = PageHolder.getTotal();
        resultVo.setRecordsTotal(total);
        resultVo.setInfoData(dataList);
        return resultVo;
    }

    @RequestMapping("buildIndex")
    @ResponseBody
    public Object buildIndex() {
        Map<String, Object> ret = new HashMap();
        indexService.buildIndex();
        ret.put("code", "0");
        ret.put("msg", "build 成功");
        return ret;
    }

}
