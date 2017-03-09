package com.ffzx.weixin.menu;


import com.ffzx.ffsip.TestBase;
import com.ffzx.ffsip.mapper.WxMenuMapper;
import com.ffzx.ffsip.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public class WxMenuServiceTest extends TestBase {

    @Resource
    private WxMenuMapper wxMenuMapper;
    @Resource
    private WxMenuService wxMenuService;

    @org.junit.Test
    public void createMenu() throws Exception {
        WxMenuExample example=new WxMenuExample();
        example.createCriteria().andIdGreaterThan("1");
        List<com.ffzx.ffsip.model.WxMenu>  data=wxMenuMapper.selectByExample(example);
        wxMenuService.createMenu(data);
    }

    @org.junit.Test
    public void createMenu1() throws Exception {
        WxMenuService.createMenu();
    }

}