/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.User;
import model.Vlog;

/**
 *
 * @author Vibhu007
 */
public class RendererVlog extends JLabel implements ListCellRenderer<Vlog> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Vlog> list, Vlog value, int index, boolean isSelected, boolean cellHasFocus) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
       /*
        TODO further apart from this :
       
            Store image icon (thumbnail) for every vlog on server while uploading vlog and fetch it from server directly from here in order to show thumbnails in list
       */
       
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Vibhu007\\Documents\\NetBeansProjects\\NewsGyan\\src\\news.jpg");
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        //ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        setIcon(imageIcon);
        
        String reporter = value.getReporter();
        String title = value.getTitle();
        String description = value.getDescription();
        String upvotes = Integer.toString(value.getUpvotes()) + "upvotes";
        String z = " (You voted this!)";
        
        String text = "<html>" + "<p style=\"font-size:15px\">"+"<b>" + title + "</b>"+ "</p>";
        
        if(User.getVotedVlogs().contains(reporter+"_"+value.getTimeStamp())) 
            text = text + "<p>" +"<i>"+reporter+"</i>"+ "</p>"+ "<p>"+description+"</p>" + "<p>"+upvotes+z+"</p>" + "</html>";
        else
            text = text + "<p>" +"<i>"+reporter+"</i>"+ "</p>"+ "<p>"+description+"</p>" + "<p>"+upvotes+"</p>" + "</html>";
        
        setText(text);
       
       if (isSelected) {
            float[] hsbvals = java.awt.Color.RGBtoHSB(51, 147, 223,null);
            //setBackground(list.getSelectionBackground());
            setBackground(Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]));
            setForeground(list.getSelectionForeground());
            setOpaque(true);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setOpaque(false);
        }
        
        setEnabled(true);
        setFont(list.getFont());
        
        
       return this;
    }
    
}
