package cn.ztuo.bitrade.exchanges.okex.bean.futures.result;

public class Currencies {
    private String id;
    private String name;
    private String min_size;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMin_size() {
        return this.min_size;
    }

    public void setMin_size(final String min_size) {
        this.min_size = min_size;
    }
}
