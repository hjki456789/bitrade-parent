package cn.ztuo.bitrade.utils;

import java.util.concurrent.*;

public class CommonFixedThreadPool
{
    private static ExecutorService pool;
    
    public static void runThread(final Thread t) {
        CommonFixedThreadPool.pool.execute(t);
    }
    
    static {
        CommonFixedThreadPool.pool = Executors.newFixedThreadPool(200);
    }
}
