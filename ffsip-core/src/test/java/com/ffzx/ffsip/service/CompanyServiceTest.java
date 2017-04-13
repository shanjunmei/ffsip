package com.ffzx.ffsip.service;

import com.ffzx.ffsip.TestBase;
import com.ffzx.ffsip.model.Company;
import com.ffzx.ffsip.util.JsonConverter;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/9.
 */
public class CompanyServiceTest extends TestBase {
    @Resource
    private CompanyService service;



    @Test
    public void testFindByCode() throws Exception {
      Company company= service.findByCode("");
        logger.info("ret {}", JsonConverter.toJson(company));
    }


}