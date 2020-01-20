/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import ControllerBase.ActionHandler;
import Handlers.AcceptCollaboratorRequestHandler;
import Handlers.AcceptFriendHandler;
import Handlers.AcceptTaskHandler;
import Handlers.AddCollaboratorRequestHandler;
import Handlers.AddFriendHandler;
import Handlers.AssignTaskHandler;
import Handlers.DeclineFriendHandler;
import Handlers.CommentCreationHandler;
import Handlers.ItemCreationHandler;
import Handlers.ItemDeleteHandler;
import Handlers.ItemUpdateHandler;
import Handlers.FriendHandler;
import Handlers.LoginHandler;
import Handlers.RejectCollaboratorRequestHandler;
import Handlers.NotificationHandler;
import Handlers.SignUpHandler;
import Handlers.ToDoCreationHandler;
import Handlers.ToDoDeleteHandler;
import Handlers.ToDoUpdateHandler;
import Handlers.RejectTaskHandler;
import Handlers.SearchFriendHandler;
import Handlers.TaskCreationHandler;
import Handlers.TaskDeleteHandler;
import Handlers.UpdateTaskStatusHandler;
import Handlers.WithdrawFromTaskHandler;
import Handlers.LogoutHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
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
    public static Vector<SocketHandler> socketHandlers = new Vector<>();
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

                isRuning = true;

                System.out.println("received json: "+recievedString);

                /*eman kamal*/
                
                handleResponse(recievedString);
                
                /*if(recievedString != null){
                    handleResponse(recievedString);
                    isRuning = true;
                }
                else{
                    socketHandlers.remove(this);
                    isRuning = false;

                }*/
                /*eman kamal*/
                
            } catch (IOException ex) {
                //System.out.println("in catch");
                socketHandlers.remove(this);
                closeSocket();
                //isRuning = false;
                //System.out.println(ex);
            }
        }
    }

    /*Eman Kamal*/
    public void handleResponse(String jsonObjectStr) {
        try {

            ActionHandler actionHandler = null;

            JSONObject jsonObject = new JSONObject(jsonObjectStr);
            String action = jsonObject.getString("action");

            if(action.equals("logout")){
                isRuning = false;
                
                actionHandler = new LogoutHandler();
                actionHandler.handleAction(jsonObjectStr, output);
                
                closeSocket();
                socketHandlers.remove(this);
            }
            else{

                switch (action) {
                    case "signup":
                        actionHandler = new SignUpHandler();
                        ((SignUpHandler)actionHandler).assignUserIdToSocket(this::setUserId);
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
                        System.out.println("to handler");
                        actionHandler = new ToDoUpdateHandler();
                        break;
                    case "delete todo list":
                        //broadCast(jsonObjectStr);
                        actionHandler = new ToDoDeleteHandler();
                        break;
                    case "create task":
                        actionHandler = new TaskCreationHandler();
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
                        actionHandler = new WithdrawFromTaskHandler();
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

                    case "searchFriend":
                        actionHandler = new FriendHandler();
                        break;
                    case "addFriend":
                        actionHandler = new AddFriendHandler();
                        break;
                    case "delete task":
                        actionHandler = new TaskDeleteHandler();
                        break;
                    case "withdraw from task":
                        actionHandler = new WithdrawFromTaskHandler();
                        break;
                    case "add comment":
                        actionHandler = new CommentCreationHandler();
                        break;      



                }
                actionHandler.handleAction(jsonObjectStr, output);
            }
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
    
    /* ahmedpro */
    public static List<Integer> getOnlineIds() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < socketHandlers.size(); i++)
            list.add(socketHandlers.get(i).userId);
        return list;
    }
    
    public void printResponse(String responsString) {
        
        output.println(responsString);
    }
    
    public int getUserId() {
        return userId;
    }
    
    /* ahmedpro */

}
