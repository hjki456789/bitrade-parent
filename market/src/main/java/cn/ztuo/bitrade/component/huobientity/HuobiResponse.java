package cn.ztuo.bitrade.component.huobientity;

import lombok.Data;

@Data
public class HuobiResponse
{
    private int code;
    private boolean success;
    private String message;
    private HuobiData data;

}
