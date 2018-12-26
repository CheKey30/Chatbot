package com.octopedia.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket", configurator= MyEndpointConfigure.class)
@Component
public class MyWebSocket {
    @Autowired
    private ForPython forPython;
    //online number
    private static int onlineAccount = 0;

    // websocket for each customer
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();

    //session
    private Session session;

    // after successful connection
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        onlineAccount+=1;
        System.out.println("New account, now have" + onlineAccount);
        try {
            sendMessage("Can I help you?");
        } catch (IOException e) {
            System.out.println("IO exception");
        }
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        onlineAccount-=1;
        System.out.println("One account left, now have" +onlineAccount);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("From account message:"+message);
        try {
            String answer = forPython.answer(message);
            System.out.println(answer);
            sendMessage(answer);
        }catch (Exception e){
            System.out.println("onMessage exception");
        }
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
