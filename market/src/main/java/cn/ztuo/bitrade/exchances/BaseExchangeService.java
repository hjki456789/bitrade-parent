package cn.ztuo.bitrade.exchances;

import java.math.*;
import java.util.*;
import cn.ztuo.bitrade.exchances.entity.*;

public interface BaseExchangeService
{
    BigDecimal getSymbolPrice(final String p0);
    
    LastTrade getLastTrade(final String p0);
    
    Map<Side, List<Depth>> getSymbolDepth(final String p0);
}
