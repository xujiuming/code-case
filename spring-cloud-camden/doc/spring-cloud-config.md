/{application}/{profile}/{label}
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
{application}-{profile}.properties 
{label}分支

是spring-cloud-starter-config  不是spring-cloud-config
#注意！！！！！
spring-cloud-config-client 的配置必须在bootstrap.yml中 
因为 这个才是在启动前加载到env中的  
application.yml是在config-client加载后才会加载配置
####刷新 
需要 actuator模块 
需要在需要刷新的类上注解 @RefreshScope
post 请求refresh端点 从新访问即可

config server配置优先级最高 覆盖所有配置 ！！！！


@RefreshScope只能注释在config中无效 必须在引用的地方注解
