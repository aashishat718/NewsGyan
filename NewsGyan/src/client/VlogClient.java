/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import newsgyan_test.Client;

/**
 *
 * @author Vibhu007
 */
public class VlogClient {
    
    Socket socket;
    BufferedReader in;
    PrintWriter out;

    public VlogClient() {
        try {
            socket = new Socket("localhost",9807);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
        } catch (IOException ex) {
            Logger.getLogger(VlogClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getAllVlogs()
    {
        ArrayList<String> vlogs = new ArrayList<>();
        
        try {
            //System.out.println("client started");
            
            out.println(10);
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine())){
                getVlogsFromNetwork(vlogs);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vlogs;
    }
    
    public ArrayList<String> getUserVlogs(String username)
    {
        ArrayList<String> vlogs = new ArrayList<>();
        
        try {
            //System.out.println("client started");
            
            out.println(11);
            out.println(username);
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine())){
                getVlogsFromNetwork(vlogs);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vlogs;
    }

    private void getVlogsFromNetwork(ArrayList<String> vlogs) {
        
        try {
            String s; 
            while((s = in.readLine()) != null)
                vlogs.add(s);
            
        } catch (IOException ex) {
            Logger.getLogger(AllBlogsClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean upvoteVlog(String reporter, String timestamp) {
        try {
            //System.out.println("client started");
            
            out.println(12);
            out.println(reporter);
            out.println(timestamp);
            out.println(User.getInstance().getUserName());
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine())){
                return true;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public String getPortForVideoStream(String reporter, String timestamp, String extension) {
        try {
            //System.out.println("client started");
            
            out.println(13);
            out.println(reporter);
            out.println(timestamp);
            out.println(extension);
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine())){
                String port = in.readLine();
                return port;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "";
    }
    
    public ArrayList<String> getVotedVlogs(String username) {
        ArrayList<String> votedVlogs = new ArrayList<>();
        
        try {
            //System.out.println("client started");
            
            out.println(14);
            out.println(username);
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine())){
                getVlogsFromNetwork(votedVlogs);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return votedVlogs;
    }
    
    public boolean postVlog(String vlogString) {
        try {
            out.println(15);
            out.println(vlogString);
            out.flush();
            
            if(Boolean.parseBoolean(in.readLine()))
                return true;
            
        } catch (IOException ex) {
            Logger.getLogger(VlogClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public void postVideo(String filePath, String uniqueFileName) {
        BufferedInputStream bis = null;
        try {
            File myFile = new File(filePath);
            int fileSize = (int)myFile.length();
            out.println(16);
            out.println(uniqueFileName);
            out.println(fileSize);
            out.flush();
            
            OutputStream os = this.socket.getOutputStream();
            byte[] mybytearray = new byte[fileSize];
            bis = new BufferedInputStream(new FileInputStream(myFile));
            
            int count;
            while( (count = bis.read(mybytearray, 0, mybytearray.length)) > 0) {
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();
                System.out.println(count);
            }
            os.close();
            bis.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VlogClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) { 
            Logger.getLogger(VlogClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
