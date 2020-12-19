package cn.ztuo.bitrade.utils;

import org.springframework.stereotype.*;
import com.google.gson.*;
import org.slf4j.*;
import cn.ztuo.bitrade.entity.*;
import com.alibaba.fastjson.*;
import org.web3j.utils.Convert;

import java.math.*;
import java.util.*;

@Component
public class Erc20Component
{
    private static final String API_URL = "http://api-cn.etherscan.com/api";
    private static final String API_KEY = "K39YBUV1FK2XJGMNKN3V4ZGDQ5ES25NMJP";
    private static final Integer RESULT_STATUS_OK;
    private static Gson gson;
    private Logger logger;

    public Erc20Component() {
        this.logger = LoggerFactory.getLogger((Class)Erc20Component.class);
    }

    public List<Erc20Transcation> getTranscationListByAddress(final Long startblock, final Long endblock, final String contractAddress) throws Exception {
        String param = "module=account&action=tokentx&contractaddress=_contractAddress&startblock=_startblock&endblock=_endblock&sort=desc&apikey=K39YBUV1FK2XJGMNKN3V4ZGDQ5ES25NMJP";
        param = param.replace("_startblock", startblock + "");
        param = param.replace("_endblock", endblock + "");
        param = param.replace("_contractAddress", contractAddress);
        List<Erc20Transcation> list = new ArrayList<Erc20Transcation>();
        final String result = HttpUtils.sendPostRequest("http://api-cn.etherscan.com/api", param, "utf-8");
        final Erc20ResultOBJ obj = (Erc20ResultOBJ)Erc20Component.gson.fromJson(result, (Class)Erc20ResultOBJ.class);
        if (obj.getStatus() == Erc20Component.RESULT_STATUS_OK) {
            list = obj.getResult();
        }
        else {
            list = null;
        }
        return list;
    }

    public static void main(final String[] args) throws Exception {
        final Erc20Component erc20Component = new Erc20Component();
        final List<Erc20Transcation> transcationList = erc20Component.getTranscationListByAddress(10154879L, 10154898L, "0xff642a0D2d0f3B15D7F19d6F0F2c9139BE1Aa29e");
        for (final Erc20Transcation result : transcationList) {
            if (result.getTo().equalsIgnoreCase("0x0874985c78c2d1c4df521cbc24e8d99ea58fca9c")) {
                System.out.println(JSON.toJSONString((Object)result));
                Double rechargeNum = rechargeNum = Convert.fromWei(new BigDecimal(Double.toString(Double.parseDouble(result.getValue()))), Convert.Unit.ETHER).toBigInteger().doubleValue();
                System.out.println(rechargeNum);
            }
        }
    }

    static {
        RESULT_STATUS_OK = 1;
        Erc20Component.gson = new Gson();
    }
}
