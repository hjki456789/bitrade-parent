package cn.ztuo.bitrade.exchanges.okex.bean.other;

public class SpotOrderBookItem implements OrderBookItem<String> {
    private final String price;
    private final String size;
    private final String numOrder;

    public SpotOrderBookItem(final String price, final String size, final String numOrder) {
        this.price = price;
        this.size = size;
        this.numOrder = numOrder;
    }

    @Override
    public String toString() {
        return "[\"" + this.price.toString() + "\",\"" + this.size + "\",\"" + this.numOrder + "\"]";
    }

    @Override
    public String getPrice() {
        return this.price;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    public String getNumOrder() {
        return this.numOrder;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SpotOrderBookItem)) {
            return false;
        }
        final SpotOrderBookItem other = (SpotOrderBookItem) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        Label_0065:
        {
            if (this$price == null) {
                if (other$price == null) {
                    break Label_0065;
                }
            } else if (this$price.equals(other$price)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$size = this.getSize();
        final Object other$size = other.getSize();
        Label_0102:
        {
            if (this$size == null) {
                if (other$size == null) {
                    break Label_0102;
                }
            } else if (this$size.equals(other$size)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$numOrder = this.getNumOrder();
        final Object other$numOrder = other.getNumOrder();
        if (this$numOrder == null) {
            if (other$numOrder == null) {
                return true;
            }
        } else if (this$numOrder.equals(other$numOrder)) {
            return true;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SpotOrderBookItem;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $price = this.getPrice();
        result = result * 59 + (($price == null) ? 43 : $price.hashCode());
        final Object $size = this.getSize();
        result = result * 59 + (($size == null) ? 43 : $size.hashCode());
        final Object $numOrder = this.getNumOrder();
        result = result * 59 + (($numOrder == null) ? 43 : $numOrder.hashCode());
        return result;
    }
}
