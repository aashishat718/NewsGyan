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
public class UserInfoUpdate {
    
    private String username;
    private int politicsF;
    private int sportsF;
    private int businessF;
    private int entertainmentF;
    private int healthF;
    private int scienceF;

    public UserInfoUpdate() {
    }

    public UserInfoUpdate(String username, int politicsF, int sportsF, int businessF, int entertainmentF, int healthF, int scienceF) {
        this.username = username;
        this.politicsF = politicsF;
        this.sportsF = sportsF;
        this.businessF = businessF;
        this.entertainmentF = entertainmentF;
        this.healthF = healthF;
        this.scienceF = scienceF;
    }
    
    public boolean updateUserInfoInDatabase()
    {
        PreparedStatement ps;
        String query = "UPDATE userinfo SET politics_f = ?, sports_f = ?, business_f = ?, entertainment_f = ?, health_f = ?, science_f = ? WHERE user_name = ?;";
        
        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, this.politicsF);
            ps.setInt(2, this.sportsF);
            ps.setInt(3, this.businessF);
            ps.setInt(4, this.entertainmentF);
            ps.setInt(5, this.healthF);
            ps.setInt(6, this.scienceF);
            ps.setString(7, this.username);
            
            if(ps.executeUpdate() > 0){ 
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserInfoUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
