# springcloud2020
2020年springcloud学习
[脑图]()
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
4. 测试controller [FlowLimitController](cloudalibaba-sentinel-service8401\src\main\java\com\atguigu\springcloud\controller\FlowLimitController.java)
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
 - 根据参数索引指定对哪个参数设定热点规则，在有该参数的服务请求，如果请求频率大于阈值，则在时间窗口发生限流
 - 如果请求中不包含热点规则的参数，热点规则就不对其起作用。
 - 参数例外项:对于某个服务的某参数的热点限流为特例值时，对其的限流阈值不同于该参数为其它值，可以配置例外项。只有基本数据类型与String可以配置，记得按添加按钮
11. 系统规则
 - 系统规则对整个微服务进行熔断，只要达到阈值，整个微服务不可用。谨慎配置
 - Load自适应：仅对Linux/Unix可用，当微服务所在主机负载大于阈值，且系统并发线程数大于预估系统容量，则触发系统保护。设定参考CPU Cores*2.5
 - CPU usage：系统cpu使用率大于阈值（0-1），触发系统保护
 - 平均RT：当单台机器上所有服务入口流量的平均RT大于阈值，触发系统保护。单位毫秒
 - 并发线程数：当单台机器上所有服务入口流量的并发线程数大于阈值，触发系统保护
 - 入口QPS： 当单台机器上所有服务入口流量的QPS大于阈值，触发系统保护
12. @SentinelResource
 - value: 服务名。随便，一般和请求名相同
 - blockHandlerClass: 自定义流控处理类名.class。该类内部自定义流控处理静态方法
 - blockHandler：流控处理方法名。当流控发生，进入该方法。可以在controller本类，也可以在自定义处理类中。处理方法必须有和本方法相同的返回值和形参，BlockException形参。
 - fallBackClass：同上
 - fallbcak: 服务熔断处理方法名。管运行异常
 - exceptionsToIgnore：忽略的异常。
 - 使用资源名（value）限流: 限流后续处理需要自定义处理方法，否则报ErrorPage
 - 使用Url地址限流：发生限流后，sentinel自行处理，报Blocked by Sentinel（fow limiting），不适合现实业务
 - 注解不配置fallback和blockHandler，没有任何处理，不合适
 - 只配fallback：只接管业务异常发生，当抛出RuntimeException时，对应处理方法会成为主逻辑。（发现如果配了流控规则，到达阈值也会调用fallback？）
 - 只配blockHandler：只接管sentinel配置规则。发生业务异常，不会处理。当满足sentinel配置阈值，blockHandler处理方法成为主逻辑。
 - 都配置：发生业务异常fallback接管，发生sentinel配置问题，blockHandler接管。都发生，sentinel优先接管
 - 配合Ribbon和OpenFeign使用:添加RestTemplate@LoaBalance配置类，主启动类添加@EnableFeignClients，添加@FeignClent的接口与其实现，并注册为Bean，在其实现中添加相应降级方法
 ，则可以在controller中使用对应方法。
13. sentinel规则持久化
 - 使用nacos进行持久化
 - 导入sentinel-datasource-nacos的GAV坐标
 - 配置yml（8401）
 - 在nacos中写入流控规则配置
 - 关闭服务后规则不消失
14. sentinel有三个核心类：SphU定义资源，Tracer定义统计，ContextUtil定义上下文
 ## seata
 1. 配置实例[seata入门配置demo](https://zhuanlan.zhihu.com/p/103120151)
 2. 需要启动nacos和seata服务器，并且配置seata注册到nacos，seata使用mysql进行事务
 3. 创建order、storage、account微服务，并配置。注意ribbon超时配置和关闭hystrix
 4. 在order的service方法上写@GlobalTransactional( name = "事务名称" , rollbackFor = 需要回滚的异常.class)
 5. 当所有服务任意出现错误，就会回滚所有服务
 6. seata存在TC(事务协调者，即seata服务器coordinator，维护全局和分支事务的状态，驱动全局事务提交或回滚)、TM(事务管理者，即@GlobalTransactional的服务，定义全局事务的范围：开始全局事务、提交或回滚全局事务)、RM(资源管理器，管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚)
 