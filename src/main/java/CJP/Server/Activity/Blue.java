package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;

import javax.swing.*;
import java.awt.*;

public class Blue implements Activity {

    @Override
    public JPanel Layout(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        return panel;
    }
}
