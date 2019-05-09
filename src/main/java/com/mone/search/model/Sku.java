package com.mone.search.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class Sku extends  BaseEntity<Long> {

    private String skuNo;

    private Long storeId;
    private String goodsNo;
    private String skuKeyValue;
    private Long originalPrice;
    private Long salePrice;
    private Long vipPrice;
    private Long isDefault;
    private String skuImg;
    private Long skuAmount;

    private Integer status;
    private Integer saleAmount;
    private Integer praiseNum;
    private Integer shareNum;
    private String stockNo;

    private Integer isLimit;
    private Integer payType;

}
