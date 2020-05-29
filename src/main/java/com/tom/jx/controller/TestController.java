package com.tom.jx.controller;

import com.tom.jx.service.AsyncService;
import com.tom.jx.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("api")
public class TestController {
    @Autowired
    private WebSocket webSocket;

    @Resource
    private AsyncService asyncService;

    @RequestMapping("/sendAllWebSocket")
    public String test() {
        webSocket.sendAllMessage("清晨起来打开窗，心情美美哒~");
        return "websocket群体发送！";
    }

    @RequestMapping("/sendOneWebSocket/{sessionId}/{send_massage}")
    public String sendOneWebSocket(@PathVariable("sessionId") String sessionId,@PathVariable("send_massage") String send_message)  {
        for (int i = 0; i < 2240; i++) { //*此处线程数不能大于线程池内队列数
//            asyncService.executeAsync1(sessionId, send_message);
            asyncService.executeAsync2(send_message);
        }
        return "websocket单人发送";
    }
}
