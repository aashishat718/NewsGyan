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
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_media_t;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;
import uk.co.caprica.vlcj.player.list.MediaListPlayerEventAdapter;
import uk.co.caprica.vlcj.player.list.MediaListPlayerMode;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;


public class StreamRTPtest {
    private MediaPlayerFactory factory;
    private MediaListPlayer mediaListPlayer;
    private MediaList playList;
    
    public StreamRTPtest() throws Exception {
        chargerLibrairie();
        boolean found = new NativeDiscovery().discover();
        if (found) {
        	System.out.println(LibVlc.INSTANCE.libvlc_get_version());
            factory = new MediaPlayerFactory();
            mediaListPlayer = factory.newMediaListPlayer();
            mediaListPlayer.addMediaListPlayerEventListener(new MediaListPlayerEventAdapter() {
                @Override
                public void nextItem(MediaListPlayer mediaListPlayer, libvlc_media_t item, String itemMrl) {
                    System.out.println("Playing next item: " + itemMrl + " (" + item + ")");
                }
            });
            playList = factory.newMediaList();
		}else {
			throw new Exception("Error During construction, please check NativeDiscovery");
		}
    }
    
    static void chargerLibrairie(){
             NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(), "D:\\Program Files\\VideoLAN\\VLC");
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            LibXUtil.initialise();
        }
   
    @Override
    public void finalize() {
        // Stop processing.
        mediaListPlayer.stop();
        // Finish program.
        mediaListPlayer.release();
        factory.release();
    }
        
    /**
     * 
     * @param serverAddress
     * @param serverPort
     * @return  mediaOptions for rtp stream wih vlc
     */
    private String formatRtpStream(String serverAddress, int serverPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(serverAddress);
        sb.append(",port=");
        sb.append(serverPort);
        sb.append(",mux=ts}");
        return sb.toString();
    }
    
    /*
    private static String formatRtpStream(String serverAddress, int serverPort) {

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
    */
    
    /**
     * Start streaming of music by adding music to the playList
     * 
     * @param dir
     * @param address
     * @param port
     * @param musique
     * @throws Exception
     */
     void start(String address, int port, String music) throws Exception {
        mediaListPlayer = factory.newMediaListPlayer();
        String mediaOptions = formatRtpStream(address, port);
        playList.addMedia(music, mediaOptions);
        // Attach the play-list to the media list player
        mediaListPlayer.setMediaList(playList);
        mediaListPlayer.setMode(MediaListPlayerMode.LOOP);
        // Finally, start the media player
        mediaListPlayer.play();
        System.out.println("Streaming started at rtp://" + address + ":" + port);

        Thread.currentThread().join(); // Don't exit
    }

}