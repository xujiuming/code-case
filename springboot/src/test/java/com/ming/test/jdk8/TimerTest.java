package com.ming.test.jdk8;

import com.ming.base.job.TestJob;
import groovy.lang.GroovyShell;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.springframework.scheduling.annotation.Scheduled;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时器 各种实现 测试用例类
 *
 * @author ming
 * @date 2017-11-07 10:32
 */
public class TimerTest {
    /**
     * 任务间隔时间
     */
    private final Long timeInterval = 1000L;

    /**
     * 主线程休眠10s
     *
     * @author ming
     * @date 2017-11-07 10:42
     */
    @After
    public void sleepMainThread() throws InterruptedException {
        //主进程不关闭 否则整个测试用例结束 子线程也杀死
        Thread.sleep(10000L);
    }

    /**
     * 使用线程方式实现 sleep
     *
     * @author ming
     * @date 2017-11-07 10:33
     */
    @Test
    public void testThread() throws InterruptedException {
        new Thread(() -> {
            while (true) {
                System.out.println("执行定时任务:" + System.currentTimeMillis());
                try {
                    //休眠 指定时间
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    /**
     * 使用timer+timerTask 实现定时任务测试用例
     *
     * @author ming
     * @date 2017-11-07 10:38
     */
    @Test
    public void testTimerAndTimerTask() throws InterruptedException {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行定时任务:" + System.currentTimeMillis());
            }
        };
        timer.scheduleAtFixedRate(task, 0, timeInterval);
        //task 2
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("执行定时任务2:" + System.currentTimeMillis());
            }
        }, 0, timeInterval);
        //2s后停止这个定时器上所有任务
        Thread.sleep(2000L);
        System.out.println(timer.purge());
        ;
        timer.cancel();
    }


    /**
     * 定时线程池实现 定时任务
     *
     * @author ming
     * @date 2017-11-07 10:43
     */
    @Test
    public void testScheduledExecutorService() {
        //创建 定时任务线程池 服务
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 创建定时任务
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("执行定时任务:" + System.currentTimeMillis())
                , 0, timeInterval, TimeUnit.MILLISECONDS);
        //第二个定时任务
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("执行定时任务2：" + System.currentTimeMillis())
                , 0, timeInterval, TimeUnit.MILLISECONDS);
    }

    /**通过简单的trigger来触发  定时任务
     *
     *@author ming
     *@date 2017-11-07 14:32
     */
    @Test
    public void testSimpleTriggerRunner() throws SchedulerException {
        //定义任务详情
        JobDetail jobDetail = new JobDetail("testJob","testGroup", TestJob.class);
        //设定任务执行触发规则
        SimpleTrigger simpleTrigger = new SimpleTrigger("trigger","tgroup");
        //从当前时间开始
        simpleTrigger.setStartTime(new Date());
        //1s执行
        simpleTrigger.setRepeatInterval(1000L);
        //执行多少次
        simpleTrigger.setRepeatCount(5);

        //获取调度器实例
        SchedulerFactory factory =  new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        //添加定时任务和定时触发规则
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        //开启调度器
        scheduler.start();
    }


    /**使用cron 表达式 来触发定时任务
     *
     *@author ming
     *@date 2017-11-07 14:33
     */
    @Test
    public void testCronTriggerRunner() throws ParseException, SchedulerException {
        JobDetail jobDetail = new JobDetail("testJob",TestJob.class);
        //必须要有name  否则报错
        CronTrigger cronTrigger = new CronTrigger("cron");
        //启动时间
        cronTrigger.setStartTime(new Date());
        //每1s 执行一次
        cronTrigger.setCronExpression("* * * * * ?");
        //获取定时调度器
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        //添加定时规则和定时任务到定时调度器
        scheduler.scheduleJob(jobDetail,cronTrigger);
        //启动
        scheduler.start();

    }

    /**排除自定义特殊日期
     *
     *@author ming
     *@date 2017-11-07 15:46
     */
    @Test
    public void testQuartzExcluded() throws SchedulerException {
        //排除五一
        GregorianCalendar wuyi = new GregorianCalendar();
        wuyi.add(Calendar.MONTH,5);
        wuyi.add(Calendar.DATE,1);

        //添加排除日期
        AnnualCalendar annualCalendar = new AnnualCalendar();
        annualCalendar.setDaysExcluded(Lists.newArrayList(wuyi));

        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        //设定 Calendar
        scheduler.addCalendar("exdate",annualCalendar,false,false);


        Date runDate = TriggerUtils.getDateOf(0,0,10,1,4);
        JobDetail jobDetail = new JobDetail("job","g",TestJob.class);
        SimpleTrigger simpleTrigger = new SimpleTrigger("t","g",runDate,null,SimpleTrigger.REPEAT_INDEFINITELY,60L*60L*1000L);
        //使用Calendar
        simpleTrigger.setCalendarName("exdate");

        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();
    }


    /**
     * spring schedule  简单定时器
     *
     * @author ming
     * @date 2017-11-07 10:59
     */
    @Test
    @Scheduled(fixedRate = 1000L)
    public void testScheduledBySpringSimple() {

    }

    /**
     * spring schedule 使用cron 表达式定时器
     *
     * @author ming
     * @date 2017-11-07 11:07
     */
    @Test
    @Scheduled(cron = "")
    public void testScheduledBySpringCron() {

    }

    /**
     * spring qz 简单定时器
     *
     * @author ming
     * @date 2017-11-07 11:00
     */
    @Test
    public void testQzBySimple() {
    }

    /**
     * spring qz 使用cron定时器
     *
     * @author ming
     * @date 2017-11-07 11:07
     */
    @Test
    public void testQzByCron() {
    }


}
