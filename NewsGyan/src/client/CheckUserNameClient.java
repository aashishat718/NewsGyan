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
public class CheckUserNameClient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    private String username;

    public CheckUserNameClient() {
    }

    public CheckUserNameClient(String username) {
        this.username = username;
    }
    
    public boolean check()
    {
        boolean result = false;
        try {
            //System.out.println("client started");
            socket = new Socket("localhost",9807);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.println(5);
            out.println(this.username);
            out.flush();
            
            result = Boolean.parseBoolean(in.readLine());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
