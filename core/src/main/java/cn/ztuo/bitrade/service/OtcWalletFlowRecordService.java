package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@Service
public class OtcWalletFlowRecordService extends BaseService
{
    @Autowired
    private OtcWalletFlowRecordDao otcWalletFlowRecordDao;

    public MessageResult saveRecord(OtcWalletFlowRecord otcWalletFlowRecord) {
        otcWalletFlowRecord = otcWalletFlowRecordDao.saveAndFlush(otcWalletFlowRecord);
        if (otcWalletFlowRecord != null) {
            return MessageResult.success("success");
        }
        return MessageResult.error(500, "error");
    }
}
