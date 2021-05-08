/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyanserver;

import databaseHanding.AllBlogs;
import databaseHanding.CheckUserName;
import databaseHanding.Login;
import databaseHanding.PostBlog;
import databaseHanding.ProfileUpdate;
import databaseHanding.SendUserInfo;
import databaseHanding.SignUp;
import databaseHanding.UserBlogs;
import databaseHanding.UserInfoUpdate;
import databaseHanding.VlogsDbHandling;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vibhu007
 */
public class NewsGyanServerThread extends Thread {
    protected Socket socket;
    BufferedReader in;
    PrintWriter out;
    
    /*
        Request information:
    
        1 - login request
        2 - get user info request
        3 - profile update
        4 - update user info
        5 - check user name exists or not
        6 - create account
        7 - get all blogs
        8 - particular user blog
        9 - post blog
        10 - get all vlogs
        11 - get User vlogs
        12 - upvote vlog
        13 - play vlog
        14 - list of vlogs voted by a user
        15 - post vlog
        16 - store video
    
    */

    public NewsGyanServerThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run()
    {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            
            int requestCode = Integer.parseInt(in.readLine());

            switch (requestCode) {
                case 1:
                    doLogin();
                    break;
                case 2:
                    getUserInfo();
                    break;
                case 3:
                    updateProfile();
                    break;
                case 4:
                    updateUserInfo();
                    break;
                case 5:
                    checkUserName();
                    break;
                case 6:
                    doSignUp();
                    break;
                case 7:
                    getAllBlogs();
                    break;
                case 8:
                    getUserBlogs();
                    break;
                case 9:
                    postBlog();
                    break;
                case 10:
                    getAllVlogs();
                    break;
                case 11:
                    getUserVlogs();
                    break;
                case 12:
                    upvoteVlog();
                    break;
                case 13:
                    playVideo();
                    break;
                case 14:
                    alreadyVotedVlog();
                    break;
                case 15:
                    postVlog();
                    break;
                case 16:
                    postVideo();
                    break;
                default:
                    break;
            }

            out.flush();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(NewsGyanServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void doLogin() throws IOException
    {
        String username = in.readLine();
        String password = in.readLine();
        Login login = new Login(username,password);
        boolean authenticationResult = login.loginAuthentication();

        if(authenticationResult)
            out.println("true");
        else
            out.println("false");
    }

    private void getUserInfo() throws IOException {
        
        String username = in.readLine();
        SendUserInfo obj = new SendUserInfo(username);
        ArrayList<String> userInfo = obj.getInfoFromDatabase();

        userInfo.forEach((s) -> {
            out.println(s);
        });
    }

    private void updateProfile() throws IOException {
        String username = in.readLine();
        String fullName = in.readLine();
        String pref1 = in.readLine();
        String pref2 = in.readLine();
        ProfileUpdate obj = new ProfileUpdate(username,fullName,pref1,pref2);

        if(obj.updateProfileInDatabase())
            out.println("true");
        else
            out.println("false");
    }

    private void updateUserInfo() throws IOException {

        String username = in.readLine();
        int politicsF = Integer.parseInt(in.readLine());
        int sportsF = Integer.parseInt(in.readLine());
        int businessF = Integer.parseInt(in.readLine());
        int entertainmentF = Integer.parseInt(in.readLine());
        int healthF = Integer.parseInt(in.readLine());
        int scienceF = Integer.parseInt(in.readLine());
        UserInfoUpdate obj = new UserInfoUpdate(username,politicsF,sportsF,businessF,entertainmentF,healthF,scienceF);

        if(obj.updateUserInfoInDatabase())
            out.println("true");
        else
            out.println("false");
    }

    private void checkUserName() throws IOException {
        String username = in.readLine();
        CheckUserName obj = new CheckUserName(username);

        out.println(obj.check());
    }

    private void doSignUp() throws IOException {
        
        String username = in.readLine();
        String password = in.readLine();
        String fullName = in.readLine();
        String pref1 = in.readLine();
        String pref2 = in.readLine();
        SignUp obj = new SignUp(username,password,fullName,pref1,pref2);

        out.println(obj.createAccount());
    }

    private void getAllBlogs() throws IOException {
        
        AllBlogs obj = new AllBlogs();
        ArrayList<String> blogs = obj.getBlogs();
        out.println("true");
        for(int i = blogs.size()-1;i>=0;i--)
            out.println(blogs.get(i));
    }

    private void getUserBlogs() throws IOException {
        
            String username = in.readLine();
            UserBlogs obj = new UserBlogs(username);
            ArrayList<String> blogs = obj.getBlogs();
            out.println("true");
            for(int i = blogs.size()-1;i>=0;i--)
                out.println(blogs.get(i));
    }

    private void postBlog() throws IOException {
        String blogString = in.readLine();
        PostBlog obj = new PostBlog(blogString);
        out.println(obj.post());
    }

    private void getAllVlogs() {
        
        VlogsDbHandling obj = new VlogsDbHandling();
        ArrayList<String> vlogs = obj.getAllVlogs();
        out.println("true"); 
        for(int i = vlogs.size()-1;i>=0;i--)
            out.println(vlogs.get(i));
    }

    private void getUserVlogs() throws IOException {
        
        String username = in.readLine();
        VlogsDbHandling obj = new VlogsDbHandling();
        ArrayList<String> vlogs = obj.getUserVlogs(username);
        out.println("true");
        for(int i = vlogs.size()-1;i>=0;i--)
            out.println(vlogs.get(i));
    }

    private void upvoteVlog() throws IOException {
        
        String reporter = in.readLine();
        String timestamp = in.readLine();
        String voter = in.readLine();
        VlogsDbHandling obj = new VlogsDbHandling();
        out.println(Boolean.toString(obj.upvoteVlog(reporter, timestamp, voter)));      
    }

    private void playVideo() throws IOException {
        
        String reporter = in.readLine();
        String timestamp = in.readLine();
        String extension = in.readLine();
        String path = "D:\\C\\NewsGyanData\\vlogs\\" + reporter+"_"+timestamp + extension;
        
        ServerSocket ss = new ServerSocket(0);
        int port = ss.getLocalPort();
        if(port > 0) {
            out.println("true");
            out.println(Integer.toString(port));
            
            Thread streamVideo = new Thread() {
                @Override
                public void run() {
                    try {
                        StreamRTP rtp = new StreamRTP();
                        rtp.start("230.0.0.1", port, path);
                    } catch (Exception ex) {
                        Logger.getLogger(NewsGyanServerThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            streamVideo.start();
        }
        else {
            out.println("false");
        }
    }

    private void alreadyVotedVlog() throws IOException{
        String votername = in.readLine();
        VlogsDbHandling obj = new VlogsDbHandling();
        ArrayList<String> voted = obj.alreadyVoted(votername);
        
        out.println("true");
        voted.forEach((v) -> {
            out.println(v);
        });
    }

    private void postVlog() throws IOException{
        String vlogString = in.readLine();
        // System.out.println(vlogString);
        VlogsDbHandling obj = new VlogsDbHandling();
        out.println(Boolean.toString(obj.postVlog(vlogString)));
    }

    private void postVideo() throws IOException{
        String fileName = in.readLine();
        int fileSize = Integer.parseInt(in.readLine());
        
        System.out.println(fileName + " " + fileSize);
        
        BufferedOutputStream bos;
        try (InputStream is = this.socket.getInputStream()) {
            FileOutputStream fos = new FileOutputStream("D:\\C\\NewsGyanData\\vlogs\\"+fileName);
            bos = new BufferedOutputStream(fos);
            byte[] filebyte = new byte[fileSize];
            int file;
            while( (file = is.read(filebyte, 0, filebyte.length)) > 0) {
                bos.write(filebyte, 0, file);
                System.out.println(file);
            }
        }
        bos.close();
        System.out.println("here");
        // out.println("true");
    }
       
}
