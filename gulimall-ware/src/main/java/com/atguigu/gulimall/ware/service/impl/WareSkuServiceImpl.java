package com.atguigu.gulimall.ware.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.common.utils.R;
import com.atguigu.gulimall.ware.dao.WareSkuDao;
import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.atguigu.gulimall.ware.feign.ProductFeignService;
import com.atguigu.gulimall.ware.service.WareSkuService;
import com.atguigu.gulimall.ware.vo.SkuHasStockVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Resource
    ProductFeignService productFeignService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        /**
         * skuId : 1
         * wareId : 2
         */
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        String skuId = (String) params.get("skuId");
        if (!StringUtils.isEmpty(skuId)){
            queryWrapper.eq("sku_id", skuId);
        }
        String wareId = (String) params.get("wareId");
        if (!StringUtils.isEmpty(wareId)){
            queryWrapper.eq("ware_id", wareId);
        }


        IPage<WareSkuEntity> page = this.page(
                new Query<WareSkuEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        //1.判断如果还没这个库存记录新增
        List<WareSkuEntity> entities = this.baseMapper.selectList(new QueryWrapper<WareSkuEntity>().eq("sku_id", skuId).eq("ware_id", wareId));
        if (entities.size() == 0 || entities == null){
            WareSkuEntity skuEntity = new WareSkuEntity();
            skuEntity.setSkuId(skuId);
            skuEntity.setWareId(wareId);
            skuEntity.setStock(skuNum);
            skuEntity.setStockLocked(0);
            //TODO 远程擦汗寻sku的名字,如果失败，整个事务不需要回滚
            //TODO 还可以用办法让异常出现以后不会滚， 高级
            try {
                R info = productFeignService.info(skuId);
                if (info.getCode() == 0){
                    Map<String, Object> data = (Map<String, Object>) info.get("skuInfo");
                    skuEntity.setSkuName((String) data.get("skuName"));
                }
            }catch (Exception e){

            }

            this.baseMapper.insert(skuEntity);
        }else {
            this.baseMapper.addStock(skuId, wareId, skuNum);
        }
    }

    @Override
    public List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds) {

        List<SkuHasStockVo> collect = skuIds.stream().map(skuId -> {
            SkuHasStockVo vo = new SkuHasStockVo();

            //查询当前sku的总库存量
            //SELECT SUM(stock - stock_locked) FROM 'wms_ware_sku' WHERE sku_id = 1
            Long count = this.baseMapper.getSkuStock(skuId);
            vo.setSkuId(skuId);
            vo.setHasStock(count==0?false:count>0);
            return vo;
        }).collect(Collectors.toList());
        return collect;
    }

}