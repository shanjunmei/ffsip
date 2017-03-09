package com.ffzx.ffsip.admin.controller;


import com.ffzx.common.utils.WebUtils;
import com.ffzx.ffsip.model.Menu;
import com.ffzx.ffsip.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 */
@Controller
public class IndexController {

    // 记录日志
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }


    /**
     * 根据用户 获取已分配的菜单
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/queryMenuByUserId", method = RequestMethod.POST)
    @ResponseBody
    public Object getMenuListByUserId(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("");
        /*List<Map> menuList = new ArrayList<>();
        Map<String,Object> menu=null;
        menu=new HashMap<>();
        menu.put("id","122222222");
        menu.put("url","/ffsip-admin/User/toList.do");
        menu.put("name","用户管理");
        menu.put("href","/User/toList.do");
        menu.put("sub",new String[]{});
        
        //车辆管理菜单
        Map<String,Object> menuVehicle=null;
        menuVehicle=new HashMap<>();
        menuVehicle.put("id","menuVehicle");
        menuVehicle.put("url","/ffsip-admin/Vehicle/toList.do");
        menuVehicle.put("name","车辆管理");
        menuVehicle.put("href","/Vehicle/toList.do");
        menuVehicle.put("sub",new String[]{});
        
        //营销管理菜单
        Map<String,Object> menuActivity=null;
        menuActivity=new HashMap<>();
        menuActivity.put("id","menuActivity");
        menuActivity.put("url","/ffsip-admin/Activity/toList.do");
        menuActivity.put("name","营销管理");
        menuActivity.put("href","/Activity/toList.do");
        menuActivity.put("sub",new String[]{});
        
        //订单管理
        Map<String,Object> menuOrderInfo=null;
        menuOrderInfo=new HashMap<>();
        menuOrderInfo.put("id","menuOrderInfo");
        menuOrderInfo.put("url","/ffsip-admin/OrderInfo/toList.do");
        menuOrderInfo.put("name","订单管理");
        menuOrderInfo.put("href","/OrderInfo/toList.do");
        menuOrderInfo.put("sub",new String[]{});

        //验证码
        Map<String,Object> menuVerificationCode=null;
        menuVerificationCode=new HashMap<>();
        menuVerificationCode.put("id","menuVerificationCode");
        menuVerificationCode.put("url","/ffsip-admin/OrderInfo/toVerificationCode.do");
        menuVerificationCode.put("name","验证码");
        menuVerificationCode.put("href","/OrderInfo/toVerificationCode.do");
        menuVerificationCode.put("sub",new String[]{});

        menuList.add(menuVerificationCode);
        menuList.add(menuOrderInfo);
        menuList.add(menuActivity);
        menuList.add(menuVehicle);
        menuList.add(menu);*/
        List<Menu> menuList= menuService.selectByExample(null);
        return menuList;
    }

    /**
     * 没有权限跳转页面
     */
    @RequestMapping(value = "/noRight", method = RequestMethod.GET)
    public String noRight(HttpServletRequest request, ModelMap map) {
        String permissions = request.getParameter("permissions");
        if (null != permissions) {
            map.put("permissionStr", permissions);
        }

        return "error/403";
    }

    @RequestMapping(value = "/logout")
    public void noRight(HttpServletRequest request,HttpServletResponse response) throws IOException {
       HttpSession session= WebUtils.getSession();
        session.removeAttribute("loginUser");
        session.invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
       // return "/login.html";
    }


    /**
     * 404页面
     *
     * @return
     */
    @RequestMapping("*")
    public String noHandlerFound404() {
        return "error/noHandlerFound404";
    }

    @RequestMapping("*/**")
    public String noHandlerFound404more() {
        return "error/noHandlerFound404";
    }


}