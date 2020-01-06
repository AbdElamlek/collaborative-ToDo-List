/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Connection.SocketHandler;
import java.net.Socket;

/**
 *
 * @author Abd-Elmalek
 */
public class SocketData {
    private String userId;
    private SocketHandler socketHandler;

    public SocketData() {
    }

    public SocketData(String userId, SocketHandler socketHandler) {
        this.userId = userId;
        this.socketHandler = socketHandler;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public SocketHandler getSocket() {
        return socketHandler;
    }

    public void setSocket(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }
    
    
}
