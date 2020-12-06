package cn.ztuo.bitrade.util;

import org.springframework.core.convert.converter.*;

import java.math.*;

import org.bson.types.*;
import org.springframework.data.convert.*;

@ReadingConverter
@WritingConverter
public class BigDecimalToDecimal128Converter implements Converter<BigDecimal, Decimal128> {
    public Decimal128 convert(final BigDecimal bigDecimal) {
        return new Decimal128(bigDecimal);
    }
}
