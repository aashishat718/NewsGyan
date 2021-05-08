/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/**
 *
 * @author Vibhu007
 */
public class MediaPanel {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                loadLibrary();
                new MediaPanel(args);
            }
            
        });
    }
    
    static void loadLibrary(){
        NativeLibrary.addSearchPath(
               RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC");
       Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
       LibXUtil.initialise();
    }
    
    private MediaPanel(String[] args) {
        String publicIP, publicServer;
        int publicPort;

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
        EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();

        Canvas canvas = new Canvas();
        canvas.setBackground(Color.black);
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
        mediaPlayer.setVideoSurface(videoSurface);

        JFrame f = new JFrame();
        f.setIconImage(new ImageIcon("vlog.jpg").getImage());
        f.add(canvas);
        f.setSize(800, 600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        publicIP = "230.0.0.1"; // ip where video is being stream
        publicPort = Integer.parseInt(args[0]); // port at server where video is being streamed

        publicServer = formatRtpStream(publicIP, publicPort);
        System.out.println("Capturing from '" + publicServer + "'");
        f.setTitle("Capturing from Public Server : " + publicServer);
        mediaPlayer.playMedia(publicServer);
    }
    
    private String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append("rtp://");
        sb.append(serverAddress);
        sb.append(":");
        sb.append(serverPort);
        return sb.toString();
    }
}
