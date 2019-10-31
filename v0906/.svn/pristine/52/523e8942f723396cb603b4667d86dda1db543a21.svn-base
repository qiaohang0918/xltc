package demo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
@SuppressWarnings("all")

/**
 * TODO
 *
 * @author wanghao
 * @time 2019-05-14 16:20
 */
public class javaQuartz {
    public static void main(String[] args) {
        //IntStream.range(0, 1).forEachOrdered(i -> javaQuartz.start("myJob" + i, "group" + i, "tigger" + i));
       /* for (int i = 0; i < 10; i++) {
            javaQuartz.stop();
        }*/
        javaQuartz.stop();
    }

    public static void start(String name, String group, String triggerName) {
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();

            Scheduler sched = schedFact.getScheduler();

            sched.start();
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .usingJobData("orderId", System.currentTimeMillis() + "")
                    .withIdentity(name, group)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Date date = new Date();
            date.setTime(date.getTime() + 5 * 1000);

            Trigger trigger = newTrigger()
                    .withIdentity(triggerName, group)
                    .startAt(date)

                    .build();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);
            System.err.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //sched.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public static void stop() {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

        try {
            Scheduler scheduler = schedFact.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    String jobName = jobKey.getName();
                    String jobGroup = jobKey.getGroup();
                    //get job's trigger
                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    Date nextFireTime = triggers.get(0).getNextFireTime();
                    System.out.println("[jobName] : " + jobName + " [groupName] : "
                            + jobGroup + " - " + nextFireTime);
                    if ("myJob1".equals(jobName) && "group1".equals(groupName)) {
                        scheduler.deleteJob(jobKey);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
