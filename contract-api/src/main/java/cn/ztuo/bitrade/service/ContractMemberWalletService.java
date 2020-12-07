package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;

import java.math.*;

import cn.ztuo.bitrade.entity.*;

@Service
public class ContractMemberWalletService {
    @Autowired
    private ContractWalletService contractWalletService;
    @Autowired
    private CoinService coinService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ContractDoubleDirectionWalletService contractDoubleDirectionWalletService;

    public MessageResult getMemberWalletBalance(final long memberId) {
        ContractWallet contractWallet = this.contractWalletService.findByMemberId(memberId);
        if (null == contractWallet) {
            contractWallet = new ContractWallet();
            final Coin coin = this.coinService.findOne("USDT");
            contractWallet.setCoin(coin);
            final Member member = this.memberService.findOne(Long.valueOf(memberId));
            contractWallet.setMember(member);
            contractWallet = this.contractWalletService.save(contractWallet);
        }
        contractWallet.setBalance(contractWallet.getBalance().setScale(4, RoundingMode.DOWN));
        contractWallet.setFrozenBalance(contractWallet.getFrozenBalance().setScale(4, RoundingMode.DOWN));
        ContractDoubleDirectionWallet contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.getContractDoubleDirectionWalletByMemberIdAndCoin(Long.valueOf(memberId), "USDT");
        if (contractDoubleDirectionWallet == null) {
            contractDoubleDirectionWallet = new ContractDoubleDirectionWallet();
            contractDoubleDirectionWallet.setBalance(BigDecimal.ZERO);
            contractDoubleDirectionWallet.setMemberId(Long.valueOf(memberId));
            final Coin coin2 = this.coinService.findOne("USDT");
            contractDoubleDirectionWallet.setCoin(coin2);
            contractDoubleDirectionWallet = this.contractDoubleDirectionWalletService.save(contractDoubleDirectionWallet);
        }
        contractDoubleDirectionWallet.setBalance(contractDoubleDirectionWallet.getBalance().setScale(4, RoundingMode.DOWN));
        contractDoubleDirectionWallet.setFrozenBalance(contractDoubleDirectionWallet.getFrozenBalance().setScale(4, RoundingMode.DOWN));
        final WalletData walletData = new WalletData();
        walletData.setContractDoubleDirectionWallet(contractDoubleDirectionWallet);
        walletData.setContractWallet(contractWallet);
        return MessageResult.success("\u6210\u529f\u83b7\u53d6\u5408\u7ea6\u8d26\u6237\u4f59\u989d\u4fe1\u606f", walletData);
    }
}
