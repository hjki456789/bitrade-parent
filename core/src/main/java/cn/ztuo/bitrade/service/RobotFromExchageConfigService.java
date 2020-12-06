package cn.ztuo.bitrade.service;

import cn.ztuo.bitrade.service.Base.*;
import org.springframework.stereotype.*;
import cn.ztuo.bitrade.dao.*;
import org.springframework.beans.factory.annotation.*;
import cn.ztuo.bitrade.entity.*;

import java.io.*;
import java.util.*;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;

import javax.persistence.criteria.*;

@Service
public class RobotFromExchageConfigService extends BaseService {
    @Autowired
    private RobotFromExchageConfigDao robotFromExchageConfigDao;

    public RobotFromExchageConfig findOne(final Long id) {
        return (RobotFromExchageConfig) this.robotFromExchageConfigDao.getOne(id);
    }

    public List<RobotFromExchageConfig> findList() {
        final Specification<RobotFromExchageConfig> spec = (Specification<RobotFromExchageConfig>) ((root, criteriaQuery, criteriaBuilder) -> null);
        final Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        final Sort sort = Sort.by(new Sort.Order[]{order});
        return (List<RobotFromExchageConfig>) this.robotFromExchageConfigDao.findAll((Specification) spec, sort);
    }
}
