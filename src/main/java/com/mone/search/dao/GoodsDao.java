package com.mone.search.dao;

import com.mone.search.model.Goods;
import com.mone.search.model.Sku;
import com.mone.search.model.SysStock;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2515:49
 */
@Mapper
public interface GoodsDao {


    @Select("SELECT * FROM new_goods")
    @Results({
            @Result(column = "goods_no", property = "goodsNo"),
            @Result(column = "goods_no", property = "skus", many = @Many(
                    select = "com.mone.search.dao.GoodsDao.getSkuListByGoodsNo", fetchType = FetchType.LAZY
            ))
    })
    public List<Goods> getAllGoods();

    @Select("SELECT * FROM new_goods")
    @Results({
            @Result(column = "goods_no", property = "goodsNo"),
            @Result(column = "goods_no", property = "sysStock" ,one = @One(
                    select = "com.mone.search.dao.GoodsDao.getSysStockByGoodsNo", fetchType = FetchType.LAZY
            )),
            @Result(column = "goods_no", property = "skus", many = @Many(
                    select = "com.mone.search.dao.GoodsDao.getSkuListByGoodsNo", fetchType = FetchType.LAZY
            ))
    })
    public List<Goods> getAllGoodsAndStock();

    @Select("SELECT * FROM new_goods WHERE goods_no = #{goodsNo}")
    @Results({
            @Result(column = "goods_no", property = "goodsNo"),
            @Result(column = "goods_no", property = "skus", many = @Many(
                    select = "com.mone.search.dao.GoodsDao.getSkuListByGoodsNo", fetchType = FetchType.LAZY
            ))
    })
    public Goods getGoodsByNo(String goodsNo);

    @Select("SELECT * FROM sku WHERE goods_no = #{goodsNo}")
    public List<Sku> getSkuListByGoodsNo(String goodsNo);

    @Select("SELECT * FROM sys_stock WHERE goods_no = #{goodsNo}")
    public SysStock getSysStockByGoodsNo(String goodsNo);

}
