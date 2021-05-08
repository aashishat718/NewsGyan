/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.test;

/**
 *
 * @author Vibhu007
 */
public class MainApplication {
    public static void main(String[] args) {
            try {

                System.out.println("Begin Streaming APP");
                        StreamRTPtest rtp = new StreamRTPtest();
                        //rtp.start("YOUR_LOCAL_IP_HERE", PORT, "sample.mp3");
                        rtp.start("230.0.0.1", 5000, "F:\\Uri The Surgical Strike 2019 Hindi 1080p HDRip x264 AAC 5.1.mkv");
                System.out.println("End Streaming APP");

                } catch (Exception e) {
                        e.printStackTrace();
                }
	    }
}
