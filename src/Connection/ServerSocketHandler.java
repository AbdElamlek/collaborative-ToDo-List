/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author ahmedpro
 */
public class ServerSocketHandler extends Thread {
    
    private ServerSocket serverSocket;
    private boolean isRunning = false;

    public ServerSocketHandler() {
        try {
            serverSocket = new ServerSocket(7777);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void startServer() {
        isRunning = true;
        start();
    }

    public void stopServer() {
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
                new SocketHandler(serverSocket.accept());
                isRunning = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
}
