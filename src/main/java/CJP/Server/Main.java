package CJP.Server;

import CJP.Server.Activity.ActivityMain;
import CJP.Server.Activity.ActivitySetting;
import CJP.Server.Suporte.Databank;
import CJP.Server.Suporte.Frame;
import CJP.Server.Suporte.BroadCast;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static JFrame root;
    private static JPanel Options;

    private static final String Title = "Horus Eye";
    private static String Device_name = System.getProperty("os.name");
    private static Databank bk;
    private static ActivityMain Main;
    private static int castPort = 0;
    private static InetAddress CastAddress = null;
    private static BroadCast cast;
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args) {
        screen();

        bk = new Databank("jdbc:mysql://localhost:3306/HorusEye?useSSL=false&serverTimezone=UTC","Setting");
        bk.SetOnStart("ip Text, port INTEGER Default 2000");
        bk.Start("root","Sansandri2003");
        try {
            if(!bk.ValueExists("id=1","id")){
                bk.InsertIn("ip,port","'192.168.1.255',2000");
            }
                System.out.println(String.format("Values read from DataBase: ip=%s, port=%s", bk.Read("id=1", "ip"), bk.Read("id=1", "port")));
                CastAddress = InetAddress.getByName(bk.Read("id=1", "ip"));
                castPort = Integer.valueOf(bk.Read("id=1", "port"));

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        if(CastAddress != null && castPort > 0){
            cast = new BroadCast(CastAddress,castPort);
            cast.start();
            cast.SetForNews(String.format("Name:%s; IP:%s; PORT:%s;",Device_name, "rdp.ip()", "rdp.port()"));
        }

        Timer flash = new Timer(2000, e ->{
            cast.Send(String.format("Name:%s; IP:%s; PORT:%s;",Device_name, "rdp.ip()", "rdp.port()"));
        });
        flash.start();

        Thread gather = new Thread(() -> {
            if(cast.toRead())System.out.println(cast.Read());
        });
        gather.start();

    }
    private static void screen(){
        CJP.Server.Suporte.Frame frame = new CJP.Server.Suporte.Frame(Title);
        root = frame.getRoot();
        root.setLayout(null);
        root.setBackground(Color.MAGENTA);


        Options = new JPanel();
        Options.setSize(new Dimension((int) (screen.getWidth()*0.03),(int) screen.getHeight()));
        Options.setPreferredSize(new Dimension((int) (screen.getWidth()*0.03),(int) screen.getHeight()));
        Options.setBackground(Color.cyan);
        Options.setOpaque(false);

        frame.setOptionBar(Options, Frame.Orientation.Vertical, Frame.StartIn.Up,new ImageIcon("src/main/java/Extra/Exit_Icon.png"));
        frame.AddOption(new ImageIcon("src/main/java/Extra/Home_Icon.png"), e -> {
            frame.Alter("Main");
        });
        frame.AddOption(new ImageIcon("src/main/java/Extra/Config_Icon.png"), e -> {
            frame.Alter("Setting");
        });

        Main = new ActivityMain();
        frame.AddActivity("Main",Main);
        frame.AddActivity("Setting",new ActivitySetting());
        frame.Alter("Main");
        root.repaint();
    }
}