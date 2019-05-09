package com.mone.search;

import com.alibaba.fastjson.JSON;
import com.mone.search.dao.GoodsDao;
import com.mone.search.service.GoodsDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {

    @Autowired
    GoodsDao goodsDao;

    @Autowired
    GoodsDataService goodsDataService;

    @Test
    public void contextLoads() {

        //System.out.println(goodsDao.getAllGoods());
        List ls =     goodsDataService.getAllGoodsLabel();
        System.out.println(JSON.toJSONString(ls));
            //goodsDataService.searchGoods("亮白净色",0,0);

    }

}
