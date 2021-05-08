/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseHanding;

import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Vlog;
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class VlogsDbHandling {

    public VlogsDbHandling() {
    }
   
    public ArrayList<String> getAllVlogs() {
        
        ArrayList<String> vlogs = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `vlogs`";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                Vlog vlog = new Vlog(rs.getString("reporter"),rs.getString("title"),rs.getString("description"),rs.getString("extension"),rs.getInt("upvotes"),rs.getString("upload_timestamp"));
                String vlogString = new com.google.gson.Gson().toJson(vlog);
                vlogs.add(vlogString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VlogsDbHandling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vlogs;
    }

    public ArrayList<String> getUserVlogs(String username) {
        
        ArrayList<String> vlogs = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `vlogs` WHERE `reporter` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, username);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                Vlog vlog = new Vlog(rs.getString("reporter"),rs.getString("title"),rs.getString("description"),rs.getString("extension"),rs.getInt("upvotes"),rs.getString("upload_timestamp"));
                String vlogString = new com.google.gson.Gson().toJson(vlog);
                vlogs.add(vlogString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VlogsDbHandling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vlogs;
    }
    
    public boolean upvoteVlog(String reporter,String timestamp, String voter) {
        
        PreparedStatement ps,ps1;
        String query = "UPDATE `vlogs` SET `upvotes`=`upvotes`+1 WHERE `reporter` =? AND `upload_timestamp` =?";
        String query1 = "INSERT INTO `upvotes_info`(`unique_video_name`, `voter`) VALUES (?,?)";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, reporter);
            ps.setString(2, timestamp);
            
            if(ps.executeUpdate() > 0) {
                ps1 = MyConnection.getConnection().prepareStatement(query1);
                ps1.setString(1, (reporter+"_"+timestamp));
                ps1.setString(2, voter);
                
                if(ps1.executeUpdate() > 0)
                    return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VlogsDbHandling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<String> alreadyVoted(String votername) {
        ArrayList<String> voted = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `upvotes_info` WHERE `voter` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, votername);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                String vlogs = rs.getString("unique_video_name");
                voted.add(vlogs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VlogsDbHandling.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return voted;
    }
    
    public boolean postVlog(String vlogString) {
        Gson gson = new Gson();
        Vlog vlog = gson.fromJson(vlogString, Vlog.class);
        
        PreparedStatement ps;
        String query = "INSERT INTO `vlogs`(`reporter`, `title`, `description`, `extension`, `upvotes`, `upload_timestamp`) VALUES (?,?,?,?,?,?)";
        
        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            
            ps.setString(1,vlog.getReporter());
            ps.setString(2,vlog.getTitle());
            ps.setString(3,vlog.getDescription());
            ps.setString(4,vlog.getExtension());
            ps.setInt(5, vlog.getUpvotes());
            ps.setString(6,vlog.getTimeStamp());
            
            if(ps.executeUpdate() > 0)
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PostBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
