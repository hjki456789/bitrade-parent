package cn.ztuo.bitrade.job;

import cn.ztuo.bitrade.processor.*;

public class KlineGeneratorThread implements Runnable
{
    private CoinProcessor coinProcessor;
    private int range;
    private int field;
    private long time;
    
    public KlineGeneratorThread(final CoinProcessor coinProcessor, final int range, final int field, final long time) {
        this.coinProcessor = coinProcessor;
        this.range = range;
        this.field = field;
        this.time = time;
    }
    
    @Override
    public void run() {
        this.coinProcessor.generateKLine(this.range, this.field, this.time);
    }
}
