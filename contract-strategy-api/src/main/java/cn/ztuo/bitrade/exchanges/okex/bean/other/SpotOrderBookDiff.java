package cn.ztuo.bitrade.exchanges.okex.bean.other;

import java.util.*;

public class SpotOrderBookDiff {
    private final String contract;
    private final List<SpotOrderBookItem> asks;
    private final List<SpotOrderBookItem> bids;
    private final String timestamp;
    private final int checksum;

    public SpotOrderBookDiff(final String contract, final List<SpotOrderBookItem> asks, final List<SpotOrderBookItem> bids, final String timestamp, final int checksum) {
        this.contract = contract;
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
        this.checksum = checksum;
    }

    public boolean isEmpty() {
        return this.bids.isEmpty() && this.asks.isEmpty();
    }

    public String getContract() {
        return this.contract;
    }

    public List<SpotOrderBookItem> getAsks() {
        return this.asks;
    }

    public List<SpotOrderBookItem> getBids() {
        return this.bids;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public int getChecksum() {
        return this.checksum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"instrument_id\":\"");
        sb.append(this.contract);
        sb.append("\",\"asks\":[");
        for (int i = 0; i < this.asks.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(this.asks.get(i).toString());
        }
        sb.append("],\"bids\":[");
        for (int i = 0; i < this.bids.size(); ++i) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(this.bids.get(i).toString());
        }
        sb.append("],\"timestamp\":\"");
        sb.append(this.timestamp);
        sb.append("\",\"checksum\":");
        sb.append(this.checksum);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SpotOrderBookDiff)) {
            return false;
        }
        final SpotOrderBookDiff other = (SpotOrderBookDiff) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$contract = this.getContract();
        final Object other$contract = other.getContract();
        Label_0065:
        {
            if (this$contract == null) {
                if (other$contract == null) {
                    break Label_0065;
                }
            } else if (this$contract.equals(other$contract)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$asks = this.getAsks();
        final Object other$asks = other.getAsks();
        Label_0102:
        {
            if (this$asks == null) {
                if (other$asks == null) {
                    break Label_0102;
                }
            } else if (this$asks.equals(other$asks)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$bids = this.getBids();
        final Object other$bids = other.getBids();
        Label_0139:
        {
            if (this$bids == null) {
                if (other$bids == null) {
                    break Label_0139;
                }
            } else if (this$bids.equals(other$bids)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$timestamp = this.getTimestamp();
        final Object other$timestamp = other.getTimestamp();
        if (this$timestamp == null) {
            if (other$timestamp == null) {
                return this.getChecksum() == other.getChecksum();
            }
        } else if (this$timestamp.equals(other$timestamp)) {
            return this.getChecksum() == other.getChecksum();
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SpotOrderBookDiff;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $contract = this.getContract();
        result = result * 59 + (($contract == null) ? 43 : $contract.hashCode());
        final Object $asks = this.getAsks();
        result = result * 59 + (($asks == null) ? 43 : $asks.hashCode());
        final Object $bids = this.getBids();
        result = result * 59 + (($bids == null) ? 43 : $bids.hashCode());
        final Object $timestamp = this.getTimestamp();
        result = result * 59 + (($timestamp == null) ? 43 : $timestamp.hashCode());
        result = result * 59 + this.getChecksum();
        return result;
    }
}
