package com.mone.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2515:51
 */
@Getter
@Setter
@ToString
public class Goods extends BaseEntity<Long> {

    private String goodsName;
    private String goodsNo;
    private Integer categoryId;
    private String spuNo;
    private Integer storeId;
    private String tag;
    private String descript;
    private String goodsKeyValue;
    private String imgName;
    private String imgColor;
    private Integer price;
    private String deliveryProvinces;
    private String freightTemplateNo;
    private String interfaceTemplateNo;
    private Date upTime;
    private Date downTime;
    private Integer currentAmount;
    private Integer totalAmount;
    private Integer status;
    private Date lastUpdateTime;
    private String goodsTitle;
    private String categoryPath;

    private SysStock sysStock;

    private List<Sku> skus;


}
