package cn.ztuo.bitrade.controller;

import cn.ztuo.bitrade.service.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.transform.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/member"})
public class ContractMemberController extends BaseController {
    @Autowired
    private ContractExchangeService contractExchangeService;

    @PostMapping({"/getMemberAccountRights"})
    public MessageResult getMemberAccountRights(@SessionAttribute("API_MEMBER") final AuthMember authMember, @RequestParam(value = "contractType", required = false) final int contractType) {
        return this.contractExchangeService.getMemberAccountRights(authMember.getId(), false, contractType);
    }
}
