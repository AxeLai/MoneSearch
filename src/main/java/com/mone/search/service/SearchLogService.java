package com.mone.search.service;

import com.mone.search.model.HotSearch;
import com.mone.search.model.SearchLog;

import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2715:00
 */
public interface SearchLogService {

    /**
     * 搜索类型 1 商品搜搜
     */
    public static int SEARCH_LOG_TYPE_1 = 1;

    public void addGoodSearchLog(String key, String userId, String ip);

    public List<HotSearch> getHotSearchString(int limit);

}
