//package scheduler;
//
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//public class Schedler implements Job {
//
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        // 배치 작업에서 수행할 작업을 구현합니다.
//        System.out.println("Hello, world!");
//    }
//
//    public static void main(String[] args) {
//        try {
//            // JobDetail 생성
//            JobDetail job = JobBuilder.newJob(Schedler.class).withIdentity("job1", "group1").build();
//
//            // Trigger 생성
//            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(10).repeatForever())
//                    .build();
//
//            // Scheduler 생성
//            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//
//            // JobDetail과 Trigger를 Scheduler에 등록한다.
//            scheduler.scheduleJob(job, trigger);
//
//            // Scheduler를 시작한다.
//            scheduler.start();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//}
