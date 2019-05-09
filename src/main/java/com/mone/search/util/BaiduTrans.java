package com.mone.search.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liming
 * @Description: TODO
 * @date 2019/4/1818:14
 */
@Getter
@Setter
public class BaiduTrans {
    String from;
    String to;
    Result trans_result[];

    public String getDsc(){
        return trans_result[0].getDst();
    }

}

@Getter
@Setter
class Result{
    String src;
    String dst;
}
