/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import ControllerBase.ActionHandler;
import DAOController.UserController;
import Entities.NotificationEntity;
import Entities.UserEntity;
import Handlers.AcceptTaskHandler;
import Handlers.AssignTaskHandler;
import Handlers.LoginHandler;
import Handlers.RejectTaskHandler;
import Handlers.SignUpHandler;
import Handlers.ToDoCreationHandler;
import Handlers.UpdateTaskStatusHandler;
import Handlers.withdrawFromTaskHandler;
import com.google.gson.Gson;
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

    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private boolean isRuning = true;
    private static Vector<SocketHandler> socketHandlers = new Vector<>();

    public SocketHandler(Socket socket) {
        try {
            this.socket = socket;
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            output = new PrintStream(this.socket.getOutputStream());
            socketHandlers.add(this);
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

                try {
                    JSONObject jsonObject = new JSONObject(recievedString);
                    String action = jsonObject.getString("action");
                    if (action.equals("notification")) {
                         broadCastNotification(recievedString);
                            
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }

                /*eman kamal*/

                handleResponse(recievedString);

                /*eman kamal*/
                isRuning = true;
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }
    
        /*Eman Kamal*/

    public void handleResponse(String jsonObjectStr) {
        try {
           
            ActionHandler actionHandler = null;
            
            JSONObject jsonObject = new JSONObject(jsonObjectStr);
            String action = jsonObject.getString("action");
          
            switch (action) {
                case "signup":
                    actionHandler = new SignUpHandler();
                    break;
                case "logIn":
                    actionHandler = new LoginHandler();
                    break;
                case "create todo list":
                    actionHandler = new ToDoCreationHandler();
                    break;
                case "assigonToTaskRequest":
                    actionHandler = new AssignTaskHandler();
                    break;
                case "changeTaskStatus":
                    actionHandler = new UpdateTaskStatusHandler();
                    break;
                case "acceptTask":
                    actionHandler = new AcceptTaskHandler();
                    break;   
                case "rejectTaskRequest":
                    actionHandler = new RejectTaskHandler();
                    break;        
                case "withdrawFromTask":
                    actionHandler = new withdrawFromTaskHandler();
                    break;            
                    
                    
            }
            actionHandler.handleAction(jsonObjectStr, output);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
       
    }
    /*Eman Kamal*/
    
    /*public void handleResponse(String jsonObjectStr){
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
    }*/



 /*abd-elamelk */
    private void broadCastNotification(String jsonResponse) {
        for (SocketHandler socketH: socketHandlers){
            socketH.output.println(jsonResponse);
        }
    }   
    /*abd-elamelk */

}
