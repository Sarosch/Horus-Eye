package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;

import javax.swing.*;

public class ActivityDefault implements Activity {

    @Override
    public JPanel Layout(){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.add(new JLabel("Default"));
        return panel;
    }
}
