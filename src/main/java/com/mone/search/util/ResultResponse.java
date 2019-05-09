package com.mone.search.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mone.search.util.ResponseCode;

/**
 * @Author:Drossy
 * @Description
 * @Date:Created in  2019/1/7 13:48
 * @Modified By:
 */

public class ResultResponse<T> {

    /** 错误码 */
    private Integer code;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public void setData(T data) {
        this.data = data;
    }


    public ResultResponse setStatus(ResponseCode responseCode){
        this.code = responseCode.getCode();
        this.msg = responseCode.getDesc();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }
}
