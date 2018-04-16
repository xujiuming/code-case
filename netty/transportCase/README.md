传输案例 
java oio nio  
netty oio nio的案例实现

总结：  
java实现oio和nio的代码 提供的api 区别比较大   
oio 不需要selector 直接server socket接受 处理  
nio 通过selector 协调 分配给相应的 socket 处理   
netty 实现oio和nio 提供的api 基本相同 就是在使用发送方式和发送渠道时候 需要保持一致   
例如 使用NioEventLoopGroup 那么传输渠道必须是NioServerChannelSocket   
使用OioEventLoopGroup那么必须使用OioServerChannelSocket   
