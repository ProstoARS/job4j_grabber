package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    private static int interval;

    public static void main(String[] args) {
        try (Connection cn = read("rabbit.properties")) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("connect", cn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(interval)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException | SQLException se) {
            se.printStackTrace();
        }
    }

    public static Connection read(String file) {
        ClassLoader loader = AlertRabbit.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream(file)) {
            Properties properties = new Properties();
        properties.load(in);
            interval = Integer.parseInt(properties.getProperty("rabbit.interval"));
            Class.forName(properties.getProperty("driver"));
            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connect");
            try (PreparedStatement ps = cn.prepareStatement(
                    "INSERT INTO rabbit (created_date) values (current_timestamp)")) {
                    ps.execute();
                System.out.println("write to table rabbit completed");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}