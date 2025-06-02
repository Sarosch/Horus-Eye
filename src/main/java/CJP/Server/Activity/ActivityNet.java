package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;

import javax.swing.*;
import java.awt.*;

public class ActivityNet implements Activity {
    private static JPanel Net;
    @Override
    public JPanel Layout(){
        Net = new JPanel();

        Net.setBackground(Color.GREEN);

        return Net;
    }
}
