package cn.ztuo.bitrade.exchanges.huobi.common.util;

import java.io.*;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.*;

public class JsonUtil {
    static final ObjectMapper objectMapper;

    public static String writeValue(final Object obj) throws IOException {
        return JsonUtil.objectMapper.writeValueAsString(obj);
    }

    public static <T> T readValue(final String s, final TypeReference<T> ref) throws IOException {
        return (T) JsonUtil.objectMapper.readValue(s, (TypeReference) ref);
    }

    static ObjectMapper createObjectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.KEBAB_CASE);
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    static {
        objectMapper = createObjectMapper();
    }
}
