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
public class UnblockMemberOperateRecord implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private String orderId;
    private String symbol;
    private BigDecimal price;
    private BigDecimal currentPrice;
    private BigDecimal amount;
    private BigDecimal beforeBalance;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Long sequence;


}
