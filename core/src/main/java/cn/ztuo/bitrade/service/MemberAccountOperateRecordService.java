package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class MemberAccountOperateRecordService extends BaseService
{
    @Autowired
    private MemberAccountOperateRecordDao memberAccountOperateRecordDao;

    public MemberAccountOperateRecord save(final MemberAccountOperateRecord record) {
        return (MemberAccountOperateRecord)this.memberAccountOperateRecordDao.saveAndFlush(record);
    }
}
