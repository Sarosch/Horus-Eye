package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;

import javax.swing.*;
import java.awt.*;

public class ActivitySetting implements Activity {
    private static JPanel Setting;
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    @Override
    public JPanel Layout() {
        Setting = new JPanel();
        Setting.setLayout(null);
        Setting.setBounds((int)(screen.getWidth()*0.03),0,(int) (screen.getWidth()-(screen.getWidth()*0.03)),(int) screen.getHeight());
        Setting.setBackground(Color.green);

        return Setting;
    }
}
