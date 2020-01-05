/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Vector;

/**
 *
 * @author ahmedpro
 */
public class ServerSocketHandler extends Thread {
    
    private ServerSocket serverSocket;
    private Vector<SocketHandler> sockets;
    private boolean isRunning = false;

    public ServerSocketHandler() {
        try {
            serverSocket = new ServerSocket(7777);
            sockets = new Vector<>();
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
                sockets.add(new SocketHandler(serverSocket.accept()));
                isRunning = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        
        for (SocketHandler socket: sockets)
            socket.closeSocket();
    }
    
}
