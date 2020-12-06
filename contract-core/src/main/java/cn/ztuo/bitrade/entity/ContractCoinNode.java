package cn.ztuo.bitrade.entity;

import java.io.*;
import javax.persistence.*;

@Entity
@Table
public class ContractCoinNode implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;
    @JoinColumn(name = "coin_id")
    @ManyToOne
    private ContractCoin contractCoin;
    @JoinColumn(name = "node_id")
    @ManyToOne
    private ContractNode contractNode;
    private Integer enable;
    private Long sequence;

    public String getId() {
        return this.id;
    }

    public ContractCoin getContractCoin() {
        return this.contractCoin;
    }

    public ContractNode getContractNode() {
        return this.contractNode;
    }

    public Integer getEnable() {
        return this.enable;
    }

    public Long getSequence() {
        return this.sequence;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setContractCoin(final ContractCoin contractCoin) {
        this.contractCoin = contractCoin;
    }

    public void setContractNode(final ContractNode contractNode) {
        this.contractNode = contractNode;
    }

    public void setEnable(final Integer enable) {
        this.enable = enable;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ContractCoinNode)) {
            return false;
        }
        final ContractCoinNode other = (ContractCoinNode) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065:
        {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            } else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$contractCoin = this.getContractCoin();
        final Object other$contractCoin = other.getContractCoin();
        Label_0102:
        {
            if (this$contractCoin == null) {
                if (other$contractCoin == null) {
                    break Label_0102;
                }
            } else if (this$contractCoin.equals(other$contractCoin)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$contractNode = this.getContractNode();
        final Object other$contractNode = other.getContractNode();
        Label_0139:
        {
            if (this$contractNode == null) {
                if (other$contractNode == null) {
                    break Label_0139;
                }
            } else if (this$contractNode.equals(other$contractNode)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$enable = this.getEnable();
        final Object other$enable = other.getEnable();
        Label_0176:
        {
            if (this$enable == null) {
                if (other$enable == null) {
                    break Label_0176;
                }
            } else if (this$enable.equals(other$enable)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$sequence = this.getSequence();
        final Object other$sequence = other.getSequence();
        if (this$sequence == null) {
            if (other$sequence == null) {
                return true;
            }
        } else if (this$sequence.equals(other$sequence)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ContractCoinNode;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $contractCoin = this.getContractCoin();
        result = result * 59 + (($contractCoin == null) ? 43 : $contractCoin.hashCode());
        final Object $contractNode = this.getContractNode();
        result = result * 59 + (($contractNode == null) ? 43 : $contractNode.hashCode());
        final Object $enable = this.getEnable();
        result = result * 59 + (($enable == null) ? 43 : $enable.hashCode());
        final Object $sequence = this.getSequence();
        result = result * 59 + (($sequence == null) ? 43 : $sequence.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ContractCoinNode(id=" + this.getId() + ", contractCoin=" + this.getContractCoin() + ", contractNode=" + this.getContractNode() + ", enable=" + this.getEnable() + ", sequence=" + this.getSequence() + ")";
    }
}
