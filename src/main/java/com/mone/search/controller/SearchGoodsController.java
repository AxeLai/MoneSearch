package com.mone.search.controller;

import com.mone.search.content.GoodsContent;
import com.mone.search.model.GoodsVo;
import com.mone.search.service.GoodsDataService;
import com.mone.search.service.SearchLogService;
import com.mone.search.util.IpUtil;
import com.mone.search.util.ResponseCode;
import com.mone.search.util.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2610:47
 */

@Api(tags = "搜索商品")
@RestController
@RequestMapping("/searchGoods")
public class SearchGoodsController {

    @Autowired
    private GoodsDataService goodsDataService;

    @Autowired
    private SearchLogService searchLogService;

    @PostMapping
    @ApiOperation(value = "搜索商品")
    public ResultResponse get(@RequestParam("key") String key, @RequestParam("userId") String userId, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows, HttpServletRequest request) {

        ResultResponse result = new ResultResponse();
        List<GoodsVo> goodsList = null;
        try {
            goodsList = goodsDataService.searchGoods(key, GoodsContent.GOODS_STATUS_IS_SALE, page, rows);

            String ip = IpUtil.getIpAddr(request);
            searchLogService.addGoodSearchLog(key, userId, ip);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setData(goodsList);
        return result.setStatus(ResponseCode.SUCCESS);

    }

    @PostMapping("/delAll")
    @ApiOperation(value = "删除所有搜索商品")
    public ResultResponse delAll(@RequestParam("token") String token) {
        boolean flag = false;
        ResultResponse result = new ResultResponse();
        if ("www.51himo.com".equals(token)) {
            flag = goodsDataService.delectAllGoods();
        }
        return result.setStatus(flag ? ResponseCode.SUCCESS : ResponseCode.ERROR);

    }

    @PostMapping("/setGoods")
    @ApiOperation(value = "根据商品编号更新商品")
    public ResultResponse setGoodsByNo(@RequestParam("goodsNo") String goodsNo) {
        ResultResponse result = new ResultResponse();
        int flag = 0;
        try {
            flag = goodsDataService.updateSearchGoods(goodsNo);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return result.setStatus(flag >= 1 ? ResponseCode.SUCCESS : ResponseCode.ERROR);

    }

    @PostMapping("/inputAll")
    @ApiOperation(value = "导入所有商品")
    public ResultResponse inputAll(@RequestParam("token") String token) {
        ResultResponse result = new ResultResponse();
        int num = 0;
        if ("www.51himo.com".equals(token)) {
            try {
                boolean flag = goodsDataService.delectAllGoods();
                if (flag) {
                    num = goodsDataService.inputGoodsToSolr();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SolrServerException e) {
                e.printStackTrace();
            }
        }
        return result.setStatus(num > 0 ? ResponseCode.SUCCESS : ResponseCode.ERROR);

    }

    @PostMapping("/hotSearch")
    @ApiOperation(value = "热门搜索")
    public ResultResponse hotSearch(@RequestParam("limit") int limit, @RequestParam("token") String token) {

        ResultResponse result = new ResultResponse();

        String _token = DigestUtils.md5Hex("mone" + limit + "city");
        System.out.println(_token);

        if (!_token.equals(token)) {
            return result.setStatus(ResponseCode.ERROR);
        }
        List goodsList = searchLogService.getHotSearchString(limit);
        result.setData(goodsList);
        return result.setStatus(ResponseCode.SUCCESS);

    }


}
