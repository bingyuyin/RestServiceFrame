package com.suiyu.web.model.push.impl.wsclient;

import java.io.IOException;

/**
 * Created by yinbing on 2/23/2016.
 */
public interface SessionInterface {
    void sendText(String text) throws IOException;
    void close() throws IOException;
    boolean isOpen();
}
