#### 结构
api: 存放接口和entity、vo、dto的包     
service: 存放具体实现的服务的包
为何这样拆分  api包 后续可以以 jar的方式给其他java项目引用   
#### 使用技术
jdk8  
spring boot   
spring cloud   
mybatis  
log4j2  
pgsql   
redis   
guava utils  
apache utils  
maven   
groovy   
docker  
jenkins  

### 编码规范
1:关于mybatis sql相关的
全部采用使用xml方式 禁止使用 注解方式 
因为虽然现在关联查询少了 但是还是有 注解方式些关联查询太麻烦 不如直接写xml 让代码更加干爽 
2: