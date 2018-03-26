内部调用接口使用service定义  Impl后缀为实现 Clietn后缀 为feignclient 
当远程调用其他模块服务的时候定义feignclient 需要再client包下建立模块service  内部定义client服务继承service接口

启动需要的配置要配置在bootstrap.yml中 这个是第一个加载的    
其他配置写入config server中 禁止直接在项目中写乱七八糟的配置


##目录说明
* common:基础模块 
* config:config server 读取配置地址
* core:核心模块  
* doc:相关笔记、文档
* eurekaServer:eureka注册中心
* gateway：基于zuul的网关服务
* hystrixDashboard:熔断器监控
* mq:消息队列处理模块
* timer:定时任务模块
* turbine:集群监控模块
* user：用户模块 