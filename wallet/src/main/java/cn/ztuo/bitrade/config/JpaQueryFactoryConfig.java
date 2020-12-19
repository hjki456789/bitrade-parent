package cn.ztuo.bitrade.config;

import javax.persistence.*;
import com.querydsl.jpa.impl.*;
import org.springframework.context.annotation.*;

@Configuration
public class JpaQueryFactoryConfig
{
    @Bean
    public JPAQueryFactory getJPAQueryFactory(final EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
