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
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Vibhu007
 */
public class RecieveUserInfo {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String username;

    public RecieveUserInfo() {
    }

    public RecieveUserInfo(String username) {
        this.username = username;
    }
    
    public boolean recieveUserInfo(){
        
        try {
            //System.out.println("client started");
            socket = new Socket("localhost",9807);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.println(2);
            out.println(this.username);
            out.flush();
            
            if("true".equals(in.readLine())){
                getUserForSession();
                return true;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(RecieveUserInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void getUserForSession() {
        try {
            User user = User.getInstance();
            
            user.setUserName(in.readLine());
            user.setFullName(in.readLine());
            user.setPref1(in.readLine());
            user.setPref2(in.readLine());
            user.setPoliticsF(Integer.parseInt(in.readLine()));
            user.setSportsF(Integer.parseInt(in.readLine()));
            user.setBusinessF(Integer.parseInt(in.readLine()));
            user.setEntertainmentF(Integer.parseInt(in.readLine()));
            user.setHealthF(Integer.parseInt(in.readLine()));
            user.setScienceF(Integer.parseInt(in.readLine()));
            
        } catch (IOException ex) {
            Logger.getLogger(RecieveUserInfo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null , "Something went wrong");
        }
    }
}
