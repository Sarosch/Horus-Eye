package CJP.Server.Suporte;

import javax.swing.*;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Frame{
    private JFrame root;
    private boolean OptionStart;
    private JPanel OptionBar;
    private Orientation BarManipulator;
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

    private Map<String,JPanel> Activitys = new HashMap<>();

    /**
     * Set the {@link JFrame} and the JFrame Manipulator
     *
     * @param Title - {@link String} Set the JFrame name
     */
    public Frame(String Title){
        root = new JFrame(Title);
        root.setSize(screen);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        root.setUndecorated(true);
        root.setVisible(true);



    }

    /**
     *
     * @return - Root {@link Container}/{@link JFrame} for external manipulation
     */
    public JFrame getRoot(){
        return root;
    }

    /**Set the Root {@link Container}/{@link JFrame} with external pre-Setting
     *
     * @param root - {@link JFrame} Frame with external pre-Setting
     */
    public void setRoot(JFrame root){
        this.root = root;
    }

    /**Add the {@link Activity}/{@link JPanel} for auto manipulation and Index in control system
     *
     * @param ActivityName - {@link String} with name for calling in manipulattion methods
     * @param Activity - {@link JPanel} for add in root and index in control system
     */
    public void AddActivity(String ActivityName,JPanel Activity){
        Activitys.put(ActivityName,Activity);
        Activity.setName(ActivityName);
        root.add(Activity);
        if(ActivityName != "Main" || ActivityName != "main"){
            Activity.setVisible(false);
        }else{
            Activity.setVisible(true);
        }
    }

    /**Add the {@link Activity}/{@link JPanel} for auto manipulation and Index in control system
     *
     * @param ActivityName - {@link String} with name for calling in manipulattion methods
     * @param Activity - {@link Activity} for add in root and index in control system
     */
    public void AddActivity(String ActivityName,Activity Activity){
        JPanel activity = Activity.Layout();
        Activitys.put(ActivityName,activity);
        activity.setName(ActivityName);
        root.add(activity);
        if(ActivityName != "Main" || ActivityName != "main"){
            activity.setVisible(false);
        }else{
            activity.setVisible(true);
        }

    }

    /**
     *
     * @return - {@link String} -  list with Activity names for calling
     */
    public String[] ActivityList(){
        System.out.println("Keys:");
        for(String key: Activitys.keySet()){
            System.out.println("Key String: " + key);
        }
        return Activitys.keySet().toArray(new String[0]);
    }

    /** Set {@link Activity} visible and disable other activity
     *
     * @param ForActivity - {@link Activity} for set Visible
     */
    public void Alter(String ForActivity){
        if(Activitys.containsKey(ForActivity)){
            for(JPanel pane : Activitys.values()){
                System.out.println(String.format("Activity Down: %s",pane.getName()));
                pane.setVisible(false);
            }
            JPanel activity = Activitys.get(ForActivity);
            activity.setVisible(true);
            System.out.println(String.format("Set Activity:{Name: %s, Size:{H: %s, W: %s}, X: %s, Y:%s}",activity.getName(),activity.getHeight(),activity.getWidth(),activity.getAlignmentX(),activity.getAlignmentY()));
        }
    }

    /**Add the pre-Setting {@link  JPanel} For Assistem Bar Option
     *
     * Pre-Setting required:
     *  |-> Size
     *  |-> Possition
     *  |-> Layout = null
     *
     * @param OptionBar - {@link JPanel} with pre-Set
     * @param Orientation - set the {@link Orientation} type {horizontal - vertical}
     * @param StartIn -  Set if button {@link StartIn} { Up/left = Down/Right}
     * @param ExitButtonIcon - Set the Icon for Exit Button(FrameClosser)
     */

    public void setOptionBar(JPanel OptionBar,Orientation Orientation, StartIn StartIn, ImageIcon ExitButtonIcon){
        this.OptionBar = OptionBar;
        this.BarManipulator = Orientation;
        this.OptionStart = StartIn.equals(StartIn.Up);
        OptionBar.setLayout(null);
        System.out.println(String.format("OptionBar:{ Size:{ H: %s, W: %s}, X: %s, Y: %s}",OptionBar.getHeight(),OptionBar.getWidth(),OptionBar.getAlignmentX(),OptionBar.getAlignmentY()));
        root.add(this.OptionBar);
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Component comp : OptionBar.getComponents()){
                    if(comp instanceof JButton){
                        comp.validate();
                        comp.repaint();
                    }
                }
            }
        });
        timer.start();


        JButton exitbutton = new JButton();


        switch (Orientation){
            case Horizontal -> {
                int size = OptionBar.getHeight();
                System.out.println(String.format("Size value Set: %s",String.valueOf(size)));

                exitbutton.setIcon(new ImageIcon(ExitButtonIcon.getImage().getScaledInstance(size,size,Image.SCALE_SMOOTH)));
                if(OptionStart){
                    System.out.println(String.format("OptionStartIn: %s","down"));
                    exitbutton.setBounds((OptionBar.getWidth()-size),0,size,size);
                    System.out.println(String.format("64-Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",size,size,(OptionBar.getWidth()-size),0));
                } else {
                    System.out.println(String.format("OptionStartIn: %s","up"));
                    exitbutton.setBounds(0,0,size,size);
                    System.out.println(String.format("68-Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",size,size,0,0));
                }
                System.out.println(String.format("Exit Button{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",exitbutton.getHeight(),exitbutton.getWidth(),exitbutton.getAlignmentY(),exitbutton.getAlignmentY()));

            }
            case Vertical -> {
                int size = OptionBar.getWidth();
                exitbutton.setIcon(new ImageIcon(ExitButtonIcon.getImage().getScaledInstance(size,size,Image.SCALE_SMOOTH)));
                exitbutton.setPreferredSize(new Dimension(size,size));
                if(OptionStart){
                    System.out.println(String.format("OptionStartIn: %s","down"));
                    exitbutton.setBounds(0,(OptionBar.getHeight()-size),size,size);
                    System.out.println(String.format("80-Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",size,size,0,(OptionBar.getHeight()-size)));
                } else {
                    System.out.println(String.format("OptionStartIn: %s","up"));
                    exitbutton.setBounds(0,0,size,size);
                    System.out.println(String.format("84-Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",size,size,0,0));
                }
                System.out.println(String.format("Exit Button{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",exitbutton.getHeight(),exitbutton.getWidth(),exitbutton.getAlignmentY(),exitbutton.getAlignmentY()));

            }
        }
        exitbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        OptionBar.add(exitbutton);
    }

    /**Add new Button Option with Icon and your action
     *
     * @param Icon - {@link ImageIcon} for presentation in Layout
     * @param action - {@link ActionListener} for execute in click
     */

    public void AddOption(ImageIcon Icon, ActionListener action){
        int button_Size = 0;
        int Option_Size = OptionBar.getComponentCount()-1;

        switch (BarManipulator.ordinal()){
            case 0:
                button_Size = OptionBar.getWidth();
                break;
            case 1:
                button_Size = OptionBar.getHeight();
        }
        System.out.println(String.format("lateral Size: %s, Components count: %c, Manipulator: %S, StartIn: %S, Add in progress",button_Size,Option_Size,BarManipulator,OptionStart));


        JButton button = new JButton(new ImageIcon(Icon.getImage().getScaledInstance(button_Size,button_Size,Image.SCALE_SMOOTH)));
        button.addActionListener(action);

        switch (BarManipulator) {
            case BarManipulator.Horizontal:
                if(OptionStart){
                    button.setBounds((Option_Size*button_Size),0,button_Size,button_Size);
                    System.out.println(String.format("OptionStartIn: %s","up"));
                    System.out.println(String.format("Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button_Size,button_Size,(Option_Size*button_Size),0));
                    System.out.println(String.format("Button Setting In:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button.getHeight(),button.getWidth(),button.getAlignmentX(),button.getAlignmentY()));
                }else{
                    button.setBounds((OptionBar.getWidth()-((Option_Size+1)*button_Size)),0,button_Size,button_Size);
                    System.out.println(String.format("OptionStartIn: %s","down"));
                    System.out.println(String.format("Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button_Size,button_Size,(OptionBar.getWidth()-((Option_Size+1)*button_Size)),0));
                    System.out.println(String.format("Button Setting In:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button.getHeight(),button.getWidth(),button.getAlignmentX(),button.getAlignmentY()));
                }
                break;
            case BarManipulator.Vertical:
                if(OptionStart){
                    button.setBounds(0,(Option_Size*button_Size),button_Size,button_Size);
                    System.out.println(String.format("OptionStartIn: %s","up"));
                    System.out.println(String.format("Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button_Size,button_Size,0,(Option_Size*button_Size)));
                    System.out.println(String.format("Button Setting In:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button.getHeight(),button.getWidth(),button.getAlignmentX(),button.getAlignmentY()));
                }else{
                    button.setBounds(0,(OptionBar.getHeight()-((Option_Size+1)*button_Size)),button_Size,button_Size);
                    System.out.println(String.format("OptionStartIn: %s","down"));
                    System.out.println(String.format("Button Pre-Setting For:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button_Size,button_Size,0,(OptionBar.getHeight()-((Option_Size+1)*button_Size))));
                    System.out.println(String.format("Button Setting In:{ Size:{ H:%s, W: %s}, X: %s, Y: %s,}",button.getHeight(),button.getWidth(),button.getAlignmentX(),button.getAlignmentY()));
                }
                break;
        }
        System.out.println("Button Add");
        OptionBar.add(button);
    }

    public enum Orientation{
        Vertical,
        Horizontal
    }
    public enum StartIn{
        Up,
        Down;
    }
}
