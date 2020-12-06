package cn.ztuo.bitrade.service.contractdouble;

import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.contractdouble.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.contractdouble.*;
import cn.ztuo.bitrade.pagination.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;

import java.util.*;

import com.querydsl.core.types.*;

@Service
public class ContractDoubleMemberApiService {
    @Autowired
    private ContractDoubleMemberApiDao contractDoubleMemberApiDao;

    public Page<ContractDoubleMemberApi> getMemberApiList(final long memberId, final int pageNum, final int pageSize, final int ifDefault) {
        Sort orders = Sort.by(new Sort.Order[]{new Sort.Order(Sort.Direction.DESC, "sequence")});
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize, orders);
        Criteria<ContractDoubleMemberApi> specification = new Criteria<ContractDoubleMemberApi>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("deleteFlag", 0, false));
        specification.add(Restrictions.eq("ifDefault", ifDefault, false));
        return (Page<ContractDoubleMemberApi>) this.contractDoubleMemberApiDao.findAll((Specification) specification, (Pageable) pageRequest);
    }

    public ContractDoubleMemberApi findMemberApiByIfDefault(final long memberId, final int ifDefault) {
        final Criteria<ContractDoubleMemberApi> specification = new Criteria<ContractDoubleMemberApi>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("deleteFlag", 0, false));
        specification.add(Restrictions.eq("ifDefault", ifDefault, false));
        return this.contractDoubleMemberApiDao.findOne(specification).get();
    }

    public ContractDoubleMemberApi save(final ContractDoubleMemberApi api) {
        return this.contractDoubleMemberApiDao.saveAndFlush(api);
    }

    public ContractDoubleMemberApi findApiByMemberAndApiKey(final long memberId, final String apiKey, final String secretKey) {
        final Criteria<ContractDoubleMemberApi> specification = new Criteria<ContractDoubleMemberApi>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("deleteFlag", 0, false));
        specification.add(Restrictions.eq("apiKey", apiKey, false));
        specification.add(Restrictions.eq("secretKey", secretKey, false));
        return (ContractDoubleMemberApi) this.contractDoubleMemberApiDao.findOne((Specification) specification).get();
    }

    public int updateApiIfDefault(final long memberId, final int ifDefault) {
        return this.contractDoubleMemberApiDao.updateApiIfDefault(memberId, ifDefault);
    }

    public ContractDoubleMemberApi get(final Long id) {
        return (ContractDoubleMemberApi) this.contractDoubleMemberApiDao.getOne(id);
    }

    public int updateApiDefault(final Long id) {
        return this.contractDoubleMemberApiDao.updateApiDefault(id);
    }

    public int deleteApi(final Long id) {
        return this.contractDoubleMemberApiDao.deleteApi(id);
    }

    public List<ContractDoubleMemberApi> findAll() {
        final Criteria<ContractDoubleMemberApi> specification = new Criteria<ContractDoubleMemberApi>();
        specification.add(Restrictions.eq("deleteFlag", 0, false));
        return (List<ContractDoubleMemberApi>) this.contractDoubleMemberApiDao.findAll((Specification) specification);
    }

    public Page<ContractDoubleMemberApi> findAll(final Predicate predicate, final Pageable pageable) {
        final Page<ContractDoubleMemberApi> page = (Page<ContractDoubleMemberApi>) this.contractDoubleMemberApiDao.findAll(predicate, pageable);
        return page;
    }

    public List<ContractDoubleMemberApi> findMemberApiByMemberId(final long memberId) {
        final Criteria<ContractDoubleMemberApi> specification = new Criteria<ContractDoubleMemberApi>();
        specification.add(Restrictions.eq("memberId", memberId, true));
        specification.add(Restrictions.eq("deleteFlag", 0, false));
        return (List<ContractDoubleMemberApi>) this.contractDoubleMemberApiDao.findAll((Specification) specification);
    }
}
