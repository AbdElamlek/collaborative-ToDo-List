
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 
 * @author ahmedpro
 */

public class SocketHandler extends Thread {
    
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private boolean isRuning = true;
    
    public SocketHandler(Socket socket) {
        try {
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            output = new PrintStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        start();
    }
    
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while (isRuning) {
            isRuning = false;
            try {
                
                String recievedString = input.readLine();
                System.out.println(input.readLine());
                isRuning = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
}
