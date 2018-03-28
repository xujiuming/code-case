log4j2 发送日志到es中 
有两种方案 
1: 使用socketAppender 通过tcp协议 发送 功能多  需要自己构建 es能够识别的 数据流 
2: 自定义 appender  使用es client 发送  格式比较标准 简单  容易理解  需要自行构建 失败重试  之类的工作   


github上的工具 版本有点不太适合 现有的es 集群  只能自行扩展一下 