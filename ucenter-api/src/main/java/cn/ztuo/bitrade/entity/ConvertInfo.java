package cn.ztuo.bitrade.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class ConvertInfo
{
    private String convertCoin;
    private String convertCoinImg;
    private double rate;
    private double fee;


}
