package com.tom.jx.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
@ServerEndpoint(value = "/websocket/{sessionId}")
//此注解相当于设置访问URL
public class WebSocket {

    private Session session;


    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "sessionId") String sessionId) throws InterruptedException {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(sessionId, session);
        System.out.println("【websocket消息】有新的连接，总数为:" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    @OnMessage
    public void onMessage(Integer message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                synchronized (webSocket) {
                    webSocket.session.getBasicRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String sessionId, String message) {
        Session session_local = sessionPool.get(sessionId);

        if (session_local != null) {
            try {
                synchronized (session_local) {
                    session_local.getBasicRemote().sendText(message);
//                    System.out.println(session_local);
                }

            } catch (Exception e) {
//                    e.printStackTrace();
                System.out.println(e);
            }
        }

    }

}
