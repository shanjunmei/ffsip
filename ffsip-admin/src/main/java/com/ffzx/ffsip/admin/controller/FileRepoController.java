package com.ffzx.ffsip.admin.controller;

import com.ffzx.common.controller.BaseController;
import com.ffzx.ffsip.model.FileRepo;
import com.ffzx.ffsip.model.FileRepoExample;
import com.ffzx.ffsip.util.JsonConverter;
import com.ffzx.ffsip.service.FileRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/22.
 */
@Controller
@RequestMapping("/FileRepo")
public class FileRepoController extends BaseController<FileRepo,String,FileRepoExample> {

    @Resource
    private FileRepoService service;


    @Override
    public FileRepoService getService() {
        return service;
    }


    @RequestMapping("upload")
    @ResponseBody
    public Object upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) {
        logger.info("upload start ");
        logger.info(file.getName());
        FileRepo fileRepo = new FileRepo();
        fileRepo.setName(file.getOriginalFilename());
        fileRepo.setContentType(file.getContentType());
        try {
            fileRepo.setContent(file.getBytes());
            save(fileRepo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info(JsonConverter.toJson(fileRepo));
        return fileRepo;
    }
    @RequestMapping("file")
    public void file(HttpServletRequest request, HttpServletResponse response,String id){
        FileRepo repo= getService().findById(id);

        try {
           // response.addHeader("Content-Disposition","attachment; filename='" + URLEncoder.encode(repo.getName(),"utf-8") + "'");
            OutputStream out=response.getOutputStream();
            if(repo!=null&&repo.getContent()!=null){
                response.setContentType(repo.getContentType());
                out.write(repo.getContent());
                out.flush();
            }else{
                logger.info("can't found file:{}",id);
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
