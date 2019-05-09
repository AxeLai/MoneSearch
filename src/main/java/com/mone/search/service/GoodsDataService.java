package com.mone.search.service;

import com.mone.search.model.Goods;
import com.mone.search.model.GoodsLabel;
import com.mone.search.model.GoodsVo;
import com.mone.search.model.SearchLog;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2515:48
 */

public interface GoodsDataService {

    public int inputGoodsToSolr() throws IOException, SolrServerException;

    public List<GoodsVo> searchGoods(String queryString, int status, int page, int row) throws IOException, SolrServerException;

    public boolean delectAllGoods();

    public SolrInputDocument goodsToSolrDoc(Goods good);

    public int updateSearchGoods(String goodNo) throws IOException, SolrServerException;


    public List<GoodsLabel> getAllGoodsLabel();


}
