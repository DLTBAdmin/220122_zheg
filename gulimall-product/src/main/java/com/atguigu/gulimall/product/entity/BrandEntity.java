package com.atguigu.gulimall.product.entity;

import com.atguigu.common.valid.AddGroup;
import com.atguigu.common.valid.ListValue;
import com.atguigu.common.valid.UpdateGroup;
import com.atguigu.common.valid.UpdateStatusGroup;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Ʒ
 *
 * @author dalao
 * @email dalao@gmail.com
 * @date 2022-10-10 11:59:02
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Ʒ
	 */
	@NotNull(message = "修改必须指定品牌id", groups = {UpdateGroup.class})
	@Null(message = "新增不能指定id", groups = {AddGroup.class})
	@TableId
	private Long brandId;
	/**
	 * Ʒ
	 */
	@NotBlank(message = "品牌名必须提交", groups = {UpdateGroup.class, AddGroup.class})
	private String name;
	/**
	 * Ʒ
	 */
	@NotEmpty(groups = {AddGroup.class})
	@URL(message = "logo必须是一个合法的url地址", groups = {UpdateGroup.class, AddGroup.class})
	private String logo;
	/**
	 *
	 */
	private String descript;
	/**
	 *
	 */
	@NotNull(groups = {UpdateStatusGroup.class})
	@ListValue(vals={0,1}, groups = {AddGroup.class, UpdateStatusGroup.class})
	private Integer showStatus;
	/**
	 *
	 */

	@NotEmpty(groups = {AddGroup.class})
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母", groups = {UpdateGroup.class})
	private String firstLetter;
	/**
	 *
	 */
	@NotNull(groups = {AddGroup.class})
	@Min(value = 0, message = "排序必须大于等于0", groups = {UpdateGroup.class})
	private Integer sort;

}
