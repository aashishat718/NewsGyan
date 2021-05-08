/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import client.AllBlogsClient;
import client.UserBlogClient;
import com.google.gson.Gson;
import java.util.ArrayList;
import model.Blog;

/**
 *
 * @author Vibhu007
 */
public class BlogsGenerator {
    
    public ArrayList<Blog> getAllBlogs()
    {
        AllBlogsClient obj = new AllBlogsClient();
        ArrayList<String> blogString = obj.getBlogs();
        
        ArrayList<Blog> blogs = new ArrayList<>();
        Gson gson = new Gson();
        blogString.forEach((b) -> {
            Blog blog = gson.fromJson(b, Blog.class);
            blogs.add(blog);
        });
        return blogs;
    }
    
    public ArrayList<Blog> getUserBlogs(String username)
    {
        UserBlogClient obj = new UserBlogClient(username);
        ArrayList<String> blogString = obj.getBlogs();
        
        ArrayList<Blog> blogs = new ArrayList<>();
        Gson gson = new Gson();
        blogString.forEach((b) -> {
            Blog blog = gson.fromJson(b, Blog.class);
            blogs.add(blog);
        });
        return blogs;
    }
}
