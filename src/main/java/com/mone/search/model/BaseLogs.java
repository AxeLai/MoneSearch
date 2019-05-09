package com.mone.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/3/289:23
 */
@Setter
@Getter
@ToString
public class BaseLogs {

    private Integer id;
    private String userId;
    private Integer type;
    private String content;
    private String ip;
    private Date cTime;

}
