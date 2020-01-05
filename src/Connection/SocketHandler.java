/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import DAOController.UserController;
import Entities.UserEntity;
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
import com.google.gson.Gson;

/**
 *
 * @author ahmedprso
 */
public class SocketHandler extends Thread {

    private BufferedReader input;
    private PrintStream output;
    private boolean isRuning = true;

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
    /*Eman Kamal*/

}
