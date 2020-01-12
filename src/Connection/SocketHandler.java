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
import Handlers.AcceptCollaboratorRequestHandler;
import Handlers.AcceptTaskHandler;
import Handlers.AddCollaboratorRequestHandler;
import Handlers.AssignTaskHandler;
import Handlers.ItemCreationHandler;
import Handlers.ItemDeleteHandler;
import Handlers.ItemUpdateHandler;
import Handlers.LoginHandler;
import Handlers.RejectCollaboratorRequestHandler;
import Handlers.NotificationHandler;
import Handlers.SignUpHandler;
import Handlers.ToDoCreationHandler;
import Handlers.ToDoDeleteHandler;
import Handlers.ToDoUpdateHandler;
import Handlers.RejectTaskHandler;
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
    private int userId;

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
                    ((LoginHandler)actionHandler).assignUserIdToSocket(this::setUserId);
                    break;
                case "notification":
                    broadCast(jsonObjectStr);
                    actionHandler = new NotificationHandler();
                    break;
                case "create todo list":
                    actionHandler = new ToDoCreationHandler();
                    break;
                case "update todo list":
                    //broadCast(jsonObjectStr);
                    actionHandler = new ToDoUpdateHandler();
                    break;
                case "delete todo list":
                    //broadCast(jsonObjectStr);
                    actionHandler = new ToDoDeleteHandler();
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
                case "add collaborator request":
                    actionHandler = new AddCollaboratorRequestHandler();
                    break;
                case "accept collaborator request":
                    actionHandler = new AcceptCollaboratorRequestHandler();
                    break;
                case "reject collaborator request":
                    actionHandler = new RejectCollaboratorRequestHandler();
                    break;
                case "create item":
                    actionHandler = new ItemCreationHandler();
                    break;
                case "update item":
                    //broadCast(jsonObjectStr);
                    actionHandler = new ItemUpdateHandler();
                    break;
                case "delete item":
                    //broadCast(jsonObjectStr);
                    actionHandler = new ItemDeleteHandler();
                    break;
                    
            }
            actionHandler.handleAction(jsonObjectStr, output);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

    }

    /*Eman Kamal*/

    
    /*Reham*/
    public void setUserId(int id){
        this.userId = id;
        System.out.println("i have got id: " + id);
    }
    /*Reham*/

 /*abd-elamelk */
    private void broadCast(String jsonResponse) {
        for (SocketHandler socketH: socketHandlers){
            socketH.output.println(jsonResponse);
        }
    }
    /*abd-elamelk */

}
