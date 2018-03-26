package com.ming.test.aliyun;

import com.ming.api.oss.OssUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
public class TestPushOss {
    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void testPushOss() {
        OssUtils.pushOssImage();
    }


/*    @Test
    public void testQueryDsl() {
        QUser qUser = QUser.user;
        JPAQuery<?> query = new JPAQuery<Void>(entityManager);
        User ming = query.select(qUser)
                .from(qUser)
                .where(qUser.username.eq("ming"))
                .fetchOne();
        System.out.println(JSON.toJSONString(ming));
    }*/
}
