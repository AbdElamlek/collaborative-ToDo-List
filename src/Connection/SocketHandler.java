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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author ahmedpro
 */

public class SocketHandler extends Thread {
    
    private BufferedReader input;
    private PrintStream output;
    private boolean isRuning = true;
    private String userId;
    static Vector<SocketHandler> clientsVector = new Vector<SocketHandler>();
    
    public SocketHandler(Socket socket) {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        start();
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void senJsonObj(String jsonObj){
        try {
            JSONObject jsonObject = new JSONObject(jsonObj);
            String id = jsonObject.getString("id");
            for (SocketHandler sh : clientsVector) {
                if(sh.userId.equals(id)){
                    sh.output.println(jsonObj);
                    break;
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    
    }
    
}
