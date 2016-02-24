package com.suiyu.web.model.push.impl.wsclient;


import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

/**
 * Created by yinbing on 2/23/2016.
 */
public final class JettySession implements SessionInterface {
    private final Session session;
    public JettySession(Session session){
        this.session = session;
    }

    @Override
    public void sendText(String text) throws IOException {
        synchronized (session){
            session.getRemote().sendString(text);
        }
    }

    @Override
    public void close() throws IOException {
        synchronized (session){
            session.close();
        }
    }

    @Override
    public boolean isOpen() {
        return session.isOpen();
    }
}
