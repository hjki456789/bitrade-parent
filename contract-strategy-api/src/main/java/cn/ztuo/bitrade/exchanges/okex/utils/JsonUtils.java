package cn.ztuo.bitrade.exchanges.okex.utils;

import cn.ztuo.bitrade.exchanges.okex.constant.*;
import cn.ztuo.bitrade.exchanges.okex.exception.*;

import java.lang.reflect.*;

import com.alibaba.fastjson.*;
import org.apache.commons.collections.*;

import java.util.*;

public class JsonUtils {
    public static <T> JSONObject convertObject(final T t, final Class<T> tC) {
        if (t == null) {
            return APIConstants.NOTHING;
        }
        final Field[] fields = tC.getDeclaredFields();
        final JSONObject object = new JSONObject();
        try {
            for (final Field field : fields) {
                final String name = field.getName();
                final String type = field.getType().toString();
                final String methodName = getMethodName(type, name);
                final Method[] methods2;
                final Method[] methods = methods2 = tC.getMethods();
                for (final Method method : methods2) {
                    if (methodName.equals(method.getName())) {
                        final Object result = method.invoke(t, new Object[0]);
                        if (APIConstants.toStringTypeArray.contains(type)) {
                            object.put(field.getName(), (Object) toString(result));
                        } else if (APIConstants.toStringTypeDoubleArray.contains(type) && result != null) {
                            object.put(field.getName(), (Object) NumberUtils.doubleToString((Double) result));
                        } else {
                            object.put(field.getName(), result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new APIException("Java biz bean change JSONObject is exception.", e);
        }
        return object;
    }

    public static final <T> JSONArray convertList(final List<T> list, final Class<T> tC) {
        final JSONArray array = new JSONArray();
        if (CollectionUtils.isEmpty((Collection) list)) {
            return array;
        }
        for (final T t : list) {
            array.add((Object) convertObject(t, tC));
        }
        return array;
    }

    public static final String getMethodName(final String type, final String field) {
        final StringBuilder methodName = new StringBuilder();
        if (type.equals("boolean")) {
            methodName.append("is");
        } else {
            methodName.append("get");
        }
        return methodName.append(startUpperCase(field)).toString();
    }

    public static final String startUpperCase(final String name) {
        final char[] cs = name.toCharArray();
        if (cs[0] >= 'a' && cs[0] <= 'z') {
            final char[] array = cs;
            final int n = 0;
            array[n] -= ' ';
        }
        return String.valueOf(cs);
    }

    public static final String toString(final Object object) {
        return (object == null) ? "0" : object.toString();
    }
}
