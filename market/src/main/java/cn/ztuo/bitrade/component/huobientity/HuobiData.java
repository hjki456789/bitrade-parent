package cn.ztuo.bitrade.component.huobientity;

import lombok.Data;

import java.util.*;

@Data
public class HuobiData
{
    private int countryId;
    private int currencyId;
    private List<HuobiDetail> detail;
}
