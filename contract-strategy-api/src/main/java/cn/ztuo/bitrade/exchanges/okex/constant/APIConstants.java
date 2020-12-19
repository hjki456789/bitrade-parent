package cn.ztuo.bitrade.exchanges.okex.constant;

import okhttp3.*;

import java.nio.charset.*;

import com.alibaba.fastjson.*;
import cn.ztuo.bitrade.exchanges.okex.enums.*;

import java.util.*;

public class APIConstants {
    public static final long TIMEOUT = 30000L;
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String ACCEPT = "Accept";
    public static final String COOKIE = "Cookie";
    public static final String LOCALE = "locale=";
    public static final MediaType JSON;
    public static final Charset UTF_8;
    public static final String QUESTION = "?";
    public static final String EMPTY = "";
    public static final JSONObject NOTHING;
    public static final List<String> toStringTypeArray;
    public static final List<String> toStringTypeDoubleArray;
    public static final List<Integer> resultStatusArray;
    public static final String BOOLEAN = "boolean";
    public static final String IS = "is";
    public static final String get = "get";
    public static final char a = 'a';
    public static final char z = 'z';
    public static final String ZERO_STRING = "0";
    public static final String DOUBLE_ZERO_STRING = "0.00";
    public static final String DOT1 = ".";
    public static final String DOT2 = "\\.";
    public static final String E = "E";
    public static final String e = "e";
    public static final int DEFAULT_SCALE = 2;
    public static final String DOUBLE_END1 = "0+?$";
    public static final String DOUBLE_END2 = "[.]$";
    public static final int ONE = 1;
    public static final int HUNDRED = 100;
    public static final String HLINE = "-";
    public static final String SLASH = "/";

    static {
        JSON = MediaType.parse(ContentTypeEnum.APPLICATION_JSON.contentType());
        UTF_8 = Charset.forName(CharsetEnum.UTF_8.charset());
        NOTHING = new JSONObject();
        toStringTypeArray = Arrays.asList("class java.lang.Long", "class java.lang.Integer", "long", "int");
        toStringTypeDoubleArray = Arrays.asList("class java.lang.Double", "double");
        resultStatusArray = Arrays.asList(400, 401, 429, 500);
    }
}
