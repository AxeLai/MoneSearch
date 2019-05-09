package com.mone.search.receiver;

import com.mone.search.service.GoodsDataService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2814:23
 */
public class GoodsReceiver {

    private CountDownLatch latch;

    @Autowired
    private GoodsDataService goodsDataService;


    @Autowired
    public GoodsReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMassage(String msg) {

        int flag = 0;
        try {
            flag = goodsDataService.updateSearchGoods(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }

        System.out.println("receiveMassage:" + flag);
    }

}
