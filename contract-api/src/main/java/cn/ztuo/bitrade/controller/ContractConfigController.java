package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.contractdouble.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.util.*;

import java.util.*;

import cn.ztuo.bitrade.entity.contractdouble.*;

@RestController
@RequestMapping({"/config"})
public class ContractConfigController extends BaseController {
    @Autowired
    private ContractConfigApiService contractConfigApiService;
    @Autowired
    private ContractDoubleExchangeConfigService contractDoubleExchangeConfigService;

    @RequestMapping({"/baseConfig"})
    public MessageResult getBaseConfig(@RequestParam(value = "configKey", required = false) final String configKey) {
        final MessageResult mr = this.contractConfigApiService.getBaseConfig(configKey);
        return mr;
    }

    @RequestMapping({"/exchangeList"})
    public MessageResult getExchangeList() {
        final List<ContractDoubleExchangeConfig> list = this.contractDoubleExchangeConfigService.findAll();
        return MessageResult.success("操作成功", list);
    }
}
