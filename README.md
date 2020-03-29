# springcloud2020
2020年springcloud学习

[sprinfcloud-alibaba](https://spring-cloud-alibaba-group.github.io/github-pages/greenwich/spring-cloud-alibaba.html)
#### nacos config
1. 使用添加 nacos config gav坐标
2. 配置bootstrap.yml和application.yml
[appilcatiom.yml](cloudalibaba-nacos-config-client9004\src\main\resources\application.yml)
[bootstrap.yml](cloudalibaba-nacos-config-client9004\src\main\resources\bootstrap.yml)
3. bootstrap.yml中配置config和discovery配置信息，application.yml添加本应用的配置信息
4. 启动类添加@EnableDiscoveryClient
5. controller上添加@RefreshScope
6. 注意nacos-server中配置信息的DateId,group,namespace,文件格式必须与配置中相同
7. [nacos文档](https://nacos.io/zh-cn/docs/what-is-nacos.html)


#### sentinel demo
[sentinel wiki](https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D)
1. 添加sentinel,nacos discovery,sentinel datasource nacos坐标
2. application.yml添加nacos，sentinel配置 [appilcatiom.yml](cloudalibaba-sentinel-service8401\src\main\resources\application.yml)
3. 启动类
4. 测试controller
5. 启动nacos-server和sentinel-dashboard，发一个请求到demo，
测试是否注册到sentinel，如果成功将在实时监控页面看到QPS情况
(没有QPS不会显示)
6. 簇点链路将会展示被访问到的路径与访问信息，可以在这里进行流控降级等操作
 - 设置流控规则，设定应用来源与资源名可配置，一个资源可有多规则，优先级为：{some_origin_name} > other > default
 - default：对所有资源进行流控，{some_origin_name}：对某资源进行流控，other：对除该资源进行流控
7. 流控模式
 - QPS直接失败：在设置的QPS达到阈值，就会触发流控，返回错误信息：block by sentinel
 - 并发线程数直接失败：该资源只有限定个线程进行服务，当进入的请求需要更多线程处理时，返回错误信息：block by sentinel
 - 关联失败：该资源与其他资源关联，当其他资源达到阈值(在本流控规则设置)，本资源发生失败。一般调用链路，存在相互依赖或资源争抢，故让部分资源请求过频繁时，限流对应的资源，减少无用调用。
 - 链路：限定请求链路的入口资源，只有这个入口资源的请求才被统计到阈值中，其它的请求则无视？
8. 流控效果
 - 直接失败：即达成条件后直接返回错误信息
 - Warm Up：为防止资源在平时低流量，在某刻达到高峰把系统打死（比如秒杀）。设置预热时间，资源的阈值在起始时为(设定阈值/冷加载因子，默认为3)，
 在请求达到初始阈值时，先报错，然后会在预热时间内缓缓将阈值提升到设定值。即系统从空闲到繁忙拥有一定预热时间。
 - 匀速排队：只能选择QPS模式。当大量请求过来，使得系统可以匀速处理这些请求，在超时时间内，请求按照设置的QPS阈值匀速通过，其它等待。
 主要用于处理突发间隔性流量，如消息队列。即不拒绝请求，也保证系统稳定
9. 降级策略
 - RT：1秒内进入大于等于5个QPS，其中平均响应时间不超过设定的RT毫秒数。如果超过，则在时间窗口内降级该服务。
 - 异常比例：当资源请求>=5/s,其中请求发生异常的比例大于阈值，则时间窗口期降级该服务。注意：发生降级前，该报错的还是报错。
 - 异常数：异常数以分钟计数。当资源在近一分钟的请求中异常请求数量大于阈值，则时间窗口内发送熔断降级。
10. 热点参数限流
 - 热点参数限流只针对QPS，只对于某服务接口的参数进行限流
 - 
 