package com.mone.search.service;

import com.mone.search.dao.BaseLogsDao;
import com.mone.search.model.BaseLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/289:42
 */
@Service
public class BaseLogsServiceImpl implements  BaseLogsService {

    @Autowired
    private BaseLogsDao baseLogsDao;

    @Override
    @Async
    public int insertBaseLogs(BaseLogs baseLogs) {
        return baseLogsDao.save(baseLogs);
    }
}
