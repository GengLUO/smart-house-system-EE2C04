import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class curtainsDetailsPage extends JFrame{
    private JButton goBackButton;
    private JToggleButton controlSwitch;
    private JLabel curtains;
    private JLabel curtainPicture;
    private JLabel state;
    private JPanel curtainsDetailsPage;

    public curtainsDetailsPage(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// to avoid closing help page but closing all pages
        this.setContentPane(curtainsDetailsPage);
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());

        int status = DB.getCurtainState();
        state.setText(status == 1 ? "ON" : "OFF");
        controlSwitch.setSelected(status == 1);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        controlSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controlSwitch.isSelected())
                {
                    state.setText("ON");
                    DB.updateCurtainState(1);
                }
                else
                {
                    state.setText("OFF");
                    DB.updateCurtainState(0);
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame curtainsDetailsPage = new curtainsDetailsPage("details page");
        curtainsDetailsPage.setVisible(true);
        curtainsDetailsPage.pack();
    }
}
