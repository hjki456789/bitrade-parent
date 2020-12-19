package cn.ztuo.bitrade.exchanges.okex.bean.other;

import com.google.common.hash.*;

import java.util.*;
import java.nio.charset.*;

public class OrderBookChecksumer {
    private final HashFunction crc32;

    public OrderBookChecksumer() {
        this.crc32 = Hashing.crc32();
    }

    public <T extends OrderBookItem> int checksum(final List<T> asks, final List<T> bids) {
        final StringBuilder s = new StringBuilder();
        for (int i = 0; i < 25; ++i) {
            if (i < bids.size()) {
                s.append(bids.get(i).getPrice().toString());
                s.append(":");
                s.append(bids.get(i).getSize());
                s.append(":");
            }
            if (i < asks.size()) {
                s.append(asks.get(i).getPrice().toString());
                s.append(":");
                s.append(asks.get(i).getSize());
                s.append(":");
            }
        }
        String str;
        if (s.length() > 0) {
            str = s.substring(0, s.length() - 1);
        } else {
            str = "";
        }
        return this.crc32.hashString((CharSequence) str, StandardCharsets.UTF_8).asInt();
    }
}
