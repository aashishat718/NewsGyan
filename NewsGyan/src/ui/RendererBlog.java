/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import model.Blog;

/**
 *
 * @author Vibhu007
 */
public class RendererBlog extends JLabel implements ListCellRenderer<Blog>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Blog> list, Blog value, int index, boolean isSelected, boolean cellHasFocus) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        String title = value.getTitle();
        String author = "Author- " + value.getAuthor();
        String content = value.getContent();
        content = content.substring(0, (int)(Math.min(80, content.length()))) + "....";
        
        String text = "<html>" + "<p style=\"font-size:15px\">"+"<b>" + title + "</b>"+ "</p>";
        text = text + "<p>" +"<i>"+author+"</i>"+ "</p>"+ "<p>"+content+"</p>" + "</html>";
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
