package com.project.elasticsearch.utils;


import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static int calculatePageSize(long items, int currentPage, int numberOfThreads) {
        if (items < numberOfThreads) {
            if (currentPage == 0) {
                return (int) items;
            } else {
                return 0;
            }
        } else {
            return (int) Math.ceil(items / (double) numberOfThreads);
        }
    }

    public static long daysDifference(Date d1, Date d2)
    {
        long l = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(l, TimeUnit.MILLISECONDS);
    }

}

