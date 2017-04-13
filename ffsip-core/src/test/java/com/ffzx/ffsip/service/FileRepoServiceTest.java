package com.ffzx.ffsip.service;

import com.ffzx.ffsip.TestBase;
import com.ffzx.ffsip.model.FileRepo;
import com.ffzx.ffsip.util.JsonConverter;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/23.
 */
public class FileRepoServiceTest extends TestBase {
    @Resource
    private FileRepoService service;

    @Test
    public void testFindByCode(){
        FileRepo ret=service.findByCode("");
        logger.info("ret :{}", JsonConverter.toJson(ret));
    }

}