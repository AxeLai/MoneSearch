package com.mone.search.service;

import com.alibaba.fastjson.JSON;
import com.mone.search.dao.GoodsDao;
import com.mone.search.model.Goods;
import com.mone.search.model.GoodsLabel;
import com.mone.search.model.GoodsVo;
import com.mone.search.model.Sku;
import com.mone.search.util.BaiduTrans;
import com.mone.search.util.MoneyUtil;
import com.mone.search.util.baidu.TransApi;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.*;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2517:30
 */
@Service
public class GoodsDataServiceImpl implements GoodsDataService {

    //<delete><query>*:*</query></delete><commit/>

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SolrClient solrClient;


    public int inputGoodsToSolr() throws IOException, SolrServerException {

        List<Goods> goodsList = goodsDao.getAllGoods();

        int result = 0;
        if (goodsList != null && goodsList.size() > 0) {

            for (Goods good : goodsList) {

                SolrInputDocument doc = goodsToSolrDoc(good);
                if (doc != null) {
                    result++;
                    solrClient.add(doc);
                }

            }
            if (result > 0) {
                solrClient.commit();
            }
        }

        return result;
    }

    public List<GoodsVo> searchGoods(String queryString, int status, int page, int rows) throws IOException, SolrServerException {

        SolrQuery query = new SolrQuery();

        queryString = queryString.replace("*", "");
        if (queryString.length() == 1) {
            queryString = "*" + queryString + "*";
        }

        String q = "goodsName:" + queryString + " AND status:" + status;

        query.setQuery(q);
        page = page == 0 ? 1 : page;
        if (rows != 0) {
            query.setStart((page - 1) * rows);
            query.setRows(rows);
        }
        //query.set("df", "goodsName");
        //query.set("df", "goodsTitle");

        query.setHighlight(true);
        // 添加高亮字段，多个字段之间逗号隔开比如: A,B,C
        query.addHighlightField("goodsName");
        // 设置高亮字段的前缀
        query.setHighlightSimplePre("<font color='#ff7200'>");
        // 设置高亮字段的后缀
        query.setHighlightSimplePost("</font>");

        System.out.println("----------------------------" + queryString + "--------------------------------");
        System.out.println(queryString.length());

        QueryResponse response = solrClient.query(query);

        SolrDocumentList docs = response.getResults();

        Map<String, Map<String, List<String>>> maplist = response.getHighlighting();


        List<GoodsVo> goodsList = new ArrayList();

        for (SolrDocument solrDocument : docs) {

            String id = solrDocument.getFirstValue("id").toString();
            String goodsNo = solrDocument.getFirstValue("goodsNo").toString();
            String goodsName = solrDocument.getFirstValue("goodsName").toString();


            Map<String, List<String>> fieldMap = maplist.get(solrDocument.get("id"));
            List<String> stringlist = fieldMap.get("goodsName");
            String hgoodsName = goodsName;
            if (stringlist != null && stringlist.size() > 0) {
                hgoodsName = stringlist.get(0);
            }
            String goodsTitle = solrDocument.getFirstValue("goodsTitle").toString();

            String imgName = solrDocument.getFirstValue("imgName") == null ? "" : solrDocument.getFirstValue("imgName").toString();
            Integer categoryId = Integer.parseInt(solrDocument.getFirstValue("categoryId").toString());
            String categoryPath = solrDocument.getFirstValue("categoryPath").toString();

            Integer saleAmount = Integer.parseInt(solrDocument.getFirstValue("saleAmount").toString());
            Integer skuAmount = Integer.parseInt(solrDocument.getFirstValue("skuAmount").toString());

            Integer originalPrice = Integer.parseInt(solrDocument.getFirstValue("originalPrice").toString());
            Integer salePrice = Integer.parseInt(solrDocument.getFirstValue("salePrice").toString());
            Integer vipPrice = Integer.parseInt(solrDocument.getFirstValue("vipPrice").toString());

            Integer payType = Integer.parseInt(solrDocument.getFirstValue("payType").toString());
            Integer isLimit = Integer.parseInt(solrDocument.getFirstValue("isLimit").toString());

            String defaultSkuNo = solrDocument.getFirstValue("defaultSkuNo").toString();

            GoodsVo good = new GoodsVo();
            good.setId(id);
            good.setGoodsNo(goodsNo);
            good.setGoodsName(goodsName);
            good.setHighlightGoodsName(hgoodsName);
            good.setGoodsTitle(goodsTitle);
            good.setImgName(imgName);

            good.setCategoryId(categoryId);
            good.setCategoryPath(categoryPath);
            good.setSaleAmount(saleAmount);
            good.setSkuAmount(skuAmount);
            good.setOriginalPrice(originalPrice);
            good.setSalePrice(salePrice);
            good.setVipPrice(vipPrice);

            good.setPayType(payType);
            good.setIsLimit(isLimit);
            good.setDefaultSkuNo(defaultSkuNo);

            goodsList.add(good);

        }

        return goodsList;
    }

    @Override
    public boolean delectAllGoods() {
        try {
            solrClient.deleteByQuery("*:*");

            solrClient.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public SolrInputDocument goodsToSolrDoc(Goods good) {

        SolrInputDocument doc = new SolrInputDocument();

        List<Sku> skuList = good.getSkus();

        if (skuList == null || skuList.size() <= 0) {
            return null;
        }

        doc.setField("id", good.getGoodsNo());
        doc.setField("goodsNo", good.getGoodsNo());
        doc.setField("goodsName", good.getGoodsName());
        doc.setField("goodsTitle", good.getGoodsTitle());
        doc.setField("imgName", good.getImgName());

        doc.setField("categoryId", good.getCategoryId());
        doc.setField("categoryPath", good.getCategoryPath());


        int originalPrice = 0;
        int salePrice = 0;
        int vipPrice = 0;
        int saleAmount = 0;
        int skuAmount = 0;
        int isLimit = 0;
        int payType = 0;
        int defaultSkuNo = 0;

        for (Sku sku : skuList) {
            if (originalPrice == 0 || originalPrice < sku.getOriginalPrice()) {
                originalPrice = sku.getOriginalPrice().intValue();
            }
            if (salePrice == 0 || salePrice < sku.getSalePrice()) {
                salePrice = sku.getSalePrice().intValue();
            }
            if (vipPrice == 0 || vipPrice < sku.getVipPrice()) {
                vipPrice = sku.getVipPrice().intValue();
            }
            saleAmount += sku.getSaleAmount();
            skuAmount += sku.getSkuAmount();
            isLimit = sku.getIsLimit();
            payType = sku.getPayType();
            defaultSkuNo = sku.getId().intValue();

        }


        doc.setField("saleAmount", saleAmount);
        doc.setField("skuAmount", skuAmount);
        doc.setField("originalPrice", originalPrice);
        doc.setField("salePrice", salePrice);
        doc.setField("vipPrice", vipPrice);
        doc.setField("isLimit", isLimit);
        doc.setField("payType", payType);
        doc.setField("defaultSkuNo", defaultSkuNo);

        doc.setField("status", good.getStatus());

        return doc;
    }

    @Override
    public int updateSearchGoods(String goodNo) throws IOException, SolrServerException {
        Goods good = goodsDao.getGoodsByNo(goodNo);
        if (good != null) {
            SolrInputDocument doc = goodsToSolrDoc(good);
            if (doc != null) {
                solrClient.add(doc);
                solrClient.commit();
                return 1;
            }
        }
        return 0;
    }

    @Override
    public List<GoodsLabel> getAllGoodsLabel() {

        List<Goods> goodsList = goodsDao.getAllGoodsAndStock();

        List<GoodsLabel> ls = new ArrayList<>();

        for (Goods good : goodsList) {

            GoodsLabel gl = new GoodsLabel();

            gl.setId(good.getGoodsNo());
            gl.setName(good.getGoodsName());
            gl.setProduct_brief(good.getGoodsTitle());

            List<Sku> skus = good.getSkus();
            Sku sku = skus.get(0);

            gl.setPrice(MoneyUtil.getCentToDollar(sku.getSalePrice()));
            gl.setVPrice(MoneyUtil.getCentToDollar(sku.getVipPrice()));
            gl.setOPrice(MoneyUtil.getCentToDollar(sku.getOriginalPrice()));


            gl.setSpecifications(good.getSysStock().getSpec());
            gl.setUnit(good.getSysStock().getUnit());

            gl.setQrcode("http://www.51himo.com/#/goodsDetail/" + good.getGoodsNo());

            Calendar cal = Calendar.getInstance();
            String d = DateFormatUtils.format(cal, "yyyy-MM-dd HH:mm:ss", TimeZone.getDefault());
            gl.setStart_time(d);
            gl.setEnd_time(d);

            TransApi api = new TransApi();
            BaiduTrans b = JSON.parseObject(api.getTransResult(gl.getName(), "auto", "en"), BaiduTrans.class);
            gl.setEname(b.getDsc());

            //11位码
            String str="0123456789";
            StringBuilder sb=new StringBuilder(11);
            for(int i=0;i<11;i++)
            {
                char ch=str.charAt(new Random().nextInt(str.length()));
                sb.append(ch);
            }
            gl.setBarcode(sb.toString());

            ls.add(gl);


        }

        return ls;

    }
}
