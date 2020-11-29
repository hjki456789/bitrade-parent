package cn.ztuo.bitrade.dto;

import lombok.Data;

@Data
public class MemberTeamDto extends BaseMemberDTO
{
    private Long memberId;
    private String username;
    private String mobilePhone;
    private String email;
    private Integer generation;
    private Long teamNumber;
    private Long directNumber;

    public MemberTeamDto() {
        this.generation = 0;
        this.teamNumber = 0L;
        this.directNumber = 0L;
    }
}
