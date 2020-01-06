/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import DAOController.UserController;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

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
                System.out.println("received json: "+recievedString);
                /*eman kamal*/
                handleResponse(recievedString);
                /*eman kamal*/
                isRuning = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
        /*Eman Kamal
    public void handleResponse(String jsonObjectStr) {
        UserController userController = new UserController();
        Gson gson = new Gson();
        try {
            JSONObject jsonObject = new JSONObject(jsonObjectStr);
            String action = jsonObject.getString("action");
            String userJsonObject = jsonObject.getJSONObject("entity").toString();
            UserEntity userEntity = gson.fromJson(userJsonObject, UserEntity.class);
            switch (action) {
                case "signup":
                    if (userController.insert(userEntity)) {
                        //registerd!
                        output.println(jsonObjectStr);
                    } else {
                        //Not Registered 
                    }
                    break;
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    Eman Kamal*/
    
    public void handleResponse(String jsonObjectStr){
        System.out.println("****SERVER handleResponse");
        try {
            JSONObject jsonObject = new JSONObject(jsonObjectStr);
            String className = jsonObject.getString("className");
            ActionHandler actionHandler = null;
            
            switch(className){
                case "UserEntity":
                    actionHandler = new UserActionHandler();
                    
            }
            Handler handler = new Handler(actionHandler);
            handler.handleAction(jsonObjectStr);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}

