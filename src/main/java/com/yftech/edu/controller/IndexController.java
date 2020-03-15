package com.yftech.edu.controller;

import com.yftech.edu.domain.BizException;
import com.yftech.edu.domain.Response;
import com.yftech.edu.domain.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: andrewzhang
 * @data: 2020/3/15
 * @description:
 **/

@RestController
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping("/index")
    public String index() {
        return "Hello teacher-service";
    }

    @RequestMapping("/")
    String home() {
        return "HOME: Hello teacher-service!!";
    }

    //@RequestMapping(value = "/xxx/yyy", method = RequestMethod.GET)
    public Response index2(){
        Response response = new Response();;
        try{
            //response.setData(costEstService.***);
            response.setStatus(ResponseCode.SUCCESS);
            response.setMsg(getErrmsg(ResponseCode.SUCCESS));
        }catch (BizException e){
            //logger.info(e.toString(), e);
            response.setStatus(e.getStatus());
            response.setMsg(e.getMsg());
        } catch (Exception e){
            //logger.error("unknown server exception.", e);
            response.setStatus(ResponseCode.UNKONW_ERROR);
            response.setMsg(getErrmsg(ResponseCode.UNKONW_ERROR));
        }
        return response;
    }
}
