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
public class SignUpClient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    private String username;
    private String fullName;
    private String password;
    private String pref1;
    private String pref2;

    public SignUpClient() {
    }

    public SignUpClient(String username, String fullName, String password, String pref1, String pref2) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.pref1 = pref1;
        this.pref2 = pref2;
    }
    
    public boolean createAccount()
    {
        boolean result = false;
        try {
            //System.out.println("client started");
            socket = new Socket("localhost",9807);
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            out.println(6);
            out.println(this.username);
            out.println(this.password);
            out.println(this.fullName);
            out.println(this.pref1);
            out.println(this.pref2);
            out.flush();
            
            result = Boolean.parseBoolean(in.readLine());
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
