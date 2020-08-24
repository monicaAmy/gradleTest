//package com.lamdba;
//
//import org.joda.time.DateTime;
//import org.joda.time.DateTimeZone;
//import org.joda.time.LocalDate;
//import org.joda.time.format.DateTimeFormat;
//import org.junit.Test;
//
//import java.time.*;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class TimeTest {
//
//
//    @Test
//    public void testTime() {
//        DateTime today = new DateTime();
//
//        DateTime tomorrow = today.plusDays(1);
//
//        String s = today.toString("yyyy-MM-dd");
//        System.out.println(s);
//
//        DateTime dateTime = today.withDayOfMonth(1);
//        System.out.println(dateTime.toString("yyyy-MM-dd"));
//
//        LocalDate localDate = new LocalDate();
//        //三个月后第一天
//        LocalDate localDate1 = localDate.plusMonths(3).dayOfMonth().withMinimumValue();
//        System.out.println("localDate==" + localDate);
//        System.out.println("localDate1===" + localDate1);
//
//        //计算两年前第三个月最后一天的日期
//        DateTime dateTime1 = new DateTime();
//        DateTime dateTime2 = dateTime1.minusYears(2).monthOfYear().setCopy(3).dayOfMonth().withMinimumValue();
//        System.out.println("dateTime2===" + dateTime2.toString("yyyy-MM-dd"));
//
//    }
//
//    @Test
//    public void testUTC() {
//        String utcDate = "2014-11-04T09:22:54.888Z";
//        DateTime parse = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
//        System.out.println("parse===" + parse.toDate());
//
//        Date date = new Date();
//        DateTime dateTime = new DateTime(date, DateTimeZone.UTC);
//        System.out.println("dateTime===" + dateTime.toString());
//
//        Date date1 = new Date();
//        DateTime dateTime1 = new DateTime(date1);
//        System.out.println("dateTime1===" + dateTime1.toString("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @Test
//    public void testjisuan() {
//        LocalDate now = LocalDate.now();
//        now.getYear();
//        now.getMonthOfYear();
//        now.getDayOfMonth();
//
//        java.time.LocalDate localDate = java.time.LocalDate.of(2017, 3, 3);
//        MonthDay of = MonthDay.of(localDate.getMonth(), localDate.getDayOfMonth());
//        java.time.LocalDate localDate1 = java.time.LocalDate.of(2017, 3, 4);
//        MonthDay from = MonthDay.from(localDate1);
//        if (of.equals(from)) {
//            System.out.println("==");
//        } else {
//            System.out.println("！=");
//        }
//        System.out.println("isAfter ?=" + localDate1.isAfter(localDate));
//        System.out.println("isBefore = " + localDate1.isBefore(localDate));
//        System.out.println("equals =" + localDate1.equals(localDate));
//
//
//        LocalTime localTime = LocalTime.now().plusHours(3).plusMinutes(20);
//        System.out.println("localTime====" + localTime);
//
//        java.time.LocalDate plus = localDate.plus(2, ChronoUnit.WEEKS);
//        java.time.LocalDate minus = localDate.minus(2, ChronoUnit.MONTHS);
//
//        Clock clock = Clock.systemDefaultZone();
//        System.out.println("clock.millis===========" + clock.millis());
//        System.out.println(Instant.now());
//
//        YearMonth yearMonth = YearMonth.of(2016, 2);
//    }
//
//    @Test
//    public void testPeriod() {
//        java.time.LocalDate now1 = java.time.LocalDate.now();
//        java.time.LocalDate localDate2 = java.time.LocalDate.of(2021, 8, 16);
//        System.out.println(now1.toString());
//        Period between = Period.between(now1, localDate2);
//        System.out.println(between.getYears());
//        System.out.println(between.getMonths());
//        System.out.println(between.getDays());
//    }
//
//    @Test
//    public void testZone() {
//        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
//        TreeSet<String> strings = new TreeSet<String>() {{
//            addAll(availableZoneIds);
//        }};
//
//        strings.stream().forEach(System.out::println);
//
//        ZoneId bangui = ZoneId.of("Africa/Bangui");
//        LocalDateTime now = LocalDateTime.now();
//        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, bangui);
//
//
//        YearMonth yearMonth = YearMonth.now();
//        yearMonth.lengthOfMonth();
//        System.out.println("是否闰年 = " + yearMonth.isLeapYear());
//    }
//}
