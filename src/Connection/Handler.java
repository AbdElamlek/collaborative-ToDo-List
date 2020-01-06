/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

/**
 *
 * @author Reham
 */
public class Handler{
    private ActionHandler actionHandler;
    
    public Handler(ActionHandler actionHandler){
        this.actionHandler = actionHandler;
    }
    public String handleAction(String requestJsonObject) {
        return actionHandler.handleAction(requestJsonObject);
    }
    
}
