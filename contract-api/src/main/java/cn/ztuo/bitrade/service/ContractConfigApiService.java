package cn.ztuo.bitrade.service;

import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.util.*;
import org.apache.commons.lang3.*;

@Service
public class ContractConfigApiService {
    @Autowired
    private ContractConfigService contractConfigService;

    public MessageResult getBaseConfig(final String configKey) {
        if (StringUtils.isNotEmpty((CharSequence) configKey)) {
            return MessageResult.success("操作成功", this.contractConfigService.findByConfigKey(configKey));
        }
        return MessageResult.success("操作成功", this.contractConfigService.findAll());
    }
}
