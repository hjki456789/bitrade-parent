package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.transform.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/wallet"})
public class ContractMemberWalletController extends BaseController {
    @Autowired
    private ContractMemberWalletService contractMemberWalletService;

    @PostMapping({"/getMemberWalletBalance"})
    public MessageResult getMemberWallet(@SessionAttribute("API_MEMBER") final AuthMember authMember) {
        return this.contractMemberWalletService.getMemberWalletBalance(authMember.getId());
    }
}
