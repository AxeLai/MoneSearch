package com.mone.search.dao;

import com.mone.search.model.HotSearch;
import com.mone.search.model.SearchLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2714:53
 */
@Mapper
public interface SearchLogDao {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO search_log (key_word,type,userId,ip,cTime) VALUES (#{keyWord}, #{type}, #{userId}, #{ip}, #{cTime})")
    int save(SearchLog searchLog);

    @Select("select key_word,count(1) count from search_log GROUP BY key_word ORDER BY count desc LIMIT 1,#{limit}")
    List<HotSearch> getHostSearchList(int limit);


}
