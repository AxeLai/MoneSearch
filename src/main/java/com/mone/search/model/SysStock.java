package com.mone.search.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mone.search.util.MoneyUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Setter
@Getter
public class SysStock extends BaseEntity<Long> {

    private String goodsNo;
    private String goodsName;
    private Integer storeId;
    private Integer goodsCategory;
    private String spec;
    private String unit;
    private Integer orderAmount = 0;
    private Integer currentAmount = 0;
    private Integer lastAmount = 0;
    private Integer taxRate;
    private Long operator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operationTime;
    private Integer auditType;
    private String auditor;
    private Integer status = 0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date storageTime;
    private String spbm;
    private String username;
    private Integer price;
    private Integer pruchasingAmount;
    private Integer actualStorageAmount;

    //库存新增字段
    //是否享优惠政策
    private Integer isPolicy;
    //政策名称
    private String policyName;
    //存储条件
    private String storageCondition;
    //箱规
    private String boxGauge;
    //营业扣点
    private Integer deduction;
    //保质期
    private String qualityGuaranteePeriod;
    //开票类型
    private Integer invoiceType;
    //其它1
    private String others1 ="";
    //其它2
    private String others2 ="";
    //其它3
    private String others3 ="";
    //其它4
    private String others4 ="";
    //其它5
    private String others5 ="";


    public String getShowPrice() {
        if (getPrice() == null){
            return MoneyUtil.getCentToDollar(0);
        }
        return MoneyUtil.getCentToDollar(getPrice());
    }

    public String getShowTaxRate() {
        if (this.getTaxRate() == null || this.getTaxRate() <= 0) {
            return "0%";
        } else {
            return this.getTaxRate().doubleValue() / 100 + "%";
        }
    }

}
