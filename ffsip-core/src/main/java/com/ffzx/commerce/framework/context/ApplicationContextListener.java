package com.ffzx.commerce.framework.context;

import com.ffzx.commerce.framework.utils.SpringContextHolder;
import com.ffzx.task.TaskExcutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created by Administrator on 2017/2/23.
 */
@Component
public class ApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private Executor excutor;

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        logger.info(event.toString());
        TaskExcutor taskExcutor = SpringContextHolder.getBean(TaskExcutor.class);
        if (event instanceof ContextStartedEvent) {

        } else if (event instanceof ContextStoppedEvent) {

        } else if (event instanceof ContextClosedEvent) {
            taskExcutor.stop();
        } else if (event instanceof ContextRefreshedEvent) {
            if (event.getApplicationContext().getParent() == null) {
               Map<String,InitInterface> beans= SpringContextHolder.getApplicationContext().getBeansOfType(InitInterface.class);
                for (Map.Entry<String,InitInterface> e:beans.entrySet() ) {
                    e.getValue().init();
                }
                excutor.execute(taskExcutor);
            }
        }

    }
}
