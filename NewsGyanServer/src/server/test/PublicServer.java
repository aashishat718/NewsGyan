/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.test;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/**
 *
 * @author Vibhu007
 */
public class PublicServer {
    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.out.println("Specify a single MRL to stream");
//            System.exit(1);
//        }
        
        chargerLibrairie();

        String media = "F:\\Uri The Surgical Strike 2019 Hindi 1080p HDRip x264 AAC 5.1.mkv";
        //String publicIP = "192.168.0.255";
        String publicIP = "230.0.0.1";
        short publicPort = 5555;
        String options = formatRtpStream(publicIP, publicPort);

        System.out.println("Streaming '" + media + "' to '" + options + "'");

        // MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

        // mediaPlayer.playMedia(media, options, ":no-sout-rtp-sap", ":no-sout-standard-sap", ":sout-all", ":sout-keep");
        
        mediaPlayer.prepareMedia(media, options);
        mediaPlayer.play();
        
        Thread.currentThread().join(); // Don't exit
    }
    
    static void chargerLibrairie(){
             NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
        }

    private static String formatRtpStream(String serverAddress, short serverPort) {

        StringBuilder sb = new StringBuilder(200);
        //sb.append(":sout=#transcode{acodec=mp4a,samplerate=12000,width=400,height=300}:rtp{dst=");
        //sb.append("::sout=#transcode{vcodec=mp4v,vb=4096,scale=1,fps=30,acodec=mpga,ab=128,channels=2,samplerate=44100,width=800,height=600}:rtp:duplicate{dst=file{dst=");
        sb.append("::sout=#transcode{vcodec=mp4v,vb=3000,fps=30,scale=1,acodec=mp4a,ab=128,channels=2,samplerate=48000,width=800,height=600}:rtp{dst=");

        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
        return sb.toString();
    }
}
