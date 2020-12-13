package cn.ztuo.bitrade.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class TransferCoinResult
{
    private String name;
    private String unit;
    private String img;


}
