package com.jyd.controller;

import com.jyd.domain.web.Result;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 16:02
 */
public class BaseController {

    public Result ok() {
        return Result.success();
    }

    public Result fail() {
        return Result.fail();
    }

}
