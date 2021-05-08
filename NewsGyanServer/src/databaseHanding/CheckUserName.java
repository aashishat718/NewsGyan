/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseHanding;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class CheckUserName {
    
    private String username;

    public CheckUserName() {
    }

    public CheckUserName(String username) {
        this.username = username;
    }
    
    public boolean check()
    {
        boolean checkUser = false;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT * FROM `users` WHERE `user_name` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, this.username);
            
            rs = ps.executeQuery();
            if(rs.next())
            {
                checkUser = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return checkUser;
    }
}
