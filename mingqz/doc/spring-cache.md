缓存:没啥好说的 原理就是把读取慢的数据放到快速读取的地方  
例如 把 某些数据从数据库拿出来 丢到内存中 
一般缓存的套路:
分为分布式缓存 和单机缓存  分布式缓存就是 整个集群共享 、单机就是单独的某个节点拥有的缓存 
1：用一些 缓存框架 例如ehcache   分布式缓存
2:使用一些内存数据库 如mongo  redis 等 分布式缓存
3:使用guava、或者直接使用map 单机缓存 
为什么要使用spring 的cache的相关抽象对象
没啥 就是为了简单标准 使用spring的标准 可以通过自定义cacheManager来实现不同的缓存方案
其实就算你自定义一套缓存  你也是差不多的套路  不如直接使用spring 的标准 
总的来说 就是提供 添加缓存、访问缓存、删除缓存、定义缓存数据过期策略之类的  
从我个人角度来说  我觉得在代码中使用spring的缓存相关的接口或者注解 
我可以轻松的通过重写一些接口来扩展缓存相关的处理 而且也不需要在对代码进行大量的修改  
1:@Cacheable
org.springframework.cache.annotation包下 
添加缓存
cacheNames 定义缓存名称
key 缓存的key
condition 使用spel限制缓存的处理 例如 condition="#user.age < 30"
```
    @Cacheable(cacheNames = "user",key = "#user.id")
    public User getUser(User user){
      ....
    }
```
2:@CachePut
用法同@CacheAble
区别
@CachePut：这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存中。
@Cacheable：当重复使用相同参数调用方法的时候，方法本身不会被调用执行，即方法本身被略过了，取而代之的是方法的结果直接从缓存中找到并返回了。
参考地址:http://blog.csdn.net/maoyeqiu/article/details/50017157
其实就是 在进行更新操作 使用@CachePut 即可
3:CacheEvict
删除缓存 @Cacheable的逆向操作
也支持condition 使用spel来进行条件限制
是否清理所有缓存:allEntries boolean  默认false  true 删除此实例所有缓存 忽略key
在方法之前还是之后清理beforeInvocation boolean  默认false  true 在方法之前执行   
```
    @CacheEvict(cacheNames = "user",key = "#user.id")
    public void removeUser(User user){
        ....
    }
```
4:Caching
定义信息组时候使用 
可以定义 cacheable、CachePut、CacheEvict
例如 有三个对象  user  、emp、admin     emp和admin是继承user的  但是 emp和admin对象需要在不同的缓存的实例中缓存
```
    @Caching(cacheable = [
            @Cacheable(cacheNames = "emp",condition = "#user instanceof Emp"),
            @Cacheable(cacheNames = "admin",condition = "#user instanceof Admin")
    ])
    public User getUser(User user){...}
```
5:@CacheConfig
类注释提供 公共配置 
spring cacheManager
1:SimpleCacheManager 是一个简化实现
2:NoOpCacheManager 测试使用 没有实际功能
3:CompositeCacheManager 定义多个cacheManager






