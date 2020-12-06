package cn.ztuo.bitrade.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
public class OtcWalletDTO extends BaseMemberDTO {

    private Long id;

    private String unit;

    private BigDecimal balance;

    private BigDecimal frozenBalance;

    private BigDecimal allBalance;
}
