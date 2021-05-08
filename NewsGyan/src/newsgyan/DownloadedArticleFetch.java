/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import model.NewsArticle;

/**
 *
 * @author Vibhu007
 */
public class DownloadedArticleFetch {
    
    public static ArrayList<NewsArticle> newsArticles;
    public static ArrayList<String> articleAddress;
    
    public static void main(String args[])throws IOException{
        
        newsArticles = new ArrayList<>();
        articleAddress = new ArrayList<>();
        
        File downloadInfo = new File("D:\\C\\NewsGyanData\\downloadInfo.txt");
        downloadInfo.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(downloadInfo));
        String st;
        while( (st=br.readLine()) != null){

            // adding news article to download list
            Gson gson = new Gson();
            NewsArticle newsArticle = gson.fromJson(st, NewsArticle.class);
            newsArticles.add(newsArticle);

            // adding its local path to address list
            st = br.readLine();
            articleAddress.add(st);
            
        }
        
        br.close();
    }
}
