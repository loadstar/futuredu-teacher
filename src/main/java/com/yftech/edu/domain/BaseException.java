package com.yftech.edu.domain;

import lombok.Data;

/**
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/
@Data
public class BaseException extends RuntimeException {
    private int status;
    private String msg;

    public BaseException( int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BaseException(Throwable cause, int status, String msg) {
        super(msg, cause);
        this.status = status;
        this.msg = msg;
    }
}
