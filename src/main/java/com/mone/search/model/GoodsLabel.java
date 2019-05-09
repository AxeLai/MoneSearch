package com.mone.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/4/1815:18
 */
@Getter
@Setter
@ToString
public class GoodsLabel {

    public String id;
    public String name;
    public String ename;
    public String price;
    public String oPrice;
    public String vPrice;
    public String product_brief;
    public String origin = ""; //产地
    public String unit; //单位
    public String specifications;

    public String barcode;
    public String qrcode;

    public String start_time;
    public String end_time;

    public boolean ispromote = false;
    public String promote_name;


}
