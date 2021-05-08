/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseHanding;

import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class PostBlog {
    
    private String blogString;

    public PostBlog() {
    }

    public PostBlog(String blogString) {
        this.blogString = blogString;
    }
    
    public boolean post()
    {
        Gson gson = new Gson();
        Blog blog = gson.fromJson(blogString, Blog.class);
        
        PreparedStatement ps;
        String query = "INSERT INTO `blogs`(`author`, `timestamp`, `title`, `content`) VALUES (?,?,?,?)";
        
        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            
            ps.setString(1,blog.getAuthor());
            ps.setString(2,blog.getTimestamp());
            ps.setString(3,blog.getTitle());
            ps.setString(4,blog.getContent());
            
            if(ps.executeUpdate() > 0)
                return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PostBlog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
}
