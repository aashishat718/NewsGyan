/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.shuffle;
import model.NewsArticle;
import model.User;
import utility.NewsGyan;

/**
 *
 * @author Vibhu007
 */
public class RecommendationFetch {
    
    public static ArrayList<NewsArticle> recommended;
    
    public static void main(String args[]){
        
        recommended = new ArrayList<>();
        User user = User.getInstance();
        String pref1 = user.getPref1();
        String pref2 = user.getPref2();
        
        ArrayList<String> allNews = new ArrayList<>(
                Arrays.asList("Politics","Sports","Business","Entertainment","Health","Science"));
        
        ArrayList<NewsArticle> news;
        NewsGyan.main(new String[]{"everything?","q="+pref1+"&"});
        news = NewsGyan.newsArticles;
        
        // adding 1st prefernce
        int min = 0;
        int max = news.size()-1;
        for(int j=0;j<5;j++)
        {
            int index = min + (int)(Math.random() * ((max - min) + 1));
            recommended.add(news.get(index));
            news.remove(index);
            max--;
        }
        
        NewsGyan.main(new String[]{"everything?","q="+pref2+"&"});
        news = NewsGyan.newsArticles;
        
        // adding 2nd prefernce
        min = 0;
        max = news.size()-1;
        for(int j=0;j<5;j++)
        {
            int index = min + (int)(Math.random() * ((max - min) + 1));
            recommended.add(news.get(index));
            news.remove(index);
            max--;
        }
        
        int totalArticle = user.getBusinessF()+user.getEntertainmentF()+user.getHealthF()+user.getPoliticsF()
                                +user.getScienceF()+user.getSportsF();
        
        if(totalArticle != 0)
        {
            double politicsFraction = 0;
            double sportsFraction = 0;
            double businessFraction = 0;
            double entertainmentFraction = 0;
            double healthFraction = 0;
            double scienceFraction = 0;
            
            if(user.getPoliticsF()>20)
                politicsFraction = Math.floor((user.getPoliticsF()*10.0d)/(totalArticle*1.0d));
            if(user.getSportsF()>20)
                sportsFraction = Math.floor((user.getSportsF()*10.0d)/(totalArticle*1.0d));
            if(user.getBusinessF()>20)
                businessFraction = Math.floor((user.getBusinessF()*10.0d)/(totalArticle*1.0d));
            if(user.getEntertainmentF()>20)
                entertainmentFraction = Math.floor((user.getEntertainmentF()*10.0d)/(totalArticle*1.0d));
            if(user.getHealthF()>20)
                healthFraction = Math.floor((user.getHealthF()*10.0d)/(totalArticle*1.0d));
            if(user.getScienceF()>20)
                scienceFraction = Math.floor((user.getScienceF()*10.0d)/(totalArticle*1.0d));
            
            ArrayList<Double> fraction = new ArrayList<>(
                Arrays.asList(politicsFraction,sportsFraction,businessFraction,entertainmentFraction,healthFraction,scienceFraction));
            
            int i1 = allNews.indexOf(pref1);
            int i2 = allNews.indexOf(pref2);
            
            for(int i=0;i<fraction.size();i++)
            {
                if(i!=i1 && i!=i2)
                {
                    NewsGyan.main(new String[]{"everything?","q="+allNews.get(i)+"&"});
                    news = NewsGyan.newsArticles;
                    
                    min = 0;
                    max = news.size()-1;
                    for(int j=0;j<fraction.get(i);j++)
                    {
                        int index = min + (int)(Math.random() * ((max - min) + 1));
                        recommended.add(news.get(index));
                        news.remove(index);
                        max--;
                    }
                }
            }
            
        }
        
        shuffle(recommended);
        //System.out.println(user.getUserName());
        
    }
}
