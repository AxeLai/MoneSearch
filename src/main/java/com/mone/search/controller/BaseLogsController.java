package com.mone.search.controller;

import com.mone.search.content.GoodsContent;
import com.mone.search.dao.BaseLogsDao;
import com.mone.search.model.BaseLogs;
import com.mone.search.model.GoodsVo;
import com.mone.search.service.BaseLogsService;
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
import java.util.Date;
import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/289:27
 */
@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class BaseLogsController {

    @Autowired
    private BaseLogsService baseLogsService;

    @PostMapping
    @ApiOperation(value = "记录日志")
    public ResultResponse setLog(@RequestParam("userId") String userId, @RequestParam("type") Integer type, @RequestParam("url") String url, @RequestParam("token") String token, HttpServletRequest request) {

        ResultResponse result = new ResultResponse();
        System.out.println("--------- start setLog");

        try {

            String _token = DigestUtils.md5Hex("mone" + userId + type + url + "city");
            System.out.println("mone" + userId + type + url + "city");
            System.out.println("--------- token：" + token);
            System.out.println("--------- _token：" + _token);

            if (!_token.equals(token)) {
                return result.setStatus(ResponseCode.ERROR);
            }

            BaseLogs log = new BaseLogs();

            log.setUserId(userId);
            log.setContent(url);
            log.setType(type);
            log.setCTime(new Date());

            String ip = IpUtil.getIpAddr(request);
            log.setIp(ip);

            baseLogsService.insertBaseLogs(log);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.setStatus(ResponseCode.SUCCESS);

    }

}
