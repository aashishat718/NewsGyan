/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import client.VlogClient;
import com.google.gson.Gson;
import java.util.ArrayList;
import model.Vlog;

/**
 *
 * @author Vibhu007
 */
public class VlogsInfoGenerator {
    
    public ArrayList<Vlog> getAllVlogs()
    {
        VlogClient obj = new VlogClient();
        ArrayList<String> vlogString = obj.getAllVlogs();
        
        ArrayList<Vlog> vlogs = new ArrayList<>();
        Gson gson = new Gson();
        vlogString.forEach((b) -> {
            Vlog vlog = gson.fromJson(b, Vlog.class);
            vlogs.add(vlog);
        });
        return vlogs;
    }
    
    public ArrayList<Vlog> getUserVlogs(String username)
    {
        VlogClient obj = new VlogClient();
        ArrayList<String> vlogString = obj.getUserVlogs(username);
        
        ArrayList<Vlog> vlogs = new ArrayList<>();
        Gson gson = new Gson();
        vlogString.forEach((b) -> {
            Vlog vlog = gson.fromJson(b, Vlog.class);
            vlogs.add(vlog);
        });
        return vlogs;
    }
}
