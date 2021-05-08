/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newsgyan_test;

/**
 *
 * @author Vibhu007
 */
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/*www.codeurjava.com*/

    public class MediaPanelTest {

        public static void main(final String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                     chargerLibrairie();
                      new MediaPanelTest(args);
                }
            });
        }

        static void chargerLibrairie(){
             NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
        }
       
        /*
        private MediaPanel(String[] args) {
            JFrame frame = new JFrame("Tutoriel vlcj");
            frame.setLocation(100, 100);
            frame.setSize(1050, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            Canvas c = new Canvas();
            c.setBackground(Color.black);
            JPanel p = new JPanel();
            p.setLayout(new BorderLayout());
            p.add(c, BorderLayout.CENTER);
            frame.add(p, BorderLayout.CENTER);

            
            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
            //EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(frame));
            EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
            mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
            mediaPlayer.toggleFullScreen();
            mediaPlayer.setEnableMouseInputHandling(false);
            mediaPlayer.setEnableKeyInputHandling(true);
           
            //mediaPlayer.prepareMedia("D:\\Screen recording\\20200918_175916.mp4");
            mediaPlayer.prepareMedia("F:\\Uri The Surgical Strike 2019 Hindi 1080p HDRip x264 AAC 5.1.mkv");
            mediaPlayer.play();
        }
        */
        
        private MediaPanelTest(String[] args) {
        //    String mediatorIP = "192.168.1.104"; short mediatorPort = 6001;
            String publicIP, publicServer, localIP, localServer, clientIP;
            short publicPort, localPort;

            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
            EmbeddedMediaPlayer mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();

            Canvas canvas = new Canvas();
            canvas.setBackground(Color.black);
            CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(canvas);
            mediaPlayer.setVideoSurface(videoSurface);

            JFrame f = new JFrame();
            //f.setIconImage(new ImageIcon(Client.class.getResource("icons/vlcj-logo.png")).getImage());
            f.add(canvas);
            f.setSize(800, 600);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
//            publicIP = inFromServer.readLine(); // Recv public server's ip from mediator
//            publicPort = Short.parseShort(inFromServer.readLine()); // Recv public server's port from mediator

            publicIP = "230.0.0.1";
            publicPort = 5000;

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