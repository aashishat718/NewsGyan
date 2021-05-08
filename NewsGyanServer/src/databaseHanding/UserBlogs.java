/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseHanding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class UserBlogs {
    
    private String username;

    public UserBlogs() {
    }

    public UserBlogs(String username) {
        this.username = username;
    }
    
    public ArrayList<String> getBlogs()
    {
        ArrayList<String> blogs = new ArrayList<>();
        
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `blogs` WHERE `author` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.username);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                Blog blog = new Blog(rs.getString("author"),rs.getString("title"),rs.getString("content"),rs.getString("timestamp"));
                String blogString = new com.google.gson.Gson().toJson(blog);
                blogs.add(blogString);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllBlogs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return blogs;
    }
}
