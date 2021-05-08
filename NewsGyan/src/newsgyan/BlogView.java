/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan;

import client.UserInfoUpdateClient;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import model.Blog;
import ui.RendererBlog;
import utility.BlogsGenerator;

/**
 *
 * @author Vibhu007
 */
public class BlogView extends javax.swing.JFrame {

    /**
     * Creates new form BlogView
     */
    DefaultListModel<Blog> listModel;
    private ArrayList<Blog> blogs;
    
    public BlogView() {
        initComponents();
        BlogsGenerator obj = new BlogsGenerator();
        this.blogs = obj.getAllBlogs();
        listModel = new DefaultListModel<>();
        this.blogs.forEach((n) -> {
            listModel.addElement(n);
        });
        this.blogList.setModel(listModel);
        
        this.blogList.setCellRenderer(new RendererBlog());
        
        customWindowClosing();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        writeBlogButton = new javax.swing.JButton();
        allBlogsButton = new javax.swing.JButton();
        searchUserBlog = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        blogList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        blogArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back-button.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Blogs");

        writeBlogButton.setText("Write your blog");
        writeBlogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeBlogButtonActionPerformed(evt);
            }
        });

        allBlogsButton.setText("All blogs");
        allBlogsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allBlogsButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search blogs");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(searchUserBlog, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(allBlogsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(writeBlogButton)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(writeBlogButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(allBlogsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchUserBlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(21, 21, 21))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1160, 70));

        blogList.setModel(new javax.swing.AbstractListModel<Blog>() {
            Blog[] blogs = {};
            public int getSize() { return blogs.length; }
            public Blog getElementAt(int i) { return blogs[i]; }
        });
        blogList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                blogListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(blogList);

        blogArea.setEditable(false);
        blogArea.setColumns(20);
        blogArea.setRows(5);
        jScrollPane2.setViewportView(blogArea);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1160, 620));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void blogListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blogListMouseClicked
        Blog clickedBlog = (Blog) blogList.getSelectedValue();
        viewBlog(clickedBlog);
    }//GEN-LAST:event_blogListMouseClicked

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        openHomePage();
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void writeBlogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeBlogButtonActionPerformed
        WriteBlog obj = new WriteBlog();
        obj.setVisible(true);
        obj.pack();
        obj.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_writeBlogButtonActionPerformed

    private void allBlogsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allBlogsButtonActionPerformed
        BlogsGenerator obj = new BlogsGenerator();
        this.blogs = obj.getAllBlogs();
        listModel = new DefaultListModel<>();
        this.blogs.forEach((n) -> {
            listModel.addElement(n);
        });
        this.blogList.setModel(listModel);
    }//GEN-LAST:event_allBlogsButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String uname = searchUserBlog.getText();
        if(!uname.isEmpty()){
            BlogsGenerator obj = new BlogsGenerator();
            this.blogs = obj.getUserBlogs(uname);
            listModel = new DefaultListModel<>();
            this.blogs.forEach((n) -> {
                listModel.addElement(n);
        });
        this.blogList.setModel(listModel);
        }
        else{
            JOptionPane.showMessageDialog(null , "Empty fields not allowed");
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BlogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlogView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlogView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allBlogsButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextArea blogArea;
    private javax.swing.JList<Blog> blogList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchUserBlog;
    private javax.swing.JButton writeBlogButton;
    // End of variables declaration//GEN-END:variables

    private void viewBlog(Blog clickedBlog) {
        
        String title = clickedBlog.getTitle();
        String author = "Author- " + clickedBlog.getAuthor();
        String content = clickedBlog.getContent();
        
        //blogArea.setText( "\"<html><p style=\"font-size:15px\"><b>\"" + title + "\"</b></p><p><i>\"" +author+ "\"</i></p><p>\"" +content+ "\"</p></html>\"" );
        
        blogArea.setText(title + "\n" + author + "\n\n" + content);
    }
    
    private static void openHomePage() {
        Home home = new Home();
        home.setVisible(true);
        home.setLocationRelativeTo(null);
    }
    
    private void customWindowClosing() {
        this.addWindowListener(new WindowAdapter(){
            @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            
            UserInfoUpdateClient obj1 = new UserInfoUpdateClient();
                if(!(obj1.updateUserInfo()))
                    JOptionPane.showMessageDialog(null , "Some error occured");
                
            }
        });
    }
}
