package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1。整合mybatisPlus
 *      1）导入依赖
 *          <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.2.0</version>
 *         </dependency>
 *      2）配置
 *          1.配置数据源
 *              1）导入数据库的驱动
 *              2）在application.yml配置数据源相关信息
 *          2.配置mybatis-Plus
 *              1）使用@MapperScan
 *              2）高数MyBatis_plus，sql映射文件
 *
 * 2.逻辑删除
 *      1)配置逻辑删除的逻辑删除规则（省略）
 *      2）配置逻辑删除的组件Bean（省略）
 *      3）给Bean加上逻辑删除注解@TableLogic
 *
 * 3.JSR303
 *      1）给Bean添加校验注解：javax.validation.constraints，并定义自己的message提示
 *      2）开启校验功能@Vaild
 *          效果：校验错误以后会有默认的响应
 *      3）给校验的bean后紧跟一个BindingResult，就可以获取到校验的结果
 *      4）分组校验（多场景的复杂校验）
 *          1.给校验注解标注什么情况需要进行校验
 *          2.@Validated(AddGroup.class)
 *          3.默认没有指定分组的校验注解@NotBlank，在分组校验情况@Validated(AddGroup.class)下不生效，只会在@Validated生效
 *
 * 4.统一的异常处理
 *  @ControllerAdvice
 *      1）编写异常处理类，使用@ControllerAdvice
 *      2）使用@ExceptionHandler标注方法可以处理的异常
 *
 * 5.自定义校验
 *      1）编写一个自定义的校验注解
 *      2）编写一个自定义的校验器
 *      3）关联自定义校验器和自定义校验注解
 *          @Documented
 *          @Constraint(
 *                  validatedBy = { ListValueConstrainValidator.class 【可以适配多个不同的校验器，适配不同类型的校验】}
 *          )
 *          @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
 *          @Retention(RetentionPolicy.RUNTIME)
 *          public @interface ListValue {
 *
 *  模板引擎
 *      1）thymekeaf-starter：关闭缓存
 *      2）静态资源都放在static文件夹下就可以按照路径直接访问
 *      3）页面放在templates下，直接访问
 *          Springboot，访问项目的时候
 *      4）页面修改不重启服务器实时更新
 *          1）引入dev-tools
 *          2)修改完页面 ctrl shift f9重新自动编译下页面
 *  6.整合redis
 *      1）引入data-redis-starter
 *      2）简单配置redis的host等信息
 *      3）使用SpringBoot自动配置好的StringRedisTemplate来操作redis
 *          redis -> Map：存放数据key，数据值value
 *  7.整合redisson作为分布式锁等功能框架
 *      1）引入依赖
 *      2）配置redisson
 *  8.整合springcache简化缓存开发
 *      1)引入依赖
 *          spring-boot-starter-cache、spring-boot-starter-data-redis
 *      2)写配置
 *          1.自动配置了
 *      3)测试使用缓存
 *          1.开启缓存功能@EnableCaching
 *          2.只需要使用注解就能完成缓存操作
 */
@EnableCaching
@EnableFeignClients(basePackages = "com.atguigu.gulimall.product.feign")
@EnableDiscoveryClient
@MapperScan("com/atguigu/gulimall/product/dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
