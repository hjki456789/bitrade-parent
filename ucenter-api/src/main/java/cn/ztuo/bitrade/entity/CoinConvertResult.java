package cn.ztuo.bitrade.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Builder
@Data
@NoArgsConstructor
public class CoinConvertResult
{
    private String baseCoin;
    private String baseCoinImg;
    private List<ConvertInfo> convertInfos;

}
