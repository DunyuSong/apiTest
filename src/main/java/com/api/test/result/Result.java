package com.api.test.result;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @Description TODO
 * @Date 2020/1/7 5:57 PM
 * @Created by shayu
 */
@Slf4j
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -7517102265463124894L;

    private Integer code;

    private String msg;

    private Integer total;

    private T data;

    public Result(Integer code, String msg,Integer total,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public Result(){

    }
}
