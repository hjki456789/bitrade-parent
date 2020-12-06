package cn.ztuo.bitrade.entity;

import cn.ztuo.bitrade.constant.LoginStatus;
import cn.ztuo.bitrade.constant.VerifyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Seven
 * @date 2019年01月02日
 */
@Entity
@Data
public class MemberVerifyRecord {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long memberId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private VerifyType type;

    private String ip;

}
