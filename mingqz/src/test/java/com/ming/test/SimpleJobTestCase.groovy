package com.ming.test

import com.ming.scheduler.controller.vo.JobVO
import com.ming.scheduler.entity.ScheduleJobConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.web.client.RestTemplate

/**测试 job 基础功能 测试案例
 *
 * @author ming test
 * @date 2017-12-09 10:55
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SimpleJobTestCase {

    RestTemplate restTemplate = new RestTemplate()
    String host = "http://localhost:8080/"


    @Test
    createJavaJob() {
        JobVO jobVO = new JobVO()
        jobVO.setJobType(ScheduleJobConfig.JobType.JAVA.type)
        print(restTemplate.postForEntity(host + "", jobVO, String.class).getBody())
    }


    @Test
    createGroovyJob() {
        //注册 groovy job

        //运行 groovy job
        JobVO jobVO = new JobVO()
        jobVO.setJobType(ScheduleJobConfig.JobType.GROOVY.type)
        print(restTemplate.postForEntity(host + "", jobVO, String.class).getBody())
    }

}
