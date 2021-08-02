package com.jyd.domain.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 统一返回结果类
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 16:04
 */
@Data
public class Result implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    private Result() {

    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result msg(String msg) {
        this.setMsg(msg);
        return this;
    }

    public Result data(Object data) {
        this.setData(data);
        return this;
    }

    public static Result success() {
        Result r = new Result();
        r.code(200).msg("响应成功");
        return r;
    }

    public static Result fail() {
        Result r = new Result();
        r.code(222).msg("响应失败");
        return r;
    }
}
