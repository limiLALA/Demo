package demo;

import com.mysql.cj.jdbc.MysqlDataSourceFactory;

import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class TimeDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        long pre = 0;
        for (int i = 0; i < 10; i++) {
            long cu = System.nanoTime();
            System.out.println(cu - pre);
            pre = cu;
        }
        System.out.println(new Date());

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", " 123456");


        Statement statement = conn.createStatement();
        String sql = "select * from titles";
        ResultSet resultSet = statement.executeQuery(sql);
//        // 获得当前的日期和时间
//        LocalDateTime currentTime = LocalDateTime.now();
//        System.out.println("current date and time: " + currentTime);
//
//        // 输出当前时间的本地值（本时区）
//        LocalDate date1 = currentTime.toLocalDate();
//        System.out.println("local date: " + date1);
//
//        Month month = currentTime.getMonth();
//        int day = currentTime.getDayOfMonth();
//        int seconds = currentTime.getSecond();
//
//        // 由当前时间对象获得各个字段，输出结果
//        System.out.println("month: " + month + "day: " + day + "seconds: " + seconds);
//
//        // 由当前时间附带月份和年再输出
//        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2012);
//        System.out.println("date 2: " + date2);
//
//        // 输出2016年圣诞节的日期
//        LocalDate date3 = LocalDate.of(2016, Month.DECEMBER, 25);
//        System.out.println("date 3: " + date3);
//
//        // 输出新闻联播的开始时间
//        LocalTime date4 = LocalTime.of(19, 00);
//        System.out.println("date 4: " + date4);
//
//        // 转化为字符串，再输出
//        LocalTime date5 = LocalTime.parse("20:15:30");
//        System.out.println("date 5: " + date5);
//
//        // 将字符串代表的时区信息转化
//        ZonedDateTime date6 = ZonedDateTime.parse("2016-04-20T19:22:15+01:30[Europe/Paris]");
//        System.out.println("date1: " + date6);
//
//        // 设定地区ID为亚洲的加尔各答（位于印度），并输出
//        ZoneId id = ZoneId.of("Asia/Kolkata");
//        System.out.println("ZoneId: " + id);
//
//        // 获得系统默认的当前地区并输出
//        ZoneId currentZone = ZoneId.systemDefault();
//        System.out.println("CurrentZone: " + currentZone);
//
//
//        // 获得当前的日期并输出
//        LocalDate today = LocalDate.now();
//        System.out.println("Current date: " + today);
//
//        // 在当前日期的基础上增加两周时间再输出
//        LocalDate nextWeek = today.plus(2, ChronoUnit.WEEKS);
//        System.out.println("two weeks after now: " + nextWeek);
//
//        // 在当前日期的基础上增加6个月的时间再输出
//        LocalDate nextMonth = today.plus(6, ChronoUnit.MONTHS);
//        System.out.println("6 months after now: " + nextMonth);
//
//        // 在当前日期的基础上增加5年的时间再输出
//        LocalDate nextYear = today.plus(5, ChronoUnit.YEARS);
//        System.out.println("5 years after now: " + nextYear);
//
//        // 在当前日期的基础上增加20年的时间再输出（一个Decade为10年）
//        LocalDate nextDecade = today.plus(2, ChronoUnit.DECADES);
//        System.out.println("20 years after now: " + nextDecade);
    }
}