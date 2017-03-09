//实际业务操作线程
package com.ffzx.task;

import com.ffzx.ffsip.util.JsonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


@Component
public class TaskExcutor implements Runnable {

    private final static Logger logger = LoggerFactory.getLogger(TaskExcutor.class);
    private volatile boolean shutdown = false;
    @Resource
    private Executor excutor;//Executors.newFixedThreadPool(5);// ApplicationContextUtil.getBean(Executor.class);

    public TaskExcutor() {
        logger.info("TaskExcutor init");
        //excutor.execute(this);
    }

    public static void add(TimeTask<?> task) {
        TaskDataHolder.queue.add(task);
    }

    /**
     * <p>
     * <p>
     * 计算下次执行时间
     * <p>
     * <p>
     * </p>
     *
     * @param date
     * @param x
     * @return 传入时间+（重试次数的二次方分钟）
     * @author
     * @date 2017年2月17日 下午13:55
     * @version
     */
    public static Date calcNextExcuteTime(Date date, int x) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, x);
        return cal.getTime();
    }

    public static <T> TimeTask<T> createTask(Task t, int interval) {
        TimeTask<T> task = new TimeTask<>();
        Date currentTime = new Date();
        task.setCreateTime(currentTime);
        task.setLastExcuteTime(currentTime);
        task.setInterval(interval);
        task.setNextExcuteTime(calcNextExcuteTime(currentTime, interval));
        task.setTask(t);
        logger.info(JsonConverter.toJson(task));
        return task;
    }

    public static <T> void createAndAddTask(Task t, int interval) {
        add(createTask(t, interval));
    }

    public static void main(String[] args) {
        Date current = new Date();
        Date nextTime = calcNextExcuteTime(current, 2);
        System.out.println("x = " + current + " , y = " + nextTime);
    }

    @Override
    public void run() {
        logger.info("TaskExcutor start");

        while (!shutdown) {
            TaskWorker worker = new TaskWorker();
            excutor.execute(worker);

            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.info("exception", e);
            }

        }
    }

    public void stop() {
        shutdown = true;
    }

}