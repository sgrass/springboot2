
## 服务短路（CircuitBreaker）

QPS: Query Per Second



TPS: Transaction Per Second



QPS: 经过全链路压测，计算单机极限QPS，集群 QPS = 单机 QPS * 集群机器数量 * **可靠性比率**



全链路压测 除了压 极限QPS，还有错误数量



全链路：一个完整的业务流程操作



JMeter：可调整型比较灵活



## Spring Cloud Hystrix Client



### 官网:https://github.com/Netflix/Hystrix



> Reactive Java 框架：
>
> * Java 9 Flow API
> * Reactor
> * RxJava（Reactive X）



### 激活 Hystrix

通过 `@EnableHystrix`  激活



> Hystrix 配置信息wiki：https://github.com/Netflix/Hystrix/wiki/Configuration



### Hystrix


#### 注解方式（Annotation）

`HystrixDemoController.java`



#### 编程方式

`HystrixDemo2Controller.java`





对比 其他 Java 执行方式：

`Future.java` , `RxJava.java`




### Health Endpoint(`/health`)



```json
{
  status: "UP",
  diskSpace: {
    status: "UP",
    total: 500096983040,
    free: 304113217536,
    threshold: 10485760
  },
  refreshScope: {
    status: "UP"
  },
  hystrix: {
    status: "UP"
  }
}
```



### 激活熔断保护



`@EnableCircuitBreaker` 激活 ：`@EnableHystrix ` + Spring Cloud 功能

`@EnableHystrix` 激活，没有一些 Spring Cloud 功能，如 `/hystrix.stream` 端点



### Hystrix Endpoint(`/hystrix.stream`)



```json
data: {
    "type": "HystrixThreadPool",
    "name": "HystrixDemoController",
    "currentTime": 1509545957972,
    "currentActiveCount": 0,
    "currentCompletedTaskCount": 14,
    "currentCorePoolSize": 10,
    "currentLargestPoolSize": 10,
    "currentMaximumPoolSize": 10,
    "currentPoolSize": 10,
    "currentQueueSize": 0,
    "currentTaskCount": 14,
    "rollingCountThreadsExecuted": 5,
    "rollingMaxActiveThreads": 1,
    "rollingCountCommandRejections": 0,
    "propertyValue_queueSizeRejectionThreshold": 5,
    "propertyValue_metricsRollingStatisticalWindowInMilliseconds": 10000,
    "reportingHosts": 1
}
```





## Spring Cloud Hystrix Dashboard



### 激活

`@EnableHystrixDashboard`

```java
@SpringBootApplication
@EnableHystrixDashboard
public class SpringCloudHystrixDashboardDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudHystrixDashboardDemoApplication.class, args);
	}
}
```





## 整合 Netflix Turbine
