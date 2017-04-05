package com.ffzx.ffsip.web.controller;

import com.ffzx.common.controller.CoreController;
import com.ffzx.common.utils.ResultVo;
import com.ffzx.ffsip.model.WxArticle;
import com.ffzx.ffsip.search.IndexService;
import com.ffzx.ffsip.search.PageHolder;
import com.ffzx.ffsip.search.WxArticleIndexService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.store.Directory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
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
    private WxArticleIndexService wxArticleIndexService;


    @Resource
    private IndexService indexService;

    @RequestMapping(value = "toSearch")
    public String toSearch(String keyWords, ModelMap modelMap) {

        if (StringUtils.isBlank(keyWords)) {
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
        List<WxArticle> dataList = wxArticleIndexService.query(name, keyWords, pageIndex, pageSize);
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
        List<WxArticle> dataList = wxArticleIndexService.query(name, keyWords, pageIndex, pageSize);
        total = PageHolder.getTotal();
        resultVo.setRecordsTotal(total);
        resultVo.setInfoData(dataList);
        return resultVo;
    }

    @RequestMapping("buildIndex")
    @ResponseBody
    public Object buildIndex() {
        Map<String, Object> ret = new HashMap();
        long t = System.currentTimeMillis();
        int result = wxArticleIndexService.buildIndex();
        t = System.currentTimeMillis() - t;
        ret.put("code", "0");
        ret.put("msg", "成功索引：" + result + " 条记录，耗时：" + t + " ms");
        return ret;
    }

    @RequestMapping("removeIndexs")
    @ResponseBody
    public Object removeIndexs() {
        Map<String, Object> ret = new HashMap();
        String basePath=System.getProperty("index.stored.dir");
        if(StringUtils.isBlank(basePath)){
            basePath=System.getProperty("user.home");
        }
         String path = basePath+"/ffsip/index/"+"article";
        long t = System.currentTimeMillis();
        File dir=new File(path);
        if(dir.exists()){
            deleteDir(dir);
        }else{
            logger.info("dir :{},not exist",path);
        }
        t = System.currentTimeMillis() - t;
        ret.put("code", "0");
        ret.put("msg", "删除目录：" + path + " ，耗时：" + t + " ms");
        return ret;
    }

    /**
     * 目录删除
     *
     * @param dir
     */
    public void deleteDir(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            return; // 检查参数
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) file.delete(); // 删除所有文件
            else if (file.isDirectory()) deleteDir(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}
