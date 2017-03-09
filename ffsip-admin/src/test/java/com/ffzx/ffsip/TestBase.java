package com.ffzx.ffsip;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
* @ClassName: TestBase 
* @Description: 单元测试基类
* @author 李淼淼  445052471@qq.com
* @date 2016年6月23日 上午9:08:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public abstract class TestBase {

	protected Logger logger = LoggerFactory.getLogger(getClass());
}
