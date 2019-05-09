package com.mone.search.model;

import com.mone.search.util.MoneyUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2610:23
 */

@Getter
@Setter
@ToString
public class GoodsVo {

    private String id;

    private String goodsNo;
    private String goodsName;
    private String highlightGoodsName;


    private String goodsTitle;
    private String imgName;

    private Integer categoryId;
    private String categoryPath;

    private Integer saleAmount;
    private Integer skuAmount;

    private Integer originalPrice;
    private Integer salePrice;
    private Integer vipPrice;

    private Integer payType;
    private Integer isLimit;

    private String defaultSkuNo;

    public String getViewOriginalPrice() {

        return originalPrice == null ? "0" : MoneyUtil.getCentToDollar(originalPrice);
    }

    public String getViewSalePrice() {
        return salePrice == null ? "0" : MoneyUtil.getCentToDollar(salePrice);
    }

    public String getViewVipPrice() {
        return vipPrice == null ? "0" : MoneyUtil.getCentToDollar(vipPrice);
    }

}
