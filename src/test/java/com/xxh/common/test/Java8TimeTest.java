package com.xxh.common.test;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * Created by wulongtao on 2017/6/29.
 */
public class Java8TimeTest {

    @Test
    public void testClock() {
        Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());
    }

    @Test
    public void testInstant() throws InterruptedException {
        Instant start = Instant.now();

        Thread.sleep(3000);

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        long seconds = duration.getSeconds();
        long millis = duration.toMillis();
        boolean isAfter = end.isAfter(start);

        System.out.println("seconds="+seconds);
        System.out.println("millis="+millis);
        System.out.println("isAfter="+isAfter);
    }

    @Test
    public void testLocalDate() {
        LocalDate today  = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);
    }

    @Test
    public void testLocalTime() {
        LocalTime time = LocalTime.of(20, 30);
        System.out.println(time);
        int hour = time.getHour();
        System.out.println("hour="+hour);
        int minute = time.getMinute();
        System.out.println("minute="+minute);
        time = time.withSecond(6);
        System.out.println(time);
        time = time.plusHours(3);
        System.out.println(time);
    }

    @Test
    public void testDateFormatter() {
        //按照内置的不同方式格式化
        String format = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now());
        String format2 = DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now());
        String format3 = DateTimeFormatter.ISO_DATE.format(LocalDateTime.now());
        String format4 = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
        System.out.println(format);
        System.out.println(format2);
        System.out.println(format3);
        System.out.println(format4);

        //按照标准格式格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        String format5 = formatter.format(LocalDateTime.now());
        System.out.println(format5);

        //按照指定方式格式化
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd E HH:mm:ss");
        String format6 = pattern.format(LocalDateTime.now());
        System.out.println(format6);
    }
}
