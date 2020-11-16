package com.api.test.common.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author xiaoyu
 * @create 2020-11-08
 * @description
 */
@Data
@ToString
public class CommonResponse{
    private String status;

    private String message;

    private Object data;

    enum ResponseStatus {
        SUCCESS("success"),
        FAILED("failed");

        String status;

        ResponseStatus(String status) {
            this.status = status;
        }

        static String success() {
            return SUCCESS.status;
        }

        static String failed() {
            return FAILED.status;
        }
    }

    public void ok() {
        this.status = ResponseStatus.success();
    }

    public void ok(Object data) {
        ok();
        this.data = data;
    }

    public void failed(String message) {
        this.status = ResponseStatus.failed();
        this.message = message;
    }

    public boolean isSuccess() {
        return this.status.equals(ResponseStatus.success());
    }
}