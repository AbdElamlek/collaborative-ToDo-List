/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author ahmedpro
 */
public class ServerSocketHandler extends Thread {
    
    private ServerSocket serverSocket;
    //private static Vector<SocketHandler> sockets;
    private boolean isRunning = false;
    private String userId;//userId is set upon successful login attempt or registeration

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /*public Vector<SocketHandler> getSockets() {
        return sockets;
    }*/

    /*public void setSockets(Vector<SocketHandler> sockets) {
        this.sockets = sockets;
    }*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    

    public ServerSocketHandler() {
        try {
            serverSocket = new ServerSocket(7777);
            //sockets = new Vector<>();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void startServer() {
        isRunning = true;
        start();
    }

    public void closeServer() {
        isRunning = false;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            isRunning = false;
            try {
                System.err.println("before accept");
                Socket socket = serverSocket.accept();
                new SocketHandler(socket);
                //sockets.add(new SocketHandler(socket));
                
                isRunning = true;
                System.err.println("after accept");
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
        for (SocketHandler socket: SocketHandler.socketHandlers)
            socket.closeSocket();
    }
    
}
