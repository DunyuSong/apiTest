package com.api.test.result;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.List;


@Slf4j
public class ResultUtil {

    /**成功且带数据,**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        result.setData(object);
        if (object instanceof List) {
            result.setTotal(((List) object).size());
        }
        if (object.getClass().isArray()) {
            result.setTotal(Array.getLength(object));
        }
        return result;
    }
    /**成功但不带数据,直接返回成功信息**/
    public static Result success(){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        return result;
    }
    /**失败**/
    public static Result fail(Integer code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }


    /**失败**/
    public static Result fail(Integer code){
        Result result = new Result();
        result.setCode(code);
        return result;
    }

    /**失败**/
    public static Result fail(String  message){
        Result result = new Result();
        result.setMsg(message);
        return result;
    }

}
