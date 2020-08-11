### 后端技术选型   
|名称|版本|备注|
|:--|:--|:---|
|jdk|openjdk14| 这里版本选择11也行|
|maven |3.x|使用maven + shell方式来管理|
|spring boot |2.3.x|最新稳定版本 选用servlet模式 不采用webflux模式|
|sharding JDBC|4.1.1|最新稳定版 提供db读写分离、数据分区分库功能|
|postgres| 10 or 11|看运维部署版本|
|redis|5.x |提供缓存、注册中心、mq等功能|
|docker ||机房安装版本为准|
|mybatis plus ||最新稳定版、业务模块使用mybatis 其他公共模块考虑使用jpa来减少工作量|
|quartz or xxl job||考虑中 初期先沿用spring Scheduled 等定时任务达到一定量级别 建立复杂的调度中心来维护|
|spring boot admin |最新稳定版|java服务监控|
|swagger boot |3.0.0|接口文档|
|spring cloud |最新稳定版|自实现redis注册中心|
|alibaba Sentinel|稳定版|流量监控、熔断|

### 模块划分    

|名称|功能|备注|    
|:---|:--|:--|  
|core|核心模块|各种无状态工具类、对象|
|base|基础模块|各种公共配置  如mvc、orm等配置|
|user|user模块|业务模块-用户相关模块|  
|common|common|业务模块-基础模块|  
|gateway|网关模块|流量入口、swagger聚合|   
|support|支持模块 考虑拆分|zipkin、admin、mq center、sentinel admin、全局公共服务管理、Seata|  
 
 
大体结构如下 
1. microService
   1. xxxModule  功能模块
      1. api    rpc接口定义  
      2. service  controller层 、rpc接口实现层、dao层
     
命名规则:
* controller  XxxController    
* rpc接口: IXxxService    
* rpc接口实现层 IXxxServiceImpl
* 内部服务接口 : XxxService 
* 内部服务接口实现层: XxxServiceImpl 
* dao层: mybatis -> XxxMapper   jpa-> XxxRepository



     
     





