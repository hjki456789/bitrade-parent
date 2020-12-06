package cn.ztuo.bitrade.entity;


import javax.persistence.*;
import javax.persistence.Entity;
import java.math.*;
import java.util.*;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.*;

/**
 * @Description 投票详情
 * @Author wsy
 * @Date 2020/11/19 22:59
 * @Version 1.0
 **/
@Entity
@Data
public class VoteDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long userId;
    @JoinColumn(name = "vote_id")
    @ManyToOne
    private Vote vote;
    @JoinColumn(name = "pre_coin_id")
    @ManyToOne
    private PreCoin preCoin;
    private BigDecimal amount;
    private int voteAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @CreationTimestamp
    private Date createTime;
}
