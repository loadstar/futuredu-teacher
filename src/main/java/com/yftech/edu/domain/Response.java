package com.yftech.edu.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/
@Data
public class Response implements Serializable {
    private int status = 0;
    private String msg = "";
    private Object data;
}
