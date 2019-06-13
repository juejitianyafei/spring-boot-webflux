package com.ty.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author wangfei
 * @date 2019/6/5 13:36
 */
@Controller
@RequestMapping("")
public class WebSocketController {

    public static final String WEBSOCKET_PAGE = "websocket-client";

    @GetMapping("/page/websocket")
    public String toWebsocket() {
        return WEBSOCKET_PAGE;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Object pushToWeb(@PathVariable String cid,String message) {
        try {
            WebSocketServer.sendInfo(message,cid);
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "SUCCESS";
    }
}
