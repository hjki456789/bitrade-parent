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
public class UnblockExchangeSymbol implements Serializable {
    @Id
    private String symbol;
    private BigDecimal singleRoundReleaseRate;
    private BigDecimal dayMaxReleaseRate;
    private BigDecimal dayMaxReleaseAmount;
    private BigDecimal startPrice;
    private BigDecimal priceRiseRule;
    private BigDecimal roundIntervalPrice;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private Long version;
    private int singleDayRoundMax;
    private String roundLimitKey;
    private String roundLimitValue;
    private BigDecimal priceLimit;
    private String whitelistMemberId;

}
