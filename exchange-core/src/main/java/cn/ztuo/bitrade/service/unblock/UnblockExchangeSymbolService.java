package cn.ztuo.bitrade.service.unblock;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.unblock.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.unbolck.*;

import java.io.*;

import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

import java.util.*;

@Service
public class UnblockExchangeSymbolService {
    @Autowired
    private UnblockExchangeSymbolRepository unblockExchangeSymbolRepository;

    public UnblockExchangeSymbol findBySymbol(final String symbol) {
        return (UnblockExchangeSymbol) this.unblockExchangeSymbolRepository.getOne(symbol);
    }

    public Page<UnblockExchangeSymbol> findAll(final Predicate predicate, final Pageable pageable) {
        return (Page<UnblockExchangeSymbol>) this.unblockExchangeSymbolRepository.findAll(predicate, pageable);
    }

    public int insert(final UnblockExchangeSymbol unblockExchangeSymbol) {
        final Date date = new Date();
        return this.unblockExchangeSymbolRepository.insert(unblockExchangeSymbol.getSymbol(), unblockExchangeSymbol.getSingleRoundReleaseRate(), unblockExchangeSymbol.getDayMaxReleaseRate(), unblockExchangeSymbol.getDayMaxReleaseAmount(), unblockExchangeSymbol.getStartPrice(), unblockExchangeSymbol.getPriceRiseRule(), unblockExchangeSymbol.getRoundIntervalPrice(), unblockExchangeSymbol.getSingleDayRoundMax(), unblockExchangeSymbol.getRoundLimitKey(), unblockExchangeSymbol.getRoundLimitValue(), unblockExchangeSymbol.getPriceLimit(), unblockExchangeSymbol.getWhitelistMemberId(), date, date, 0L);
    }

    public int update(final UnblockExchangeSymbol unblockExchangeSymbol) {
        final Date date = new Date();
        return this.unblockExchangeSymbolRepository.update(unblockExchangeSymbol.getSymbol(), unblockExchangeSymbol.getSingleRoundReleaseRate(), unblockExchangeSymbol.getDayMaxReleaseRate(), unblockExchangeSymbol.getDayMaxReleaseAmount(), unblockExchangeSymbol.getStartPrice(), unblockExchangeSymbol.getPriceRiseRule(), unblockExchangeSymbol.getRoundIntervalPrice(), unblockExchangeSymbol.getSingleDayRoundMax(), unblockExchangeSymbol.getRoundLimitKey(), unblockExchangeSymbol.getRoundLimitValue(), unblockExchangeSymbol.getPriceLimit(), unblockExchangeSymbol.getWhitelistMemberId(), date, unblockExchangeSymbol.getVersion());
    }

    public int deleteBySymbol(final String symbol) {
        return this.unblockExchangeSymbolRepository.deleteBySymbol(symbol);
    }

    public List<UnblockExchangeSymbol> findAll() {
        return (List<UnblockExchangeSymbol>) this.unblockExchangeSymbolRepository.findAll();
    }
}
