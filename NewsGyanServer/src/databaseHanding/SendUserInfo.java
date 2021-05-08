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
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class SendUserInfo {
    
    private String username;

    public SendUserInfo() {
    }

    public SendUserInfo(String username) {
        this.username = username;
    }
    
    public ArrayList<String> getInfoFromDatabase()
    {
        ArrayList<String> user = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        String query = "SELECT * FROM `userinfo` WHERE `user_name` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.username);
            
            rs = ps.executeQuery();
            if(rs.next())
            {   
                user.add("true");
                user.add(this.username);
                user.add(rs.getString("full_name"));
                user.add(rs.getString("pref1"));
                user.add(rs.getString("pref2"));
                user.add(String.valueOf(rs.getInt("politics_f")));
                user.add(String.valueOf(rs.getInt("sports_f")));
                user.add(String.valueOf(rs.getInt("business_f")));
                user.add(String.valueOf(rs.getInt("entertainment_f")));
                user.add(String.valueOf(rs.getInt("health_f")));
                user.add(String.valueOf(rs.getInt("science_f")));
                return user;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.add("false");
        return user;
    }
}
