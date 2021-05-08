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
public class RemoveExpiredBlogs {

    public RemoveExpiredBlogs() {
    }
    
    public void remove(String timestamp) {
        
        PreparedStatement ps;
        String query = "DELETE FROM `blogs` WHERE `timestamp` <?";
        
        try {
            ps = (PreparedStatement) MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, timestamp);
            
            ps.execute();
//            if(ps.execute()){
//                System.out.println("here");
//            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RemoveExpiredBlogs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
