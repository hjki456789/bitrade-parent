package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.*;
import cn.ztuo.bitrade.entity.*;

@Service
public class ContractCoinApiService {
    @Autowired
    private ContractCoinService contractCoinService;
    @Autowired
    private LocaleMessageSourceService msService;

    public MessageResult getCoinBaseInfo(final String symbol) {
        final ContractCoin contractCoin = this.contractCoinService.findBySymbol(symbol);
        if (null == contractCoin || StringUtils.isEmpty((CharSequence) contractCoin.getSymbol())) {
            return MessageResult.error(500, this.msService.getMessage("CONTRACT_SYMBOL_NOT_EXIST"));
        }
        return MessageResult.success("成功获取合约交易对信息！", contractCoin);
    }
}
