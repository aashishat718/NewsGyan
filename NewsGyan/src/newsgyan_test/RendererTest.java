/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan_test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.NewsArticle;

/**
 *
 * @author Vibhu007
 */
public class RendererTest extends JLabel implements ListCellRenderer<NewsArticle>{
    
    @Override
    public Component getListCellRendererComponent(JList<? extends NewsArticle> list, NewsArticle value, int index, boolean isSelected, boolean cellHasFocus) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        HttpsURLConnection connection = null;
        String url = value.getUrlToImage();
        try {
            if(url != null)
            {   System.out.println("here 1");
                URL imageUrl = new URL(value.getUrlToImage());
                connection = (HttpsURLConnection) imageUrl.openConnection();
                connection.addRequestProperty("User-Agent", "Chrome");
                //URLConnection connection = imageUrl.openConnection();
                //int status = connection.getResponseCode();
                
                BufferedImage img = ImageIO.read(connection.getInputStream());
                
                ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                setIcon(imageIcon);
                
                String title = value.getTitle()!=null ? value.getTitle() : "Not Available";
                String description = value.getDescription()!=null ? value.getDescription() : "Not Available";
                String author = "Author- " + (value.getAuthor()!=null ? value.getAuthor() : "Not Available");

                String text = "<html>" + "<p style=\"font-size:15px\">"+"<b>" + title + "</b>"+ "</p>";
                text = text + "<p>" +"<i>"+author+"</i>"+ "</p>"+ "<p>"+description+"</p>" + "</html>";
                setText(text);
                
                System.out.println("here 2");
            } 
                
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(RendererTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(url);
        } catch (IOException ex) {
            Logger.getLogger(RendererTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(url);
        } finally{
            if(connection != null)
                connection.disconnect();
        }
        
    
    /*    ImageIcon imageIcon = new ImageIcon("C:\\Users\\Vibhu007\\Documents\\NetBeansProjects\\NewsGyan\\src\\news.jpg");
        imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(80, 80, java.awt.Image.SCALE_SMOOTH));
        //ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        setIcon(imageIcon);
        
        String title = value.getTitle()!=null ? value.getTitle() : "Not Available";
        String description = value.getDescription()!=null ? value.getDescription() : "Not Available";
        String author = "Author- " + (value.getAuthor()!=null ? value.getAuthor() : "Not Available");
        
        String text = "<html>" + "<p style=\"font-size:15px\">"+"<b>" + title + "</b>"+ "</p>";
        text = text + "<p>" +"<i>"+author+"</i>"+ "</p>"+ "<p>"+description+"</p>" + "</html>";
        setText(text);*/

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
