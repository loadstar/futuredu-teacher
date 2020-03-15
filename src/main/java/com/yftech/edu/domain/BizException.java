package com.yftech.edu.domain;

/**
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/
public class BizException extends BaseException {

    public BizException(int status, String msg) {
    super(status, msg);
}

    public BizException(Throwable cause, int status, String msg) {
        super(cause, status, msg);
    }

}
