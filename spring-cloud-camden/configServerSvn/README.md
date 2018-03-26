###  配置中心  
svn作为配置仓库 
config server 组件 原生支持git 如果非要用svn  必须在原本的配置上作修改 
必须配置的地方
1:pom 需要添加第三方 svn 工具包
```
<!-- spring cloud config svn repository -->
        <dependency>
            <groupId>org.tmatesoft.svnkit</groupId>
            <artifactId>svnkit</artifactId>
            <version>1.9.0</version>
        </dependency>
```
2:开启svn 作为配置仓库
在git中 
label=分支 
profile= 配置文件-后面名称 
name=配置文件-前面的名称 
git 仓库 如果需要为每个项目配置文件夹 只需要在配置uri的后面加上{application}

由于svn 的分支功能比较麻烦 
label = 仓库路径下的文件夹名称 
profile= 配置文件-后面名称
name=配置文件-前面名称
```
spring:
  cloud:
    config:
      server:
        svn:
          # svn 配置仓库地址 每个应用对应一个文件夹
          uri: https://svnDefaultRepository
          # svn 访问账户
          username: ming
          # svn 访问密码
          password: ming
          # 默认label 默认就是trunk 可不配置 
          default-label: trunk
  # 开启使用 svn 作仓库 必须  
  profiles:
    active: subversion
```
3:开启访问控制
和git 一样 
直接添加依赖 增加配置即可 如下
```
 <!-- spring  boot security 访问控制 -->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-security</artifactId>
         </dependency>
```
```
# 访问控制
security:
  user:
    name: ming
    password: ming
```
4:开启敏感数据加密
因为 config server 使用的是aes256 加密 所以必须替换jce 相关jar 
就算使用rsa 加密方式 也是需要jce相关jar 的  因为他还是用的aes256加密
rsa 加密方式配置
* 生成 rsa 密钥包  直接到控制台执行即可  使用的是jdk 的keytool 
```
configserver rsa 秘钥生成命令 有效时间 10000天
keytool -genkeypair -alias configServer -keyalg RSA \
-dname "CN=ming,OU=ming,O=ming,L=ming,ST=ming,C=ming" \
-keypass ming1 \
-keystore configServer.keystore \
-storepass ming2 \
-validity 10000 \
```
* 配置 密钥
将生成的configServer.keystore 文件复制到 maven 项目下的resource目录下  
```
encrypt:
  # rsa 密钥 设定 使用resources 下的 configServer.keystore
  key-store:
    location: configServer.keystore
    alias: configServer
    #  生成 密钥时候的密码 storepass
    password: ming2
    # 生成 密钥时候的签名  keypass
    secret: ming1

```
* 使用加密字符串替换 原始字符串
 
```
访问 configServer的 {[/encrypt],methods=[POST]} 接口  参数为要加密的字符串  返回加密字符串
例如 加密前 字符串a   通过接口获取加密后字符串 b
在a配置的地方 使用 {cipher}b  替换a  即可 
加密前
passwd=a
加密后
passwd={cipher}b  
```

#### 默认信息
1：默认 8888 端口  
####注意:
1:使用 svn 仓库
由于没有git仓库 采用 svn 仓库作配置仓库
2:使用 访问控制
避免 直接访问url 获取配置  增加访问控制
3:使用rsa 对敏感数据加密传输
避免账户密码等相关配置明文显示 使用rsa 进行加密 

label= svn仓库目录
name=配置前面名字
profiles=配置文件名字
"{[/{label}/{name}-{profiles}.properties]}"

configserver rsa 秘钥生成命令 有效时间 10000天
keytool -genkeypair -alias configServer -keyalg RSA \
-dname "CN=onlyedu,OU=onlyedu,O=onlyedu,L=onlyedu,ST=onlyedu,C=onlyedu" \
-keypass onlyedu \
-keystore configServer.keystore \
-storepass onlyedu \
-validity 10000 \