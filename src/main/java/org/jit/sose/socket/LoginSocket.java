package org.jit.sose.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/socketLogin/{socketKey}")
public class LoginSocket {

//    private static Logger log= LoggerFactory.getLogger(LoginSocket.class);

    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("socketKey")String socketKey) {
        this.session=session;
        log.info("[微信小程序websocket]socketKey:{}",socketKey +"-->建立连接");
        sessionMap.put(socketKey,session);
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("socketKey") String socketKey) {
        log.info("[微信小程序websocket]socketKey:{}",socketKey +"-->断开连接");
        sessionMap.remove(socketKey);
    }

    public ConcurrentHashMap<String, Session> getSessionMap() {

        return sessionMap;
    }
}
