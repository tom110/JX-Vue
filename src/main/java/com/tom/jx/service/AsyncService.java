package com.tom.jx.service;

import com.tom.jx.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Autowired
    private WebSocket webSocket;

    @Async
    public void executeAsync1(String sessionId, String send_message) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer message = Integer.valueOf(send_message)+1;
        webSocket.sendOneMessage(sessionId, message.toString());
    }

    @Async
    public void executeAsync2(String send_message) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer message = Integer.valueOf(send_message)+1;
        webSocket.sendAllMessage(message.toString());
    }

}
