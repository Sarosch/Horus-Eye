package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;
import CJP.Server.Suporte.Databank;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityNet implements Activity {
    private static JPanel Net;
    private String address;
    private String port;
    private static Databank bk = new Databank("jdbc:mysql://localhost:3306/HorusEye?useSSL=false&serverTimezone=UTC","Setting");
    @Override
    public JPanel Layout(){
        Net = new JPanel();
        bk.Start("root","Sansandri2003");

        if(bk.ValueExists("id=1","ip")){
            address = bk.Read("id=1","ip");
            port = bk.Read("id=1","port");
        }


        JPanel grid = new JPanel(new GridLayout(0,2,30,10));
        grid.setBorder(new LineBorder(Color.DARK_GRAY,1));
        grid.setLocation(20,20);
        grid.setPreferredSize(new Dimension(500,70));

        grid.add(new JLabel("IP: "));
        JTextPane Ip = new JTextPane();
        Ip.setText(address);
        grid.add(Ip);
        grid.add(new JLabel("PORT: "));
        JTextPane Port = new JTextPane();
        Port.setText(port);
        grid.add(Port);
        grid.add(new JLabel());
        JButton confirm = new JButton("Confirm");
        grid.add(confirm);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bk.EditIn("id=1","ip","\'"+Ip.getText()+"\'");
                bk.EditIn("id=1","port",Port.getText());
            }
        });

        Net.add(grid);

        return Net;
    }
}
