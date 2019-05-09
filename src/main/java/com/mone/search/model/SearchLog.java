package com.mone.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/2714:40
 */
@Setter
@Getter
@ToString
public class SearchLog {

    public Integer id;
    private String keyWord;
    private Integer type;
    private String userId;
    private String ip;
    private Date cTime;


}
