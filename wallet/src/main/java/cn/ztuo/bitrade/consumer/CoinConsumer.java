package cn.ztuo.bitrade.consumer;

import cn.ztuo.bitrade.entity.SysAddressPool;
import cn.ztuo.bitrade.service.SysAddressPoolService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.ztuo.bitrade.constant.BooleanEnum;
import cn.ztuo.bitrade.entity.Coin;
import cn.ztuo.bitrade.entity.Member;
import cn.ztuo.bitrade.entity.MemberWallet;
import cn.ztuo.bitrade.service.CoinService;
import cn.ztuo.bitrade.service.MemberService;
import cn.ztuo.bitrade.service.MemberWalletService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Seven
 * @date 2019年02月08日
 */
@Component
public class CoinConsumer {

    private Logger logger = LoggerFactory.getLogger(CoinConsumer.class);
    @Autowired
    private CoinService coinService;
    @Autowired
    private MemberWalletService walletService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MemberService memberService;
    @PersistenceContext
    protected EntityManager em;

    @Autowired
    private SysAddressPoolService sysAddressPoolService;

    /**
     * 添加新币种，为用户增加钱包
     *
     * @param content
     */
    @KafkaListener(topics = {"coin-start"})
    public void handle(String content) {
        logger.info("handle coin-start,data={}", content);
        if (StringUtils.isEmpty(content)) {
            return;
        }
        JSONObject json = JSON.parseObject(content);
        if (json == null) {
            return;
        }
        Coin coin = coinService.findOne(json.getString("name"));
        if (coin != null) {
            List<Member> list = memberService.findAll();
            if (coin.getEnablePool() == 1 && coin.getChainType() != 0) {
                int i = 0;
                for (final Member member : list) {
                    ++i;
                    final SysAddressPool address = this.sysAddressPoolService.findByType(coin.getChainType());
                    if (address == null) {
                        continue;
                    }
                    this.sysAddressPoolService.updateStatus(address.getAddress());
                    final MemberWallet wallet = new MemberWallet();
                    wallet.setCoin(coin);
                    wallet.setMemberId(member.getId());
                    wallet.setBalance(new BigDecimal(0));
                    wallet.setFrozenBalance(new BigDecimal(0));
                    wallet.setAddress(address.getAddress());
                    this.em.persist((Object)wallet);
                    if (i % 1000 != 0 && i != list.size() - 1) {
                        continue;
                    }
                    this.em.flush();
                    this.em.clear();
                }
                this.em.flush();
                this.em.clear();
            }else if(coin.getEnableRpc().equals(BooleanEnum.IS_TRUE)){
                int size = list.size();
                List<String> list1 = new ArrayList<>();
                List<Map> mapList = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Member member = list.get(i);
                    list1.add("U" + member.getId());
                    if (i % 1000 == 0 || i == size - 1) {
                        //远程RPC服务URL,后缀为币种单位
                        String serviceName = "SERVICE-RPC-" + coin.getUnit();
                        String url = "http://" + serviceName + "/rpc/address/batch";
                        ResponseEntity<List> result = restTemplate.postForEntity(url, list1, List.class);
                        logger.info("remote call:service={},result={}", serviceName, result);
                        if (result.getStatusCode().value() == 200) {
                            mapList.addAll(result.getBody());
                        }
                        list1.clear();
                    }
                }
                int mapListSize = mapList.size();
                for (int i = 0; i < mapListSize; i++) {
                    MemberWallet wallet = new MemberWallet();
                    wallet.setCoin(coin);
                    wallet.setMemberId((Long) mapList.get(i).get("uid"));
                    wallet.setBalance(new BigDecimal(0));
                    wallet.setFrozenBalance(new BigDecimal(0));
                    wallet.setAddress(mapList.get(i).get("address").toString());
                    em.persist(wallet);
                    if (i % 1000 == 0 || i == size - 1) {
                        em.flush();
                        em.clear();
                    }
                }
                em.flush();
                em.clear();
            }
        }
    }
}
