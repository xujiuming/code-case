### 项目使用技术
|技术名|作用|备注|
|:-----|:--|:--|
|jdk8|sdk|java开发包版本|
|groovy|sdk|groovy开发包版本|
|spring boot |框架|核心框架|
|spring cloud |框架|主要是为了引入 spring cloud config client|
|spring platform|框架|依赖包版本管理|
|maven|项目打包依赖管理工具|管理工具|


### 项目模块划分
|模块名|模块功能|备注|
|:----|:-----|:---|
|core|核心模块|各种无状态工具类等等|
|store|存储模块|dao、mapper、repository等等|
|service|服务模块|服务|
|web|controller模块 对外提供接口|api、view等模块|

包名规则: com.组织名.子模块名.功能名...


