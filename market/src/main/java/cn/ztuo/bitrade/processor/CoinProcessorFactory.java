package cn.ztuo.bitrade.processor;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CoinProcessorFactory {
    private HashMap<String, CoinProcessor> processorMap;
    private HashMap<String, CoinProcessor> contractMap;
    private List<String> exchangeZones = new ArrayList<>();
    public CoinProcessorFactory() {
        processorMap = new HashMap<>();
        this.contractMap = new HashMap<>();
    }

    public void addProcessor(String symbol, CoinProcessor processor) {
        log.info("CoinProcessorFactory addProcessor = {}",symbol);
        processorMap.put(symbol, processor);
        if(!exchangeZones.contains(processor.getBaseCoin())){
            exchangeZones.add(processor.getBaseCoin());
        }
    }

    public void addProcessor(final String source, final String symbol, final CoinProcessor processor) {
        CoinProcessorFactory.log.info("CoinProcessorFactory addProcessor = {}", symbol);
        if (StringUtils.isNotEmpty(source) && source.equals("contract")) {
            this.contractMap.put(symbol, processor);
        }
        else {
            this.processorMap.put(symbol, processor);
        }
        if (!this.exchangeZones.contains(processor.getBaseCoin())) {
            this.exchangeZones.add(processor.getBaseCoin());
        }
    }

    public CoinProcessor getProcessorByCoin(String coin){
        for(String base:exchangeZones){
            String symbol = coin + "/" +base;
            if(processorMap.containsKey(symbol)){
                return processorMap.get(symbol);
            }
        }
        return null;
    }
    public CoinProcessor getProcessorByCoin(final String source, final String coin) {
        for (final String base : this.exchangeZones) {
            final String symbol = coin + "/" + base;
            if (StringUtils.isNotEmpty(source) && source.equals("contract")) {
                if (this.contractMap.containsKey(symbol)) {
                    return this.contractMap.get(symbol);
                }
                continue;
            }
            else {
                if (this.processorMap.containsKey(symbol)) {
                    return this.processorMap.get(symbol);
                }
                continue;
            }
        }
        return null;
    }

    public List<CoinProcessor> getProcessorListByCoin(String coin){
        List<CoinProcessor> list = new ArrayList<>();
        for(String base:exchangeZones){
            String symbol = coin + "/" +base;
            if(processorMap.containsKey(symbol)){
                list.add(processorMap.get(symbol));
            }
        }
        return list;
    }
    public CoinProcessor getProcessor(final String source, final String symbol) {
        if (StringUtils.isNotEmpty(source) && source.equals("contract")) {
            return this.contractMap.get(symbol);
        }
        return this.processorMap.get(symbol);
    }

    public CoinProcessor getProcessor(String symbol) {
        return processorMap.get(symbol);
    }

    public HashMap<String, CoinProcessor> getProcessorMap() {
        return processorMap;
    }
    public HashMap<String, CoinProcessor> getProcessorMap(final String source) {
        if (StringUtils.isNotEmpty(source) && source.equals("contract")) {
            return this.contractMap;
        }
        return this.processorMap;
    }
}
