package CJP.Server.Suporte;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RDP extends Thread{
    private String Pass;
    private int port;
    private String OS;
    private Robot robot;
    private Rectangle Screen;
    private BufferedImage record;

    public RDP(int Port, String Password){
        Pass = Password;
        port = Port;
    }
    public RDP(int Port){
        port = Port;
    }

    @Override
    public void run(){


    }
    public BufferedImage ScreenRecord(){
        try {
            robot = new Robot();
            Screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

            new Thread(()-> {
                while(true){
                        record = robot.createScreenCapture(Screen);
                }
            }).start();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return record;
    }
}