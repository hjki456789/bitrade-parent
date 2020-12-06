package cn.ztuo.bitrade.entity;

/**
 * @Description TODO
 * @Author wsy
 * @Date 2020/11/19 22:48
 * @Version 1.0
 **/

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class PreCoin {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String unit;
    private String remark;
    private int amount;
    @Column(columnDefinition = "varchar(256) comment '详情链接'")
    private String link;
    @JsonIgnore
    @ManyToOne(targetEntity = Vote.class)
    private Vote vote;
    @JsonIgnore
    @Version
    private long version;

    public PreCoin() {
        this.amount = 0;
    }
}

