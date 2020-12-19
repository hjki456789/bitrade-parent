package cn.ztuo.bitrade.exchanges.okex.bean.other;

import java.util.function.*;
import java.util.*;

public class OrderBookDiffer {
    public <T extends OrderBookItem> List<T> diff(final List<T> current, final List<T> snapshot, final Comparator<String> comparator, final int order) {
        final Iterator<T> snapshotIter = snapshot.iterator();
        final Iterator<T> currentIter = current.iterator();
        final List<T> diff = new ArrayList<T>(current.size());
        if (snapshotIter.hasNext() && currentIter.hasNext()) {
            T snapshotBookItem = snapshotIter.next();
            T currentBookItem = currentIter.next();
            while (true) {
                final double currentPrc = Double.parseDouble(currentBookItem.getPrice());
                final double snapPrc = Double.parseDouble(snapshotBookItem.getPrice());
                int compare = 0;
                if ((order == 1 && snapPrc > currentPrc) || (order == 2 && snapPrc < currentPrc)) {
                    compare = 1;
                } else if ((order == 1 && snapPrc < currentPrc) || (order == 2 && snapPrc > currentPrc)) {
                    compare = -1;
                }
                if (compare == 0) {
                    if (!snapshotBookItem.equals(currentBookItem) && !"0".equals(snapshotBookItem.getSize().toString())) {
                        diff.add(snapshotBookItem);
                    }
                    if (!currentIter.hasNext() || !snapshotIter.hasNext()) {
                        break;
                    }
                    currentBookItem = currentIter.next();
                    snapshotBookItem = snapshotIter.next();
                } else if (compare < 0) {
                    if (!"0".equals(snapshotBookItem.getSize().toString())) {
                        diff.add(snapshotBookItem);
                    }
                    if (!snapshotIter.hasNext()) {
                        diff.add(currentBookItem);
                        break;
                    }
                    snapshotBookItem = snapshotIter.next();
                } else {
                    diff.add(currentBookItem);
                    if (currentIter.hasNext()) {
                        currentBookItem = currentIter.next();
                    } else {
                        if (!"0".equals(snapshotBookItem.getSize().toString())) {
                            diff.add(snapshotBookItem);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        if (!snapshotIter.hasNext()) {
            currentIter.forEachRemaining(diff::add);
        }
        if (!currentIter.hasNext()) {
            snapshotIter.forEachRemaining(diff::add);
        }
        return Collections.unmodifiableList((List<? extends T>) diff);
    }
}
