package CJP.Server.Activity;

import CJP.Server.Suporte.Activity;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class ActivitySetting implements Activity {
    private static JPanel Setting;
    private static JPanel SettingCategory;
    private static JPanel Firsth;
    private static JLabel FirsthLabel;
    private static JPanel Second;
    private static JLabel SecondLabel;
    private static String lastfocus;
    private static Dimension PanelArea;
    private static Map<String,Dimension> PreSetDimension = new HashMap<>();
    private static Map<String,Point> PreSetPoint = new HashMap<>();
    private static Map<String,JPanel> panels = new HashMap<>();

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

        int zero = SettingCategory.getWidth();

        PreSetPoint.put("Firsth",new Point(zero,0));
        PreSetPoint.put("Second",new Point(zero,(int)PanelArea.getHeight()/2));

        Firsth = DefaultSetting(new ActivityNet(),"Firsth");
        Second = DefaultSetting(new ActivityNet(), "Second");

        Setting.add(Firsth);
        Setting.add(Second);

        Setting.add(SettingCategory);

        addCategory("Network",new ActivityNet());
        addCategory("Blue",new Blue());
        addCategory("Yellow",new Yellow());

        return Setting;
    }
    private void addCategory(String CategoryName, Activity activity){
        JButton button = new JButton(CategoryName);
        button.setBounds(0,(int)(SettingCategory.getComponentCount()*(screen.getWidth()*0.03)),SettingCategory.getWidth(),(int)(screen.getWidth()*0.03));
        button.addActionListener(e -> {
            if(lastfocus != null) {
                while (!panels.containsKey(lastfocus)) ;
                System.out.println(String.format("Transition start in: %s for: %s",lastfocus,activity));
                Setting.add(DefaultSetting(activity, lastfocus));
            }
        });
        SettingCategory.add(button);
    }

    private JPanel DefaultSetting(@NotNull Activity activity, String Name){
        System.out.println(String.format("New Panel: %s : %s",Name,activity));
        JPanel panel = activity.Layout();
        panel.setName(Name);
        panel.setVisible(true);
        panel.setOpaque(true);
        Point OldPoint;
        Dimension OldDimension;
        if(panels.containsKey(Name)){
            JPanel old = panels.get(Name);
            panels.replace(Name,old,panel);
            OldPoint = old.getLocation();
            OldDimension = old.getSize();
            panel.setSize(OldDimension);
            panel.setLocation(OldPoint);
            old.getParent().remove(old);
        }else{
            panels.put(Name,panel);
            if(PreSetDimension.containsKey(Name) && PreSetPoint.containsKey(Name)){
                panel.setLocation(PreSetPoint.get(Name));
                panel.setSize(PreSetDimension.get(Name));
            }
        }

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Component source = e.getComponent();
                    lastfocus = source.getName();
                    System.out.println(String.format("Panel In Focus: %s",source.getName()));
            }
        };
        panel.addMouseListener(mouseListener);
        Setting.repaint();
        return panel;
    }
}
