package com.atguigu.gulimall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装页面所有可能传递过来的查询条件
 */
@Data
public class SearchParam {

    private String keyword;//页面传递过来的全文匹配
    private Long catalog3Id;//三级分类id

    /**
     * sort=saleCount_asc/desc
     * sort=skuPrice
     * sort=hostCount_asc/desc
     */
    private String sort;//排序条件

    /**
     * 过滤条件
     * hasStock（是否有货）、skuPrice（价格区间）、brandId、catalog3Id、attrs
     * hasStock=1/0
     * skuPrice=0_500/_500/500_
     * brandId=1
     */
    private Integer hasStock;//是否有货
    private String skuPrice;//价格区间
    private List<Long> brandId;//品牌id,可以多选
    private List<String> attrs;//按照属性进行筛选
    private Integer pageNum = 1;//页码

    private String _queryString;//原生的所有查询条件
}
