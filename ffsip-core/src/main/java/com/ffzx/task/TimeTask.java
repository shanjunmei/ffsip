//任务实体定义
package com.ffzx.task;

import com.ffzx.ffsip.util.DateUtil;

import java.util.Date;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 * </p>
 *
 * @date 2016年2月23日 上午9:43:33
 */
public class TimeTask<T> {

    private Date createTime;

    private Date lastExcuteTime;

    private Date nextExcuteTime;


    private int interval;

    private Task task;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastExcuteTime() {
        return lastExcuteTime;
    }

    public void setLastExcuteTime(Date lastExcuteTime) {
        this.lastExcuteTime = lastExcuteTime;
    }

    public Date getNextExcuteTime() {
        return nextExcuteTime;
    }

    public void setNextExcuteTime(Date nextExcuteTime) {
        this.nextExcuteTime = nextExcuteTime;
    }


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+"{" +
                "createTime=" + DateUtil.format(createTime,"yyyy-MM-dd HH:mm:ss.SSS") +
                ", lastExcuteTime=" +  DateUtil.format(lastExcuteTime,"yyyy-MM-dd HH:mm:ss.SSS") +
                ", nextExcuteTime=" +  DateUtil.format(nextExcuteTime,"yyyy-MM-dd HH:mm:ss.SSS") +
                ", interval=" + interval +
                ", task=" + task +
                '}';
    }
}