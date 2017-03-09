package com.ffzx.ffsip.service;

import com.ffzx.ffsip.TestBase;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/9.
 */
public class CompanyService1Test extends TestBase {
    @Resource
    private CompanyService1 service1;

    @Test
    public void hello() throws Exception {
        service1.hello();
    }

}