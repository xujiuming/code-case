# 项目结构规范
顶级pom  用来管理所有项目所需jar包、插件、依赖  
一级pom 用来管理本项目的模块、公共依赖  
二级pom 管理本模块私有依赖  
#子项目说明
* user  
  用户、权限相关服务
  user::api  
  用户的实体、接口定义 方便其他模块调用查看  
  user::service  
  用户的具体方法实现 
* common    
  基础服务 
  common::api  
  基础服务的实体 接口定义
  common::service  
  基础服务的具体实现
