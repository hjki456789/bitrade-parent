package cn.ztuo.bitrade.service.insurance;

import com.querydsl.core.types.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.insurance.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import cn.ztuo.bitrade.entity.insurance.*;
import org.apache.commons.lang3.*;
import org.apache.commons.collections.*;

import java.math.*;
import java.util.*;

@Service
@Slf4j
public class WholeNetInsuranceFundWalletRecordService {
    @Autowired
    private WholeNetInsuranceFundWalletRecordRepository wholeNetInsuranceFundWalletRecordRepository;

    public Page<WholeNetInsuranceFundWalletRecord> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<WholeNetInsuranceFundWalletRecord>) this.wholeNetInsuranceFundWalletRecordRepository.findAll(predicate, pageable);
    }

    public WholeNetInsuranceFundWalletRecord findById(final Long id) {
        return (WholeNetInsuranceFundWalletRecord) this.wholeNetInsuranceFundWalletRecordRepository.getOne(id);
    }

    public WholeNetInsuranceFundWalletRecord save(final WholeNetInsuranceFundWalletRecord record) {
        return this.wholeNetInsuranceFundWalletRecordRepository.saveAndFlush(record);
    }

    public List<WholeNetInsuranceFundWalletRecord> sumAmount(final String coin, final long startSeq, final long endSeq) {
        List<Object[]> list;
        if (StringUtils.isEmpty((CharSequence) coin)) {
            list = this.wholeNetInsuranceFundWalletRecordRepository.sumAmountBySeq(startSeq, endSeq);
        } else {
            list = this.wholeNetInsuranceFundWalletRecordRepository.sumAmountByCoinAndSeq(coin, startSeq, endSeq);
        }
        if (CollectionUtils.isEmpty((Collection) list)) {
            return null;
        }
        final List<WholeNetInsuranceFundWalletRecord> resultList = new ArrayList<WholeNetInsuranceFundWalletRecord>();
        for (final Object[] objects : list) {
            final WholeNetInsuranceFundWalletRecord record = new WholeNetInsuranceFundWalletRecord();
            record.setAmount((BigDecimal) objects[0]);
            record.setCoin(objects[1].toString());
            resultList.add(record);
        }
        return resultList;
    }

}
