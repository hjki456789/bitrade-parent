package cn.ztuo.bitrade.controller;

import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import cn.ztuo.bitrade.entity.transform.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import cn.ztuo.bitrade.util.*;
import cn.ztuo.bitrade.constant.*;
import java.math.*;
import org.springframework.transaction.annotation.*;
import cn.ztuo.bitrade.entity.*;
import org.slf4j.*;

@RestController
@RequestMapping(method = { RequestMethod.POST })
public class VoteController
{
    private static final Logger log;
    @Autowired
    private VoteService voteService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private PreCoinService preCoinService;
    @Autowired
    private MemberWalletService memberWalletService;
    @Autowired
    private MemberTransactionService memberTransactionService;
    @Autowired
    private VoteDetailService voteDetailService;

    @RequestMapping(value = { "/vote" }, method = { RequestMethod.POST })
    @Transactional(rollbackFor = { Exception.class })
    public MessageResult vote(final long preCoinId, final int amount, @SessionAttribute("API_MEMBER") final AuthMember user) {
        final PreCoin preCoin = this.preCoinService.findById(preCoinId);
        if (preCoin.getVote().getStatus().equals((Object)BooleanEnum.IS_FALSE)) {
            return MessageResult.error("This vote has been closed");
        }
        final Coin coin = this.coinService.queryPlatformCoin();
        if (coin == null) {
            return MessageResult.error("system not set platform coin");
        }
        Assert.isTrue(amount > 0, "The number of votes must be greater than 0");
        final MemberWallet memberWallet = this.memberWalletService.findByCoinAndMemberId(coin, Long.valueOf(user.getId()));
        Assert.isTrue(memberWallet != null, "wallet is null");
        final BigDecimal consume = BigDecimalUtils.mul(preCoin.getVote().getAmount(), (double)amount);
        Assert.isTrue(memberWallet.getBalance().compareTo(consume) >= 0, "Insufficient closeBalance");
        final int voted = this.voteDetailService.queryVoted(user.getId(), preCoin.getVote());
        Assert.isTrue(preCoin.getVote().getVoteLimit() - voted >= amount, "You can vote up to " + (preCoin.getVote().getVoteLimit() - voted) + " votes");
        if (this.memberWalletService.deductBalance(memberWallet, consume) > 0) {
            preCoin.setAmount(preCoin.getAmount() + amount);
            this.preCoinService.save(preCoin);
            final MemberTransaction memberTransaction = new MemberTransaction();
            memberTransaction.setMemberId(Long.valueOf(user.getId()));
            memberTransaction.setType(TransactionType.VOTE);
            memberTransaction.setSymbol(memberWallet.getCoin().getUnit());
            memberTransaction.setAmount(consume.multiply(new BigDecimal(-1)));
            this.memberTransactionService.save(memberTransaction);
            final VoteDetail voteDetail = new VoteDetail();
            voteDetail.setVoteAmount(amount);
            voteDetail.setPreCoin(preCoin);
            voteDetail.setVote(preCoin.getVote());
            voteDetail.setUserId(user.getId());
            voteDetail.setAmount(consume);
            this.voteDetailService.save(voteDetail);
            return MessageResult.success();
        }
        return MessageResult.error("Insufficient closeBalance");
    }

    @RequestMapping(value = { "/vote/info" }, method = { RequestMethod.POST })
    public MessageResult voteInfo() {
        final MessageResult result = MessageResult.success();
        final Vote vote = this.voteService.findVote();
        result.setData((Object)vote);
        return result;
    }

    static {
        log = LoggerFactory.getLogger((Class)VoteController.class);
    }
}
