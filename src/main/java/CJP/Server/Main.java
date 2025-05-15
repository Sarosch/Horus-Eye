package CJP.Server;

import CJP.Server.Activity.ActivityMain;
import CJP.Server.Activity.ActivitySetting;
import CJP.Server.Suporte.Activity;
import CJP.Server.Suporte.Frame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static JFrame root;
    private static JPanel Setting;
    private static JPanel Options;

    private static final String Title = "Horus Eye";
    private static String Device_name;
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private static Map<String,JButton> TabButton = new HashMap<>();
    public static void main(String[] args) {
        screen();

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

        frame.AddActivity("Main",new ActivityMain());
        frame.AddActivity("Setting",new ActivitySetting());
        frame.Alter("Main");
        root.repaint();
    }
}