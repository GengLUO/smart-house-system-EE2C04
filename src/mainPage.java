import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainPage extends JFrame{ //1
    private static String name;
    private static String[] states;
    private JPanel mainPage;
    private JLabel someonesHome;
    private JLabel lock;
    private JLabel deskLight;
    private JLabel curtains;
    private JLabel lockState;
    private JLabel lightState;
    private JLabel curtainsState;
    private JButton help;
    private JLabel lockIcon;
    private JToggleButton lockSwitch;
    private JLabel lightIcon;
    private JToggleButton lightSwitch;
    private JLabel curtainsIcon;
    private JToggleButton curtainsSwitch;
    private JPanel lockPanel;
    private JButton lightInformation;
    private JButton curtainsInformation;
    private JMenuBar menu;
    private JLabel ceilingLight;
    private JToggleButton ceilingSwitch;
    private JLabel ceilingLightState;
    private JLabel ceilingLightIcon;

    public mainPage(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPage);                             //2
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());
        name = DB.getName();
        String[] info = DB.getSystemsStates();

        JMenu topMenu = new JMenu();
        topMenu.setIcon(new ImageIcon("src/icons/icons8-more-30.png"));
        menu.add(topMenu);
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");
        topMenu.add(fileMenu);
        topMenu.add(helpMenu);
        JMenuItem userInfo = new JMenuItem("User Info");
        JMenuItem logOut = new JMenuItem("Log Out");
        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(userInfo);
        fileMenu.add(logOut);
        fileMenu.add(exit);
        JMenuItem softwareHelp = new JMenuItem("Software Help");
        helpMenu.add(softwareHelp);

        someonesHome.setText(name + "'s home");
        lockState.setText(info[0]);
        lightState.setText(info[1]);
        curtainsState.setText(info[2]);
        ceilingLightState.setText(info[3].equals("1") ? "ON" : "OFF");
        lockSwitch.setSelected(info[0].equals("Online"));
        lightSwitch.setSelected(info[1].equals("Online"));
        curtainsSwitch.setSelected(info[2].equals("Online"));
        ceilingSwitch.setSelected(info[3].equals("1"));//the button will be selected if we've activated the system

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame helpPage = new helpPage("help page");
                App.showPage(helpPage);
            }
        });

        lightInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame lightDetailsPage = new lightDetailsPage("details page");
                App.showPage(lightDetailsPage);
            }
        });

        lightSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newEnlp = lightSwitch.isSelected()? "Online" : "Offline";
                DB.updateRealTimeControl("enlp",newEnlp);
                lightState.setText(newEnlp);
            }
        });

        curtainsInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame curtainsDetailsPage = new curtainsDetailsPage("details page");
                App.showPage(curtainsDetailsPage);
            }
        });

        curtainsSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newEnct = curtainsSwitch.isSelected()? "Online" : "Offline";
                DB.updateRealTimeControl("enct",newEnct);
                curtainsState.setText(newEnct);
            }
        });

        lockSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newEnlk = lockSwitch.isSelected()? "Online" : "Offline";
                DB.updateRealTimeControl("enlk",newEnlk);
                lockState.setText(newEnlk);
            }
        });

        ceilingSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ceilingSwitch.isSelected())
                {
                    ceilingLightState.setText("ON");
                    DB.updateCeilingLightState(1);
                }
                else
                {
                    ceilingLightState.setText("OFF");
                    DB.updateCeilingLightState(0);
                }
            }
        });

        userInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame profile = new profilePage("profile  Page");
                App.showPage(profile);
            }
        });

        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JFrame login = new loginPage("Welcome to the smart home!");
                App.showPage(login);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        softwareHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame helpPage = new helpPage("help page");
                App.showPage(helpPage);
            }
        });

    }

    public static void main(String[] args)
    {
        JFrame mainPage = new mainPage("main page");
        App.showPage(mainPage);
    }                                                        //3
}