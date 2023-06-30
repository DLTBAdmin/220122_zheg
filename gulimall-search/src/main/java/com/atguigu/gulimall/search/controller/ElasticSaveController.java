package com.atguigu.gulimall.search.controller;

import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.search.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/search/save")
@RestController
public class ElasticSaveController {

    @Resource
    ProductService productService;

    //上架商品
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels){
        boolean b = false;
        try {
            b = productService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            log.error("ElasticSaveController商品上架错误：{}");
            return R.error(BizCodeEnum.PEODUCT_UP_EXCEPTON.getCode(),BizCodeEnum.PEODUCT_UP_EXCEPTON.getMsg());
        }

        if (!b){
            return R.ok();
        }else {
            return R.error(BizCodeEnum.PEODUCT_UP_EXCEPTON.getCode(),BizCodeEnum.PEODUCT_UP_EXCEPTON.getMsg());
        }
    }
}
