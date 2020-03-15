package com.yftech.edu.controller;

import com.yftech.edu.domain.ResponseCode;
import org.springframework.util.StringUtils;

/**
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/
public class BaseController {
    public String getErrmsg(int errno) {
        return getErrmsg(errno, "");
    }

    public String getErrmsg(int errno, String defaultErrmsg) {
        String message = null;
        switch (errno) {
            case ResponseCode.SUCCESS:
                message = "OK";
                break;
            case ResponseCode.UNKONW_ERROR:
                message = "u-service.response.message.unknown_error";
                break;
        }
        return StringUtils.hasText(message) ? message : defaultErrmsg;

    }
}
