package com.iteractionapps.soccertal.chat;

import com.iteractionapps.soccertalk.security.Seguranca;
import org.primefaces.context.RequestContext;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Michael Kurz
 */
@SessionScoped
@Named
public class ChatPage implements Serializable {
    @Inject
    private ChatUsers chatUsers;
    @Inject
    private ChatController chatController;
    @Inject
    private MessageHelper msgHelper;
    @Inject
    private Seguranca seguranca;
    
    @NotNull @Size(min = 3)
    private String userName;
    private boolean loggedIn;
    private String message;

    public void login() {
        
        userName = seguranca.getNomeUsuario() + " [" + seguranca.getTimeSigla() + "]";
        
        if (chatUsers.login(userName)) {
            loggedIn = true;
            chatController.join(userName);
            RequestContext.getCurrentInstance().execute("subscriber.connect()");
        } else {
            msgHelper.addErrorMessage("User name exists");
        }
    }

    public void logout() {
        chatUsers.logout(userName);
        chatController.leave(userName);
        loggedIn = false;
        userName = null;
    }

    public void sendMessage() {
        chatController.sendMessage(userName, message);
        message = null;
    }

    // Getters and setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
