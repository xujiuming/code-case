#### hvstrix 
```
pom.xml中添加
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>


//开启注册
@EnableDiscoveryClient
//开启熔断器
@EnableCircuitBreaker
@SpringBootApplication
//一个顶上面三个
//@SpringCloudApplication
public class StartCommon {}
```
####流程
![流程图](./image/hvstrix流程.jpeg)
* 1:创建command
创建HystrixCommand(依赖服务返回单个结果)或 HystrixObservableCommand(依赖服务返回多个结果)


* 2:
* 3:
* 4:
* 5:
* 6:
* 7:
* 8:
* 9:

