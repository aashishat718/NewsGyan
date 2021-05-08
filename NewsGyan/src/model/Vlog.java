/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Vibhu007
 */
public class Vlog {
    
    private String reporter;
    private String title;
    private String description;
    private String extension;
    private int upvotes;
    private String timeStamp;

    public Vlog() {
    }

    public Vlog(String reporter, String title, String description,String extension,int upvotes, String TimeStamp) {
        this.reporter = reporter;
        this.title = title;
        this.description = description;
        this.extension = extension;
        this.upvotes = upvotes;
        this.timeStamp = TimeStamp;
    }
    
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.timeStamp = TimeStamp;
    }
    
}
