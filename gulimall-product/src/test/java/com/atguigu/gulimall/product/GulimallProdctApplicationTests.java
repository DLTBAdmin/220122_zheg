package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.atguigu.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;

/**
 * 1.引入oss-starter
 * 2.配置key，endpoint相关信息即可
 * 3.使用OSSClient进行相关操作
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProdctApplicationTests {

    @Resource
    BrandService brandService;

    @Resource
    CategoryService categoryService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void testRedisson(){
        System.out.println(redissonClient);
    }

    @Test
    public void testStringRedisTemplate(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //保存
        ops.set("hello", "world_" + UUID.randomUUID().toString());

        //查询
        String hello = ops.get("hello");
        System.out.println("保存的数据是: " + hello);

    }

    @Test
    public void testFindPath(){
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径：{}", Arrays.asList(catelogPath));
    }

    @Test
    public void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();

//        brandEntity.setName("华为");
//        brandService.save(brandEntity);
//
//        System.out.println("保存成功");


    }

}
