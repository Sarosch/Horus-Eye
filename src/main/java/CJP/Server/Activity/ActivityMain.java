package CJP.Server.Activity;

import CJP.Server.Main;
import CJP.Server.Suporte.Activity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ActivityMain implements Activity{
    private static JPanel Main;
    private static CardLayout cards;
    private static JPanel core;
    private static JPanel TabBar;
    private static Map<String,JButton> Tabs = new HashMap<>();
    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    @Override
    public JPanel Layout() {
        Main = new JPanel();
        Main.setLayout(null);
        Main.setBounds((int)(screen.getWidth()*0.03),0,(int) (screen.getWidth()-(screen.getWidth()*0.03)),(int) screen.getHeight());
        Main.setBackground(Color.BLACK);

        cards = new CardLayout();
        core = new JPanel(cards);
        core.setName("core");
        core.setBounds((int)(Main.getWidth()*0.01),(int)(Main.getHeight()*0.01),(int)(Main.getWidth()-(Main.getWidth()*0.02)),(int)(Main.getHeight()-(Main.getHeight()*0.02)));
        core.setOpaque(false);

        TabBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        TabBar.setMinimumSize(new Dimension(Main.getWidth(),30));
        TabBar.setSize(TabBar.getMinimumSize());
        TabBar.setLocation(0,screen.height - TabBar.getHeight());
        TabBar.setVisible(true);
        TabBar.setBackground(Color.GREEN);


        Main.add(TabBar);
        Main.add(core);

        addCard(new Yellow(),"Yellow");
        addCard(new ActivityDefault(), "Default");

        return Main;
    }
    public void addCard(Activity activity,String Name){
        JPanel panel = activity.Layout();
        panel.setName(Name);
        core.add(panel,Name);
        JButton button = new JButton(Name);
        button.setName(Name);
        button.setSize(new Dimension(30,30));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(String.format("Parent: %s ,Name: %s",core.getName(),button.getName()));
                cards.show(core,button.getName());
            }
        });
        TabBar.add(button);
        TabBar.repaint();
        TabBar.revalidate();
        if(!Tabs.containsKey(Name)){
            System.out.println(String.format("New Button add: %s in: %s",button,TabBar));
            Tabs.put(Name,button);
        }
    }

    private JPanel DefaultSetting(Activity activity,JPanel parent){
        JPanel panel = activity.Layout();
        panel.setBounds(0,0,parent.getWidth(),parent.getHeight());
        panel.setVisible(true);
        panel.setOpaque(false);


        return panel;
    }
}
