package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ActivitySetting implements Activity {
    private static JPanel Setting;
    private static JPanel SettingCategory;
    private static JScrollPane Firsth;
    private static JLabel FirsthLabel;
    private static JScrollPane Second;
    private static JLabel SecondLabel;
    private static String lastfocus;
    private static Dimension PanelArea;
    private static Map<String,Dimension> PreSetDimension = new HashMap<>();
    private static Map<String,Point> PreSetPoint = new HashMap<>();
    private static Map<String,JScrollPane> panels = new HashMap<>();

    private static Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    @Override
    public JPanel Layout() {
        Setting = new JPanel();
        Setting.setLayout(null);
        Setting.setBounds((int)(screen.getWidth()*0.03),0,(int) (screen.getWidth()-(screen.getWidth()*0.03)),(int) screen.getHeight());
        Setting.setBackground(Color.green);

        SettingCategory = new JPanel(null);
        SettingCategory.setBounds(0,0,(int)(screen.getWidth()*0.15),(int)screen.getHeight());
        SettingCategory.setBackground(Color.cyan);

        PanelArea = new Dimension(Setting.getWidth() - SettingCategory.getWidth(),Setting.getHeight());

        PreSetDimension.put("Firsth",new Dimension((int)PanelArea.getWidth(),(int)PanelArea.getHeight()/2));
        PreSetDimension.put("Second",new Dimension((int)PanelArea.getWidth(),(int)PanelArea.getHeight()/2));


        PreSetPoint.put("Firsth",new Point(0,0));
        PreSetPoint.put("Second",new Point(0,(int)PanelArea.getHeight()/2));

        Firsth = DefaultSetting(new ActivityNet(),"Firsth");
        Second = DefaultSetting(new ActivityNet(), "Second");

        Setting.add(Firsth);
        Setting.add(Second);

        Setting.add(SettingCategory);

        return Setting;
    }
    private void addCategory(String CategoryName, Activity activity){
        JButton button = new JButton(CategoryName);
        button.setBounds(0,(int)(SettingCategory.getComponentCount()*(screen.getWidth()*0.03)),SettingCategory.getWidth(),(int)(screen.getWidth()*0.03));
        button.addActionListener(e -> {

        });
        SettingCategory.add(button);
    }

    private JScrollPane DefaultSetting(@NotNull Activity activity, String Name){
        JPanel panel = activity.Layout();
        panel.setName(Name);


        JScrollPane scroll = new JScrollPane();
        panel.setSize(scroll.getSize());
        Point OldPoint;
        Dimension OldDimension;
        if(panels.containsKey(Name)){
            JScrollPane old = panels.get(Name);
            panels.replace(Name,old,scroll);
            OldPoint = old.getLocation();
            OldDimension = old.getSize();
            scroll.setSize(OldDimension);
            scroll.setLocation(OldPoint);
            old.getParent().remove(old);
        }else{
            panels.put(Name,scroll);
            if(PreSetDimension.containsKey(Name) && PreSetPoint.containsKey(Name)){
                scroll.setLocation(PreSetPoint.get(Name));
                scroll.setSize(PreSetDimension.get(Name));
            }
        }

          scroll.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                lastfocus = panel.getName();
                System.out.println(String.format("Panel in Focus: %s",panel.getName()));
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        return scroll;
    }
}
