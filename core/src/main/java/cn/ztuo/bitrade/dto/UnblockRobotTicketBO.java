package cn.ztuo.bitrade.dto;

import lombok.Data;

@Data
public class UnblockRobotTicketBO
{
    private String id;
    private Integer status;
    private Long memberId;
}
