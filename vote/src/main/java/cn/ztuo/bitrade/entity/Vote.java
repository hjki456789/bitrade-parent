package cn.ztuo.bitrade.entity;


import lombok.Data;
import org.hibernate.annotations.*;
import com.fasterxml.jackson.annotation.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
/**
 * @Description 投票
 * @Author wsy
 * @Date 2020/11/19 22:56
 * @Version 1.0
 **/
@Entity
@Data
public class Vote
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @Enumerated(EnumType.ORDINAL)
    private BooleanEnum status;
    @Column(columnDefinition = "decimal(18,2) comment '每次投票消耗的平台币数量'")
    private BigDecimal amount;
    private int voteLimit;
    @OrderBy("id")
    @OneToMany(targetEntity = PreCoin.class, cascade = { CascadeType.ALL })
    @JoinColumn(name = "vote_id")
    private List<PreCoin> preCoins;
}
