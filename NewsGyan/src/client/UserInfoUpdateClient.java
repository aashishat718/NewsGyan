/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Vibhu007
 */
public class UserInfoUpdateClient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public UserInfoUpdateClient() {
    }
    
    public  boolean updateUserInfo()
    {
        User user = User.getInstance();
        try {
            //System.out.println("client started");
            socket = new Socket("localhost",9807);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.println(4);
            out.println(user.getUserName());
            out.println(user.getPoliticsF());
            out.println(user.getSportsF());
            out.println(user.getBusinessF());
            out.println(user.getEntertainmentF());
            out.println(user.getHealthF());
            out.println(user.getScienceF());
            out.flush();
            
            if("true".equals(in.readLine())){
                return true;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RecieveUserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
