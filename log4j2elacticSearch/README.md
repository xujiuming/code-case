log4j2 发送日志到es中 
有两种方案 
1: 使用socketAppender 通过tcp协议 发送 功能多  需要自己构建 es能够识别的 数据流 
2: 自定义 appender  使用es client 发送  格式比较标准 简单  容易理解  需要自行构建 失败重试  之类的工作   



 * log4j2  和es交互的适配器
 * 不采用 socket appender 原因是 socket appender 功能很齐全 但是 比较麻烦 改起来
 * 不如直接用es client 实现一个简单的appender
 * ElasticAppender的实现是通过设定一个发送线程去轮询 queue 发送到es  这样 单机 环境下 很合适  如果是分布式环境 不如直接丢 mq 中好用
 * 我的思路 初期 appender 只要简单的发送日志到 es上 至于性能问题  需要的时候 通过桥接第三方 mq 去实现 本身不做复杂的功能
 * 每次 直接从线程池调用一个线程出来 执行此次task  不做 内部queue
 *

github上的工具 看起来可以 
```
        <dependency>
            <groupId>net.inemar.utility</groupId>
            <artifactId>log4j2elastic</artifactId>
            <version>3.1.0</version>
        </dependency>
```
复制下来 修改下  用 


1:start  es +kibana
```
docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana
```
2:use  elastic appender  
```
```