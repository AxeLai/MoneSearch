package com.mone.search.dao;

import com.mone.search.model.BaseLogs;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.scheduling.annotation.Async;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/289:25
 */
@Mapper
public interface BaseLogsDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO base_logs (userId,type,content,ip,cTime) VALUES (#{userId}, #{type}, #{content}, #{ip}, #{cTime})")
    int save(BaseLogs baseLogs);

}
