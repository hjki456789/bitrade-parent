package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;
import java.util.*;
import cn.ztuo.bitrade.entity.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;

@RestController
@RequestMapping({ "contract/contract-coin-info" })
public class ContractCoinInfoController extends BaseAdminController
{
    @Autowired
    private ContractCoinInfoService contractCoinInfoService;

    @RequiresPermissions({ "contract:contract-coin-info:normalCoinList" })
    @PostMapping({ "normalCoinList" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "合约币种列表")
    public MessageResult normalCoinList() {
        final List<ContractCoinInfo> list = (List<ContractCoinInfo>)this.contractCoinInfoService.getNormalCoin();
        return this.success((Object)list);
    }
}
