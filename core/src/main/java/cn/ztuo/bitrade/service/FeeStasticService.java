package cn.ztuo.bitrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.commons.lang3.*;
import org.apache.commons.collections.*;
import java.math.*;
import java.util.*;
import org.slf4j.*;

@Service
public class FeeStasticService
{
    private static final Logger log;
    @Autowired
    private FeeStasticRepository feeStasticRepository;

    public Page<FeeStastic> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<FeeStastic>)this.feeStasticRepository.findAll(predicate, pageable);
    }

    public void save(final FeeStastic feeStastic) {
        this.feeStasticRepository.save(feeStastic);
    }

    public List<FeeStastic> sumAmount(final String unit, final long startSeq, final long endSeq) {
        List<Object[]> list;
        if (StringUtils.isEmpty((CharSequence)unit)) {
            list = this.feeStasticRepository.sumAmountBySeq(startSeq, endSeq);
        }
        else {
            list = this.feeStasticRepository.sumAmountByUnitAndSeq(unit, startSeq, endSeq);
        }
        if (CollectionUtils.isEmpty((Collection)list)) {
            return null;
        }
        final List<FeeStastic> resultList = new ArrayList<FeeStastic>();
        for (final Object[] objects : list) {
            final FeeStastic record = new FeeStastic();
            record.setAmount((BigDecimal)objects[0]);
            record.setUnit(objects[1].toString());
            resultList.add(record);
        }
        return resultList;
    }

    public BigDecimal sumBonusAmount(final List<String> symbols) {
        return this.feeStasticRepository.sumBonusAmount(symbols);
    }

    static {
        log = LoggerFactory.getLogger((Class)FeeStasticService.class);
    }
}
