package com.ffzx.ffsip.task;

import com.ffzx.commerce.framework.context.InitInterface;
import com.ffzx.task.Task;
import com.ffzx.task.TaskExcutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/2/17.
 */
@Component
public class RefundTask implements Task,InitInterface {

    private final static Logger logger = LoggerFactory.getLogger(RefundTask.class);


    @Override
    public void excuete() {
        logger.info("RefundTask excuete");
        try {
       //     service.refundTask();
        } catch (Exception e) {
            logger.info("task excuete error", e);
        }

    }

    //@PostConstruct
    public void init() {
        logger.info("RefundTask init");
        TaskExcutor.createAndAddTask(this, 30*60);
    }
}
