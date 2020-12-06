package cn.ztuo.bitrade.entity.unbolck;

import java.io.*;
import java.math.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

@Entity
@Table
@Data
public class UnblockMemberDayUnlockRecord implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin;
    private Long memberId;
    private BigDecimal tradedAmount;
    private BigDecimal singleRoundReleaseRate;
    private BigDecimal blockBalance;
    private BigDecimal dayMaxReleaseRate;
    private BigDecimal dayMaxReleaseAmount;
    private BigDecimal amount;
    private int status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;
    private Long sequence;

}
