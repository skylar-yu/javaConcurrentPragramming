package com.op.concurrent.mytest.delayQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Administrator on 2017/10/12.
 */
class DelayedTask implements Runnable, Delayed {

    private static int counter = 0;
    protected static List<DelayedTask> sequence = new ArrayList<>();
    private final int id = counter++;
    private final int delayTime;
    private final long triggerTime;

    public DelayedTask(int delayInMillis) {
        delayTime = delayInMillis;
        triggerTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask) o;
        if (triggerTime < that.triggerTime) return -1;
        if (triggerTime > that.triggerTime) return 1;
        return 0;
    }

    /**
     * Ê£ÓàµÄÑÓ³ÙÊ±¼ä
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(triggerTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]", delayTime) + " Task " + id;
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService exec) {
            super(delay);
            this.exec = exec;
        }

        @Override
        public void run() {
            System.out.println(this + " calling shutDownNow()");
            exec.shutdownNow();
        }
    }
}