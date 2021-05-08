/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NewsArticle;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Vibhu007
 */
public class NewsGyan {

    /**
     */
    public static ArrayList<NewsArticle> newsArticles;
    private static HttpURLConnection connection;
    
    public static void main(String[] args) {
        
        newsArticles = new ArrayList();
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        
        StringBuilder sb = new StringBuilder();
        sb.append("https://newsapi.org/v2/");
        for(String s:args)
            sb.append(s);
        sb.append("apiKey="+Constants.API_KEY); // API KEY stored in Constant.java file in utility package
        //System.out.println(ur);
        
        try {
            //URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey="+Constants.API_KEY);
            URL url = new URL(sb.toString());
            connection = (HttpURLConnection) url.openConnection();
            
            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int status = connection.getResponseCode();
            //System.out.println(status);
            
            if(status!=200){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while( (line = reader.readLine())!= null){
                    responseContent.append(line);
                }
                reader.close();
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while( (line = reader.readLine())!= null){
                    responseContent.append(line);
                }
                reader.close();
            }
            
            //System.out.println(responseContent.toString());
            String newsResponse = responseContent.toString();
            newsResponse = newsResponse.substring(responseContent.indexOf("["));
            parse(newsResponse);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(NewsGyan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewsGyan.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connection.disconnect();
        }
        
        //method 2: java.net.HttpClient
        //java 11
        
    }
    
    public static String parse(String responseContent){
        JSONArray articleList = new JSONArray(responseContent);
        for(int i=0;i<articleList.length();i++){
            JSONObject article = articleList.getJSONObject(i);
            //String author = article.getString("author");
            try
            {
                Gson gson = new Gson();
                NewsArticle obj = gson.fromJson(article.toString(), NewsArticle.class);
                newsArticles.add(obj);
                
            } catch (NullPointerException ex){
                Logger.getLogger(NewsGyan.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
            
            }
        }
        return null;
    }
}
