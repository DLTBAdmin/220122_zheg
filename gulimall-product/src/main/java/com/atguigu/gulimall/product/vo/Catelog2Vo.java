package com.atguigu.gulimall.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//2级分类
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Catelog2Vo {
    private String catalogId;
    private List<Catalog3Vo> catalog3List;
    private String id;
    private String name;

    //3级分类vo
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Catalog3Vo{
        private String catalog2Id;
        private String id;
        private String name;
    }
}
