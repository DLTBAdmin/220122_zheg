package com.atguigu.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author dalao
 * @email dalao@gmail.com
 * @date 2022-10-10 11:46:32
 */
@Data
@TableName("sms_spu_bounds")
public class SpuBoundsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long spuId;
	/**
	 * 
	 */
	private BigDecimal growBounds;
	/**
	 * 
	 */
	private BigDecimal buyBounds;
	/**
	 * 
	 */
	private Integer work;

}
