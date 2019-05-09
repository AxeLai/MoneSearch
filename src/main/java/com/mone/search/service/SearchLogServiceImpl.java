package com.mone.search.service;

import com.mone.search.dao.SearchLogDao;
import com.mone.search.model.HotSearch;
import com.mone.search.model.SearchLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2715:01
 */
@Service
public class SearchLogServiceImpl implements SearchLogService {

    @Autowired
    private SearchLogDao searchLogDao;

    @Async
    public void addGoodSearchLog(String key, String userId, String ip) {

        SearchLog searchLog = new SearchLog();
        searchLog.setKeyWord(key.replace("*", ""));
        searchLog.setUserId(userId);
        searchLog.setIp(ip);
        searchLog.setType(SEARCH_LOG_TYPE_1);
        searchLog.setCTime(new Date());
        searchLogDao.save(searchLog);

    }

    public List<HotSearch> getHotSearchString(int limit) {
        return searchLogDao.getHostSearchList(limit);
    }

}
