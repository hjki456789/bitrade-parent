package cn.ztuo.bitrade.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.ztuo.bitrade.constant.BooleanEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.ztuo.bitrade.constant.TransactionType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @desc 会员交易记录，包括充值、提现、转账等
 */
@Entity
@Data
public class MemberTransaction {
    @Excel(name = "交易记录编号", orderNum = "1", width = 25)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Excel(name = "会员id", orderNum = "2", width = 25)
    private Long memberId;
    /**
     * 交易金额
     */
    @Excel(name = "交易金额", orderNum = "3", width = 25)
    @Column(columnDefinition = "decimal(20,8) comment '充币金额'")
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", orderNum = "4", width = 25)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 交易类型
     */
    @Excel(name = "交易类型", orderNum = "5", width = 25)
    @Enumerated(EnumType.ORDINAL)
    private TransactionType type;
    /**
     * 币种名称，如 BTC
     */
    private String symbol;
    /**
     * 充值或提现地址、或转账地址
     */
    private String address;

    /**
     * 交易手续费
     * 提现和转账才有手续费，充值没有;如果是法币交易，只收发布广告的那一方的手续费
     */
    @Column(precision = 19, scale = 8)
    private BigDecimal fee = BigDecimal.ZERO;

    private String feeUnit;

    /**
     * 标识位，特殊情况会用到，默认为0
     */
    @Column(nullable = false, columnDefinition = "int default 0")
    private int flag = 0;
    /**
     * 空投ID，只有空投时才有
     */
    private Long airdropId;
    /**
     * txid
     */
    private String txid;

    /**
     * 是否是快速提币
     */
    @Enumerated(EnumType.ORDINAL)
    private BooleanEnum isQuick;

    private Long sequence;
    private BigDecimal afterBalance;
    private Long fromMemberId;
    @JoinColumn(name = "memberId", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Member member;
    @Excel(name = "代理商ID", orderNum = "2", width = 25.0)
    @Transient
    private Long proxyId;
    @Excel(name = "邮箱", orderNum = "4", width = 35.0)
    @Transient
    private String email;
    @Excel(name = "手机号", orderNum = "3", width = 30.0)
    @Transient
    private String mobilePhone;
    @Excel(name = "出入金类型", orderNum = "6", width = 25.0)
    @Transient
    private String typeName;
    @Excel(name = "出入金", orderNum = "5", width = 30.0)
    @Transient
    private String amountStr;
    private Long lotteryCount;


    public MemberTransaction() {
        this.fee = BigDecimal.ZERO;
        this.flag = 0;
        this.sequence = System.currentTimeMillis();
    }
}
