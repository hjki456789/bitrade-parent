package cn.ztuo.bitrade.controller.contractstrategy;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.contractstrategy.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import cn.ztuo.bitrade.entity.contractstrategy.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({ "contract-strategy-common" })
public class ContractStrategyCommonConfigManageController extends BaseAdminController
{
    @Autowired
    private ContractStrategyExchangeConfigService contractStrategyExchangeConfigService;
    @Autowired
    private ContractCoinService contractCoinService;

    @PostMapping({ "exchange-config/list" })
    public MessageResult exchangeConfigList() {
        final List<ContractStrategyExchangeConfig> configList = (List<ContractStrategyExchangeConfig>)this.contractStrategyExchangeConfigService.findAll();
        if (configList == null) {
            return MessageResult.error("配置信息不存在!");
        }
        return this.success((Object)configList);
    }

    @PostMapping({ "symbol-config/list" })
    public MessageResult symbolConfigList() {
        final List<String> configList = (List<String>)this.contractCoinService.findAllSymbol();
        if (configList == null) {
            return MessageResult.error("配置信息不存在!");
        }
        return this.success((Object)configList);
    }
}
