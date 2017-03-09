//任务队列
package com.ffzx.task;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by Administrator on 2017/2/17.
 */
public class TaskDataHolder {

    public final static Queue<TimeTask<?>> queue = new ConcurrentLinkedQueue<>();
}