package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({"/coin"})
public class ContractCoinController extends BaseController {
    @Autowired
    private ContractCoinApiService contractCoinApiService;

    @RequestMapping({"/baseInfo"})
    public MessageResult getCoinBaseInfo(@RequestParam(value = "symbol", required = true) final String symbol) {
        final MessageResult mr = this.contractCoinApiService.getCoinBaseInfo(symbol);
        return mr;
    }
}
