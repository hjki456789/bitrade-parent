package cn.ztuo.bitrade.utils;

import java.util.concurrent.*;

public class CommonNewCachedThreadPool
{
    private static ExecutorService pool;
    
    public static void runThread(final Thread t) {
        CommonNewCachedThreadPool.pool.execute(t);
    }
    
    static {
        CommonNewCachedThreadPool.pool = Executors.newCachedThreadPool();
    }
}
