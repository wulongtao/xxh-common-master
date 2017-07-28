package com.xxh.common.util;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by wulongtao on 2017/7/7.
 */
public class TimeUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static long currentTimeMillis() {
        Clock clock = Clock.systemUTC();
        return clock.millis();
    }

    /**
     * 
     * @return
     */
    public static String currentTimeStr() {
        return currentTimeStr("yyyy-MM-dd HH:mm:ss");
    }

    public static String currentTimeStr(String pattern) {
        Clock clock = Clock.systemUTC();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return dateTimeFormatter.format(LocalDateTime.now(clock));
    }
}
