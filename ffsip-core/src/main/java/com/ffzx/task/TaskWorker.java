//轮询主线程
package com.ffzx.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TaskWorker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run() {
        TimeTask<?> task = TaskDataHolder.queue.poll();
        if (task != null) {
            try {
                int interval = 0;
                Date currentTime = new Date();
                Date lastExcueteTime = task.getLastExcuteTime();
                Date nextExcuteTime = task.getNextExcuteTime();
                interval = task.getInterval();
                if (interval > 0) {
                    if ((nextExcuteTime.before(currentTime) && currentTime.after(TaskExcutor.calcNextExcuteTime(lastExcueteTime, interval)))) {// 执行时间在最后执行时间之后
                        task.getTask().excuete();
                        taskUpdate(task);
                        TaskExcutor.add(task);
                    } else {
                        TaskExcutor.add(task);
                    }
                }

            } catch (Exception e) {
                logger.info("task exucete fail :", e);
                taskUpdate(task);
                TaskExcutor.add(task);
            }
        }

    }

    private void taskUpdate(TimeTask<?> task) {
        Date currentTime = new Date();

        task.setNextExcuteTime(TaskExcutor.calcNextExcuteTime(currentTime, task.getInterval()));
        task.setLastExcuteTime(currentTime);
        logger.info(task.toString());
    }

    public String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(date);
    }

}