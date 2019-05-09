package com.mone.search.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/4/1913:57
 */
@Getter
@Setter
@ToString
public class HotSearch {

    private String keyWord;
    private int count;

    public String getKeyWord() {
        return keyWord.replace("*","");
    }
}
