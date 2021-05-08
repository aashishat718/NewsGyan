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
public class Login {
    
    private String username;
    private String password;

    public Login() {
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean loginAuthentication()
    {
        PreparedStatement ps;
        ResultSet rs;
        String uname = this.username;
        String pass = this.password;
        
        String query = "SELECT * FROM `users` WHERE `user_name` =? AND `password` =?";
        
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, uname);
            ps.setString(2, pass);
            
            rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
