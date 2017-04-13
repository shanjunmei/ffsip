package com.ffzx.weixin.menu;

import com.ffzx.ffsip.util.HttpClient;
import com.ffzx.ffsip.util.JsonConverter;
import com.ffzx.ffsip.wechat.WechatApiService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Administrator on 2017/3/1.
 */
@Component
public class WxMenuApiService {

    public static String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={ACCESS_TOKEN}";

    private static Logger logger = LoggerFactory.getLogger(WxMenuApiService.class);

    @Resource
    private WechatApiService wechatApiService;

    public static void createMenu() {
        Map<String, List> menuMap = new HashMap<>();
        List menuList = new ArrayList();
        menuMap.put("button", menuList);

        Map<String, String> menu = new HashMap<>();
        menu.put("type", "view");
        menu.put("url", "http://ffsip.ffzxnet.com");
        menu.put("name", "首页");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("type", "view");
        menu.put("url", "http://ffsip.ffzxnet.com/center.html");
        menu.put("name", "个人中心");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("type", "view");
        menu.put("url", "http://ffsip.ffzxnet.com/siteChoose.html");
        menu.put("name", "在线预订");
        menuList.add(menu);
        String body = JsonConverter.toJson(menuMap);

        String token = "YJn8v4pqj2jn_FhBsynPKhxcPuBO4aDr1QxYvPKTuW7Y9dE-qf8maCL5F7dnMqe6Lexhh9mp_1-KxX1B2KoM3Np5wV09jdDdHRCifdqLhAT3m9HRR-mCEZ4CvmEAWHQuYBFdABATWA";
        String ul = url.replace("{ACCESS_TOKEN}", token);
        System.out.println(body);
       String response= HttpClient.post(ul, body);
        System.out.println(response);

    }

    public  String createMenu(List<com.ffzx.ffsip.model.WxMenu> menus) {
        List<WxMenu> list = preProcess(menus);
        Map<String, List> menuMap = new HashMap<>();
        menuMap.put("button", list);
        String body = JsonConverter.toJson(menuMap);

        String token = wechatApiService.getAccessToken();
        String ul = url.replace("{ACCESS_TOKEN}", token);
        String response = HttpClient.post(ul, body);
        logger.info(body);
     //   logger.info(response);
        return response;
    }

    protected static List<WxMenu> preProcess(List<com.ffzx.ffsip.model.WxMenu> menus) {
        Collections.sort(menus,new WxMenuComplexComparetor());
        List<WxMenu> list = new ArrayList<>();
        Map<String, com.ffzx.ffsip.model.WxMenu> menuMap = new HashMap<>();
        Map<String, WxMenu> wxMenuMap = new HashMap<>();
        for (com.ffzx.ffsip.model.WxMenu m : menus) {
            menuMap.put(m.getId(), m);
            if (StringUtils.isBlank(m.getpCode())) {
                WxMenu wxM = convert(m);
                list.add(wxM);
                wxMenuMap.put(m.getCode(), wxM);
            }
        }
        for (com.ffzx.ffsip.model.WxMenu m : menus) {
            menuMap.put(m.getId(), m);
            if (StringUtils.isNotBlank(m.getpCode())) {
                WxMenu wxM = wxMenuMap.get(m.getpCode());
                List<WxMenu> dl = wxM.getSub_button();
                if (dl == null) {
                    dl = new ArrayList<>();
                    wxM.setSub_button(dl);
                }
                dl.add(convert(m));
            }
        }
        return list;
    }

    public static WxMenu convert(com.ffzx.ffsip.model.WxMenu menu) {
        WxMenu wxMenu = new WxMenu();
        wxMenu.setName(menu.getName());
        //wxMenu.setKey(menu.getCode());
        wxMenu.setType(menu.getType());
        wxMenu.setUrl(menu.getUrl());
        return wxMenu;
    }

    public static void main(String aryz[]) {
        createMenu();
    }

    static class WxMenuLevelComparetor implements Comparator<com.ffzx.ffsip.model.WxMenu> {

        @Override
        public int compare(com.ffzx.ffsip.model.WxMenu o1, com.ffzx.ffsip.model.WxMenu o2) {
            if (StringUtils.isBlank(o1.getpCode())) {
                if (StringUtils.isNotBlank(o2.getpCode())) {
                    return 1;
                }

            } else if (StringUtils.isNotBlank(o1.getpCode())) {
                if (StringUtils.isBlank(o2.getpCode())) {
                    return -1;
                }
            }
            return 0;
        }
    }

    static class WxMenuSortComparetor implements Comparator<com.ffzx.ffsip.model.WxMenu> {

        @Override
        public int compare(com.ffzx.ffsip.model.WxMenu o1, com.ffzx.ffsip.model.WxMenu o2) {
            Integer i1 = o1.getSort();
            Integer i2 = o2.getSort();

            if (i1 == null) {
                i1 = 0;
            }
            if (i2 == null) {
                i2 = 0;
            }

            if (i1 > i2) {
                return 1;
            } else if (i2 > i1) {
                return -1;
            }
            return 0;
        }
    }

    static class WxMenuComplexComparetor implements Comparator<com.ffzx.ffsip.model.WxMenu> {

        WxMenuLevelComparetor levelComparetor = new WxMenuLevelComparetor();
        WxMenuSortComparetor sortComparetor = new WxMenuSortComparetor();
        List<Comparator<com.ffzx.ffsip.model.WxMenu>> comparators = new ArrayList<>();

        {
            comparators.add(levelComparetor);
            comparators.add(sortComparetor);
        }

        @Override
        public int compare(com.ffzx.ffsip.model.WxMenu o1, com.ffzx.ffsip.model.WxMenu o2) {

            for (Comparator<com.ffzx.ffsip.model.WxMenu> comp : comparators) {
                if (comp.compare(o1, o2) > 0) {
                    return 1;
                } else if (comp.compare(o1, o2) < 0) {
                    return -1;
                }
            }

            return 0;
        }
    }
}
