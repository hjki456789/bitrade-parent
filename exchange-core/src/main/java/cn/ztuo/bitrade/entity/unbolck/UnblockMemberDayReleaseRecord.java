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
public class UnblockMemberDayReleaseRecord implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String coin;
    private Long memberId;
    private Long unlockId;
    private BigDecimal amount;
    private BigDecimal beforeBalance;
    private BigDecimal afterBalance;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Long sequence;

}
