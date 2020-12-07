package cn.ztuo.bitrade.entity;

import lombok.Data;

@Data
public class WalletData {
    private ContractWallet contractWallet;
    private ContractDoubleDirectionWallet contractDoubleDirectionWallet;
}
