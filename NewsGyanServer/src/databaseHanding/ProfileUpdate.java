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
public class ProfileUpdate {
    
    private String username;
    private String fullName;
    private String pref1;
    private String pref2;

    public ProfileUpdate() {
    }

    public ProfileUpdate(String username,String fullName, String pref1, String pref2) {
        this.username = username;
        this.fullName = fullName;
        this.pref1 = pref1;
        this.pref2 = pref2;
    }
    
    public boolean updateProfileInDatabase()
    {
        PreparedStatement ps;
        //String query = "UPDATE `userinfo`(`full_name`, `pref1`, `pref2`) WHERE (`user_name`) VALUES (?,?,?,?)";

        String query = "UPDATE userinfo SET full_name = ?, pref1 = ?, pref2 = ? WHERE user_name = ?;";

        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            ps.setString(1,this.fullName);
            ps.setString(2,this.pref1);
            ps.setString(3,this.pref2);
            ps.setString(4,this.username);

            if(ps.executeUpdate() > 0){
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProfileUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
