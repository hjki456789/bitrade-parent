package cn.ztuo.bitrade.entity.contractdouble;

import java.io.*;
import javax.persistence.*;

import cn.ztuo.bitrade.entity.*;
import lombok.Data;

@Entity
@Table
@Data
public class ContractDoubleMemberApi implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Long memberId;
    private Long fromExchangeId;
    private String apiKey;
    private String secretKey;
    private int status;
    private int ifDefault;
    private int deleteFlag;
    private Long sequence;
    @Transient
    private String fromExchangeName;
    @Transient
    private Member member;

}
