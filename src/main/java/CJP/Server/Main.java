package CJP.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static JFrame root;

    private static JPanel Main;
    private static JPanel Setting;
    private static JPanel Options;

    private static String Title = "";
    private static String Device_name;
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args) {
        screen();

    }
    private static void screen(){
        Frame frame = new Frame(Title);
        root = frame.getRoot();


        Options = new JPanel();
        Options.setSize(new Dimension((int) (screen.getWidth()*0.03),(int) screen.getHeight()));
        Options.setPreferredSize(new Dimension((int) (screen.getWidth()*0.03),(int) screen.getHeight()));
        Options.setBackground(Color.cyan);

        frame.setOptionBar(Options,Frame.Orientation.Vertical,Frame.StartIn.Up,new ImageIcon("src/main/java/Extra/Exit_Icon.png"));
        frame.AddOption(new ImageIcon("src/main/java/Extra/Home_Icon.png"), e -> {
            frame.Alter("Main");
        });
        frame.AddOption(new ImageIcon("src/main/java/Extra/Config_Icon.png"), e -> {
            frame.Alter("Setting");
        });

        ActivityMain();
        ActivitySetting();

        frame.AddActivity("Main",Main);
        frame.AddActivity("Setting",Setting);
        root.repaint();
    }
    private static void ActivityMain(){
        Main = new JPanel();

        Main.setBounds((int)(screen.getWidth()*0.03),0,(int) (screen.getWidth()-(screen.getWidth()*0.03)),(int) screen.getHeight());
        Main.setBackground(Color.BLACK);
        JPanel core = new JPanel();
        core.setBounds((int)(Main.getWidth()*0.2),(int)(Main.getHeight()*0.2),(int)(Main.getWidth()-(Main.getWidth()*0.2)),(int)(Main.getHeight()-(Main.getHeight()*0.2)));
        core.setLocation((int)(Main.getWidth()*0.2),(int)(Main.getHeight()*0.2));
        System.out.println(String.format("core:{ Size:{ H: %s, W: %s}, X: %s, Y: %s}",core.getHeight(),core.getWidth(),core.getAlignmentX(),core.getAlignmentY()));
        System.out.println(String.format("core pre-Setting:{ Size:{ H: %s, W: %s}, X: %s, Y: %s}",(int)(Main.getWidth()-(Main.getWidth()*0.2)),(int)(Main.getHeight()-(Main.getHeight()*0.2)),(int)(Main.getWidth()*0.2),(int)(Main.getHeight()*0.2)));

        Main.add(core);
        Main.validate();
        Main.repaint();
        core.validate();
        core.repaint();
        root.add(Main);
    }
    private static void ActivitySetting(){
        Setting = new JPanel();
        Setting.setLocation((int)(screen.getWidth()*0.03),0);
        Setting.setSize(new Dimension((int) (screen.getWidth()*0.03),(int) screen.getHeight()));
        Setting.setBackground(Color.GREEN);

        root.add(Setting);
    }
}