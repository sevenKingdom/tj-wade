package com.carry.commit;

import java.util.Calendar;

/**
 * Created by songxianying on 17/8/6.
 */
public class DateUtil {
    /**
     * 获取今天的起始时间
     * @return
     */
    public static Calendar getStartTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    /**
     * 获取今天的结束时间
     * @return
     */
    public static Calendar getEndTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar;
    }
}
