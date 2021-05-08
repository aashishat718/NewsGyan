/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import client.VlogClient;
import java.util.ArrayList;

/**
 *
 * @author Vibhu007
 */
public class User {
    
    private String userName;
    private String fullName;
    private String pref1;
    private String pref2;
    private int politicsF; // politics atricle viewed frequency
    private int sportsF,businessF,entertainmentF,healthF,scienceF;
    
    private static User instance;
    private static ArrayList<String> votedVlogs;

    private User() {
    }
    
    public static User getInstance(){
        if(instance == null)
            instance = new User();
        return instance;
    }
    
    public static ArrayList<String> getVotedVlogs() {
        if(votedVlogs == null) {
            VlogClient obj = new VlogClient();
            votedVlogs = obj.getVotedVlogs(User.getInstance().getUserName());
        }
        return votedVlogs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPref1() {
        return pref1;
    }

    public void setPref1(String pref1) {
        this.pref1 = pref1;
    }

    public String getPref2() {
        return pref2;
    }

    public void setPref2(String pref2) {
        this.pref2 = pref2;
    }

    public int getPoliticsF() {
        return politicsF;
    }

    public void setPoliticsF(int politicsF) {
        this.politicsF = politicsF;
    }

    public int getSportsF() {
        return sportsF;
    }

    public void setSportsF(int sportsF) {
        this.sportsF = sportsF;
    }

    public int getBusinessF() {
        return businessF;
    }

    public void setBusinessF(int businessF) {
        this.businessF = businessF;
    }

    public int getEntertainmentF() {
        return entertainmentF;
    }

    public void setEntertainmentF(int entertainmentF) {
        this.entertainmentF = entertainmentF;
    }

    public int getHealthF() {
        return healthF;
    }

    public void setHealthF(int healthF) {
        this.healthF = healthF;
    }

    public int getScienceF() {
        return scienceF;
    }

    public void setScienceF(int scienceF) {
        this.scienceF = scienceF;
    }
    
    
}
