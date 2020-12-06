package cn.ztuo.bitrade.controller.system;

import cn.ztuo.bitrade.controller.common.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.util.*;
import org.springframework.util.*;

import java.util.*;

import cn.ztuo.bitrade.entity.*;
import org.apache.shiro.authz.annotation.*;
import org.springframework.web.bind.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.constant.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;

@RestController
@RequestMapping({"system/vote"})
public class AdminVoteController extends BaseAdminController {
    @Autowired
    private VoteService voteService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private PreCoinService preCoinService;
    @Autowired
    private LocaleMessageSourceService messageSource;

    @RequiresPermissions({"system:vote:merge"})
    @PostMapping({"merge"})
    @AccessLog(module = AdminModule.SYSTEM, operation = "新增投票")
    @Transactional(rollbackFor = {Exception.class})
    public MessageResult merge(@RequestBody Vote vote) {
        Assert.notNull((Object) vote, "vote null");
        for (final PreCoin preCoin : vote.getPreCoins()) {
            final Coin coin = this.coinService.findByUnit(preCoin.getUnit());
            if (coin != null) {
                return this.error(this.messageSource.getMessage("PRE_COIN_EXIST"));
            }
        }
        if (vote.getId() != null) {
            final Vote find = this.voteService.findVote();
            Assert.isTrue(find.getId() == vote.getId(), this.messageSource.getMessage("MODIFY_LATEST"));
            for (final PreCoin preCoin2 : vote.getPreCoins()) {
                if (preCoin2.getId() != null) {
                    final PreCoin preCoin3 = this.preCoinService.findById(preCoin2.getId());
                    preCoin2.setVersion(preCoin3.getVersion());
                }
                preCoin2.setVote(vote);
            }
        } else {
            this.voteService.turnOffAllVote();
        }
        vote = this.voteService.save(vote);
        return MessageResult.getSuccessInstance(this.messageSource.getMessage("SUCCESS"), (Object) vote);
    }

    @RequiresPermissions({"system:vote:detail"})
    @PostMapping({"detail"})
    @AccessLog(module = AdminModule.SYSTEM, operation = "投票详情")
    public MessageResult detail(final Long id) {
        final Vote vote = this.voteService.findById(id);
        return MessageResult.getSuccessInstance(this.messageSource.getMessage("SUCCESS"), (Object) vote);
    }

    @RequiresPermissions({"system:vote:page-query"})
    @PostMapping({"page-query"})
    public MessageResult pageQuery(final PageModel pageModel) {
        final Page<Vote> all = (Page<Vote>) this.voteService.findAll((Predicate) null, pageModel.getPageable());
        return this.success((Object) all);
    }
}
