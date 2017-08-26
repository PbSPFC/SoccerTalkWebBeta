package com.iteractionapps.soccertal.chat;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

/**
 * @author Michael Kurz
 */
@ApplicationScoped
public class ChatController implements Serializable {
    private static final String CHANNEL = "/chat";

    private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();

    public void join(String userName) {
        sendMessage(userName + " joined the chat."+ "\n\n");
    }

    public void sendMessage(String userName, String message) {
        sendMessage(userName + ": " + message + "\n\n");
    }

    public void leave(String userName) {
        sendMessage(userName + " left the chat." + "\n\n");
    }

    private void sendMessage(String message) {
        pushContext.push(CHANNEL, message);
    }
}
