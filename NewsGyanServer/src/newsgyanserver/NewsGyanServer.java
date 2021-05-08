/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyanserver;

import databaseHanding.RemoveExpiredBlogs;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vibhu007
 */
public class NewsGyanServer {
    
    static final int PORT = 9807;
    static ServerSocket serverSocket = null;
    static Socket socket = null;
    static boolean status = false;
    
    public static void main(String[] args)
    {   
        status = true;
        System.out.println("waiting for clients...");
        
        try {
            serverSocket = new ServerSocket(PORT);
            //serverSocket = new ServerSocket(PORT,0,InetAddress.getByName("171.76.170.117"));
        } catch (IOException e) {
            Logger.getLogger(NewsGyanServer.class.getName()).log(Level.SEVERE, null, e);
        }
        
        while(status)
        {
            try {
                synchronized(serverSocket){
                    socket = serverSocket.accept();
                }
                System.out.println("Connection estabhlished");
                
            } catch (IOException ex) {
                Logger.getLogger(NewsGyanServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            new NewsGyanServerThread(socket).start();
            System.out.println("her1");
        }
        
    }
    
    public static void stopServer()
    {
        status = false;
        try {
            if(socket != null)
                socket.close();
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(NewsGyanServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Server socket closed");
    }
    
    public static void updateBlogs() {
        //String currentTimeStamp = String.valueOf(new java.sql.Timestamp(System.currentTimeMillis()).getTime());
        Instant instant = Instant.now().minus(48, ChronoUnit.HOURS);
        String timestamp = String.valueOf(Timestamp.from(instant).getTime());
        RemoveExpiredBlogs obj = new RemoveExpiredBlogs();
        obj.remove(timestamp);
    }
}
