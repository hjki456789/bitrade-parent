package cn.ztuo.bitrade.util;

import org.springframework.core.convert.converter.*;
import org.bson.types.*;
import java.math.*;
import org.springframework.data.convert.*;

@ReadingConverter
@WritingConverter
public class Decimal128ToBigDecimalConverter implements Converter<Decimal128, BigDecimal>
{
    public BigDecimal convert(final Decimal128 decimal128) {
        return decimal128.bigDecimalValue();
    }
}
