package cn.ztuo.bitrade.enums;

import cn.ztuo.bitrade.core.*;
import com.fasterxml.jackson.annotation.*;

import java.beans.*;

public enum WholeNetInsuranceFundWalletRecordType implements BaseEnum {
    FIRST_PRIZE_RETURN("\u4e00\u7b49\u5956\u8fd4\u8fd8");

    private String cnName;

    @JsonValue
    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    public WholeNetInsuranceFundWalletRecordType[] getAll() {
        return values();
    }

    public static WholeNetInsuranceFundWalletRecordType getByValue(final int value) {
        for (final WholeNetInsuranceFundWalletRecordType credentialsType : values()) {
            if (credentialsType.getOrdinal() == value) {
                return credentialsType;
            }
        }
        return null;
    }

    @ConstructorProperties({"cnName"})
    private WholeNetInsuranceFundWalletRecordType(final String cnName) {
        this.cnName = cnName;
    }

    public String getCnName() {
        return this.cnName;
    }

    public void setCnName(final String cnName) {
        this.cnName = cnName;
    }
}
