/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseHanding;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.MyConnection;

/**
 *
 * @author Vibhu007
 */
public class SignUp {
    
    private String username;
    private String password;
    private String fullName;
    private String pref1;
    private String pref2;

    public SignUp() {
    }

    public SignUp(String username, String password, String fullName, String pref1, String pref2) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.pref1 = pref1;
        this.pref2 = pref2;
    }
    
    public boolean createAccount()
    {
        PreparedStatement ps;
        String query = "INSERT INTO `users`(`user_name`, `full_name`, `password`) VALUES (?,?,?)";

        try {
                ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);

                ps.setString(1,this.username);
                ps.setString(2,this.fullName);
                ps.setString(3,this.password);

                if(ps.executeUpdate() > 0){
                    return createUserInfo();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private boolean createUserInfo() {
        
        PreparedStatement ps;
        String query = "INSERT INTO `userinfo`(`user_name`, `full_name`, `pref1`, `pref2`, `politics_f`, `sports_f`, `business_f`, `entertainment_f`, `health_f`, `science_f`) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);

            ps.setString(1,this.username);
            ps.setString(2,this.fullName);
            ps.setString(3,this.pref1);
            ps.setString(4,this.pref2);
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.setInt(10, 0);

            if(ps.executeUpdate()>0){
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
