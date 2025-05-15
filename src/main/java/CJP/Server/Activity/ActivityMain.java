package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;

import javax.swing.*;
import java.awt.*;

public class ActivityMain implements Activity{
    private static JPanel Main;
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    @Override
    public JPanel Layout() {
        Main = new JPanel();
        Main.setLayout(null);
        Main.setBounds((int)(screen.getWidth()*0.03),0,(int) (screen.getWidth()-(screen.getWidth()*0.03)),(int) screen.getHeight());
        Main.setBackground(Color.BLACK);

        JPanel core = new JPanel(new CardLayout());
        core.setBounds((int)(Main.getWidth()*0.01),(int)(Main.getHeight()*0.01),(int)(Main.getWidth()-(Main.getWidth()*0.02)),(int)(Main.getHeight()-(Main.getHeight()*0.02)));
        core.setBackground(Color.YELLOW);
        core.setOpaque(true);

        JPanel TabBar = new JPanel(new FlowLayout(FlowLayout.LEFT));



        Main.add(core);

        return Main;
    }
}
