package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.dao.*;
import cn.ztuo.bitrade.entity.*;
import java.util.*;
import org.springframework.transaction.annotation.*;
import org.slf4j.*;

@Service
public class ContractDoubleDirectionProfitInfoService extends BaseService
{
    private static final Logger log;
    @Autowired
    private ContractDoubleDirectionProfitInfoDao contractDoubleDirectionProfitInfoDao;
    @Autowired
    private CoinDao coinDao;
    @Autowired
    private MemberTransactionService transactionService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberAccountOperateRecordService memberAccountOperateRecordService;
    @Autowired
    private MemberWalletService memberWalletService;
    public static final Integer limit;
    private static Logger logger;

    public ContractDoubleDirectionOrderProfitInfo save(final ContractDoubleDirectionOrderProfitInfo contractDoubleDirectionOrderProfitInfo) {
        return (ContractDoubleDirectionOrderProfitInfo)this.contractDoubleDirectionProfitInfoDao.saveAndFlush(contractDoubleDirectionOrderProfitInfo);
    }

    public List<ContractDoubleDirectionOrderProfitInfo> getProfitInfoListByStatus(final int status) {
        return this.contractDoubleDirectionProfitInfoDao.getProfitInfoListByStatus(status);
    }

    @Transactional(rollbackFor = { Exception.class })
    public int updateInfo(final Long id, final int status, final int remainDays) {
        return this.contractDoubleDirectionProfitInfoDao.updateStatus(id, remainDays, status);
    }

    static {
        log = LoggerFactory.getLogger((Class)ContractDoubleDirectionProfitInfoService.class);
        limit = 1000;
        ContractDoubleDirectionProfitInfoService.logger = LoggerFactory.getLogger((Class)ContractDoubleDirectionProfitInfoService.class);
    }
}
