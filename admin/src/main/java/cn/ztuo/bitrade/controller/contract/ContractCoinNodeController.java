package cn.ztuo.bitrade.controller.contract;

import cn.ztuo.bitrade.controller.common.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.service.*;
import java.util.*;
import com.querydsl.core.types.*;
import org.springframework.data.domain.*;
import org.apache.shiro.authz.annotation.*;
import cn.ztuo.bitrade.annotation.*;
import cn.ztuo.bitrade.constant.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.*;
import com.querydsl.core.types.dsl.*;
import cn.ztuo.bitrade.entity.*;
import cn.ztuo.bitrade.util.*;

@RestController
@RequestMapping({ "contract/contract-coin-node" })
public class ContractCoinNodeController extends BaseAdminController
{
    @Autowired
    private LocaleMessageSourceService messageSource;
    @Autowired
    private ContractCoinNodeService contractCoinNodeService;

    @RequiresPermissions({ "contract:contract-coin-node:page-query" })
    @PostMapping({ "page-query" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "分页查找节点参数列表")
    public MessageResult contractNodePageList(final Integer enable, final String coinId, final String coinSymbol, final String nodeId, final String nodeName, final PageModel pageModel) {
        Predicate predicate = this.getPredicate(enable, coinId, coinSymbol, nodeId, nodeName);
        if (pageModel.getProperty() == null) {
            final List<String> list = new ArrayList<String>();
            list.add("sequence");
            final List<Sort.Direction> directions = new ArrayList<Sort.Direction>();
            directions.add(Sort.Direction.DESC);
            pageModel.setProperty((List)list);
            pageModel.setDirection((List)directions);
        }
        final Page<ContractCoinNode> all = (Page<ContractCoinNode>)this.contractCoinNodeService.findAll(predicate, pageModel.getPageable());
        return this.success(all);
    }

    @RequiresPermissions({ "contract:contract-coin-node:detail-coin-node" })
    @PostMapping({ "detail-coin-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "节点参数信息详情")
    public MessageResult contractNodeDetail(@RequestParam("id") final String id) {
        final ContractCoinNode contractCoinNode = this.contractCoinNodeService.findOne(id);
        if (contractCoinNode == null) {
            return MessageResult.error("节点不存在!");
        }
        return this.success(contractCoinNode);
    }

    @RequiresPermissions({ "contract:contract-coin-node:add-coin-node" })
    @PostMapping({ "add-coin-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "新增节点参数信息")
    public MessageResult addContractCoinNode(@RequestParam("coinId") final String coinId, @RequestParam("nodeId") final String nodeId) {
        if (StringUtils.isEmpty(coinId)) {
            return MessageResult.error("币种产品不能为空!");
        }
        if (StringUtils.isEmpty(nodeId)) {
            return MessageResult.error("节点不能为空!");
        }
        final ContractCoinNode coinNodeOld = this.contractCoinNodeService.getByCoinIdAndNodeId(coinId, nodeId);
        if (coinNodeOld != null) {
            return MessageResult.error("该币种对应的节点已存在对应节点参数!");
        }
        this.contractCoinNodeService.insert(coinId, nodeId);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-coin-node:update-coin-node" })
    @PostMapping({ "update-coin-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "更新节点参数信息")
    public MessageResult updateContractCoinNode(@RequestParam("id") final String id, @RequestParam("coinId") final String coinId, @RequestParam("nodeId") final String nodeId) {
        final ContractCoinNode coinNodeOld = this.contractCoinNodeService.findOne(id);
        if (coinNodeOld == null) {
            return MessageResult.error("该节点参数不存在!");
        }
        coinNodeOld.setId(id);
        if (!StringUtils.isEmpty(coinId)) {
            final ContractCoin contractCoin = new ContractCoin(coinId);
            coinNodeOld.setContractCoin(contractCoin);
        }
        if (!StringUtils.isEmpty(nodeId)) {
            final ContractNode contractNode = new ContractNode(nodeId);
            coinNodeOld.setContractNode(contractNode);
        }
        final ContractCoinNode coinNode = this.contractCoinNodeService.getByCoinIdAndNodeId(coinId, nodeId);
        if (coinNode != null && !coinNode.getId().equals(coinNodeOld.getId())) {
            return MessageResult.error("该币种对应的节点已存在对应节点参数!");
        }
        this.contractCoinNodeService.update(coinNodeOld);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    @RequiresPermissions({ "contract:contract-coin-node:alter-enable" })
    @PostMapping({ "alter-enable" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "修改禁用状态")
    public MessageResult alterEnable(@RequestParam("id") final String id, @RequestParam("enable") final Integer enable) {
        Assert.notNull(enable, "validate enable!");
        final int result = this.contractCoinNodeService.updateEnable(id, enable);
        if (result > 0) {
            return this.success(this.messageSource.getMessage("SUCCESS"));
        }
        return MessageResult.error("FAIL!");
    }

    @RequiresPermissions({ "contract:contract-coin-node:delete-coin-node" })
    @PostMapping({ "delete-coin-node" })
    @AccessLog(module = AdminModule.CONTRACT, operation = "删除节点参数信息")
    public MessageResult deleteCoinNode(@RequestParam("ids") final String[] ids) {
        this.contractCoinNodeService.deleteByIds(ids);
        return this.success(this.messageSource.getMessage("SUCCESS"));
    }

    public Predicate getPredicate(final Integer enable, final String coinId, final String coinSymbol, final String nodeId, final String nodeName) {
        final ArrayList<BooleanExpression> booleanExpressions = new ArrayList<BooleanExpression>();
        if (enable != null) {
            booleanExpressions.add(QContractCoinNode.contractCoinNode.enable.eq(enable));
        }
        if (!StringUtils.isEmpty(nodeId)) {
            booleanExpressions.add(QContractCoinNode.contractCoinNode.contractNode.id.eq(nodeId));
        }
        if (!StringUtils.isEmpty(nodeName)) {
            booleanExpressions.add(QContractCoinNode.contractCoinNode.contractNode.nodeName.like("%" + nodeName + "%"));
        }
        if (!StringUtils.isEmpty(coinId)) {
            booleanExpressions.add(QContractCoinNode.contractCoinNode.contractCoin.id.eq(coinId));
        }
        if (!StringUtils.isEmpty(coinSymbol)) {
            booleanExpressions.add(QContractCoinNode.contractCoinNode.contractCoin.coinSymbol.eq(coinSymbol));
        }
        return PredicateUtils.getPredicate((List)booleanExpressions);
    }
}
