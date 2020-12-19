package cn.ztuo.bitrade.exchanges.okex.bean.other;

import com.google.common.hash.*;

import java.util.*;

public class SpotOrderBook {
    private String contract;
    private List<SpotOrderBookItem> asks;
    private List<SpotOrderBookItem> bids;
    private String timestamp;
    private int checksum;
    private OrderBookDiffer differ;
    private OrderBookChecksumer checksumer;
    private HashFunction crc32;

    public SpotOrderBook(final String contract, final List<SpotOrderBookItem> asks, final List<SpotOrderBookItem> bids, final String timestamp, final int checksum) {
        this.differ = new OrderBookDiffer();
        this.checksumer = new OrderBookChecksumer();
        this.crc32 = Hashing.crc32();
        this.contract = contract;
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
        this.checksum = checksum;
    }

    public boolean check() {
        if (this.bids == null || this.asks == null) {
            return false;
        }
        if (this.bids.size() > 1) {
            final SpotOrderBookItem p = this.bids.get(0);
            for (int i = 1; i < this.bids.size(); ++i) {
                if (this.bids.get(i).getPrice().compareTo(p.getPrice()) > 0) {
                    return false;
                }
            }
        }
        if (this.asks.size() > 1) {
            final SpotOrderBookItem p = this.asks.get(0);
            for (int i = 1; i < this.asks.size(); ++i) {
                if (this.asks.get(i).getPrice().compareTo(p.getPrice()) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public SpotOrderBookDiff diff(final SpotOrderBook that) {
        System.out.println("\u5168\u91cf\u6570\u636e\uff1a" + this.toString());
        System.out.println(that.timestamp + "  \u589e\u91cf\u6570\u636e\uff1a" + that.toString());
        final List<SpotOrderBookItem> askDiff = this.diff(this.getAsks(), that.getAsks(), Comparator.naturalOrder(), 1);
        final List<SpotOrderBookItem> bidDiff = this.diff(this.getBids(), that.getBids(), Comparator.reverseOrder(), 2);
        return new SpotOrderBookDiff(this.contract, (List) askDiff, (List) bidDiff, that.timestamp, that.checksum);
    }

    private List<SpotOrderBookItem> diff(final List<SpotOrderBookItem> current, final List<SpotOrderBookItem> snapshot, final Comparator<String> comparator, final int order) {
        return this.differ.diff(current, snapshot, comparator, order);
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

    public String getContract() {
        return this.contract;
    }

    public void setContract(final String contract) {
        this.contract = contract;
    }

    public List<SpotOrderBookItem> getAsks() {
        return this.asks;
    }

    public void setAsks(final List<SpotOrderBookItem> asks) {
        this.asks = asks;
    }

    public List<SpotOrderBookItem> getBids() {
        return this.bids;
    }

    public void setBids(final List<SpotOrderBookItem> bids) {
        this.bids = bids;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    public int getChecksum() {
        return this.checksum;
    }

    public void setChecksum(final int checksum) {
        this.checksum = checksum;
    }

    public OrderBookDiffer getDiffer() {
        return this.differ;
    }

    public void setDiffer(final OrderBookDiffer differ) {
        this.differ = differ;
    }

    public OrderBookChecksumer getChecksumer() {
        return this.checksumer;
    }

    public void setChecksumer(final OrderBookChecksumer checksumer) {
        this.checksumer = checksumer;
    }

    public HashFunction getCrc32() {
        return this.crc32;
    }

    public void setCrc32(final HashFunction crc32) {
        this.crc32 = crc32;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SpotOrderBook)) {
            return false;
        }
        final SpotOrderBook other = (SpotOrderBook) o;
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
        Label_0176:
        {
            if (this$timestamp == null) {
                if (other$timestamp == null) {
                    break Label_0176;
                }
            } else if (this$timestamp.equals(other$timestamp)) {
                break Label_0176;
            }
            return false;
        }
        if (this.getChecksum() != other.getChecksum()) {
            return false;
        }
        final Object this$differ = this.getDiffer();
        final Object other$differ = other.getDiffer();
        Label_0226:
        {
            if (this$differ == null) {
                if (other$differ == null) {
                    break Label_0226;
                }
            } else if (this$differ.equals(other$differ)) {
                break Label_0226;
            }
            return false;
        }
        final Object this$checksumer = this.getChecksumer();
        final Object other$checksumer = other.getChecksumer();
        Label_0263:
        {
            if (this$checksumer == null) {
                if (other$checksumer == null) {
                    break Label_0263;
                }
            } else if (this$checksumer.equals(other$checksumer)) {
                break Label_0263;
            }
            return false;
        }
        final Object this$crc32 = this.getCrc32();
        final Object other$crc32 = other.getCrc32();
        if (this$crc32 == null) {
            if (other$crc32 == null) {
                return true;
            }
        } else if (this$crc32.equals(other$crc32)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SpotOrderBook;
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
        final Object $differ = this.getDiffer();
        result = result * 59 + (($differ == null) ? 43 : $differ.hashCode());
        final Object $checksumer = this.getChecksumer();
        result = result * 59 + (($checksumer == null) ? 43 : $checksumer.hashCode());
        final Object $crc32 = this.getCrc32();
        result = result * 59 + (($crc32 == null) ? 43 : $crc32.hashCode());
        return result;
    }
}
