package com.atguigu.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 如何使用nacos作为配置中心统一管理配置
 *      1.引入依赖
 *          <!--配置中心来做配置管理-->
 *             <dependency>
 *                 <groupId>com.alibaba.cloud</groupId>
 *                 <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *             </dependency>
 *      2.创建一个bootstrap.properties
 *          spring.application.name=gulimall-coupon
 *          spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 *      3.需要给配置中心默认添加一个 数据集 bootstrapProperties-gulimall-coupon.properties。 默认规则，应用名
 *      4.给 应用名.properties 添加任何配置
 *      5.动态获取配置
 *          @RefreshScope 动态获取并刷新配置
 *          @Value(${配置的名}) 获取到配置中心
 *          如果配置中心和但钱引用的配置文件都配置了相同的项，有限使用配置中心的配置
 *
 * 细节
 *      1.命名空间：配置隔离
 *          默认：public（保留空间）；默认新增的所有配置都在public空间
 *          1）开发，测试，生产
 *          注意：在bootstrap.properties；配置上，需要使用那个命名空间下的配置
 *          spring.cloud.nacos.config.namespace=b14532f9-2529-4aab-94b1-1b4b1e250ae2
 *          2)每一个微服务之间互相隔离配置，每一个微服务都船舰自己的命名空间
 *      2.配置集:所有的配置的集合
 *      3.配置集id：类似文件名
 *          Data ID：类似文件名
 *      4.配置分组：
 *          默认所有的配置集都属于：DEFAULT_GROUP
 *          111，618，1212
 *
 * 每个微服务创建自己的命名空间，使用配置分组区分环境,dev,test,prod
 *
 * 同时加载多个配置集
 *      1.微服务任何配置信息，任何配置文件都可以凡放在配置中心中
 *      2.只需要在bootstrap.properties说明加载配置中心中那些配置文件即可
 *      3.@Value，@ConfigurationProperties
 *      以前springboot任何方法从配置文件中获取值，都可以获取
 *      配置中心有的有限使用配置中心的
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
