package cn.ztuo.bitrade.util;

import java.util.*;
import cn.ztuo.bitrade.entity.unblock.*;
import java.math.*;

public class LotteryUtils
{
    public static void main(final String[] args) {
        final int max = (int)(Math.ceil(0.0) / 0.0);
        System.out.println(max);
    }
    
    public static UnblockLotteryConfig generateAward(final List<UnblockLotteryConfig> initDrawList, final int max) {
        final long result = randomNum(1, max);
        int line = 0;
        int temp = 0;
        UnblockLotteryConfig returnobj = null;
        final int index = 0;
        for (int i = 0; i < initDrawList.size(); ++i) {
            final UnblockLotteryConfig obj2 = initDrawList.get(i);
            final int c = obj2.getLotteryRate().multiply(new BigDecimal(max)).intValue();
            temp += c;
            line = max - temp;
            if (c != 0 && result > line && result <= line + c) {
                returnobj = obj2;
                break;
            }
        }
        return returnobj;
    }
    
    private static long randomNum(final int smin, final int smax) {
        final int range = smax - smin;
        final double rand = Math.random();
        return smin + Math.round(rand * range);
    }
}
