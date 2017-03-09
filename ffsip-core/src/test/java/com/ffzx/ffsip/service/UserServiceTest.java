package com.ffzx.ffsip.service;

import com.ffzx.ffsip.TestBase;
import com.ffzx.ffsip.model.User;
import com.ffzx.ffsip.util.JsonConverter;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class UserServiceTest extends TestBase{

    @Resource
    private UserService userService;

    @Test
    public void testSelectByExample(){
       List<User> list= userService.selectByExample(null);
        logger.info(JsonConverter.toJson(list));
    }

}
