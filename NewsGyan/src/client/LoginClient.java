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
import newsgyan_test.Client;

/**
 *
 * @author Vibhu007
 */
public class LoginClient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    private String username;
    private String password;

    public LoginClient() {
    }

    public LoginClient(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean login()
    {
        try {
            //System.out.println("client started");
            socket = new Socket("localhost",9807);
            //socket = new Socket("171.76.170.117",9807);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.println(1);
            out.println(this.username);
            out.println(this.password);
            out.flush();
            
            if(in.readLine().equals("true"))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
