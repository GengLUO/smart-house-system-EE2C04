import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profilePage extends JFrame {
    private JLabel userId;
    private JLabel Id;
    private JLabel userName;
    private JLabel accountDetails;
    private JLabel Email;
    private JPanel profile;
    private JTextField mail;
    private JTextField theName;
    private JButton editProfile;
    private JButton changePassword;
    private JButton confirm;
    private JButton cancel;
    private JPanel edit1;
    private JPanel option;

    public profilePage(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(profile);
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());

        String[] info = DB.getUserInfo();
        Id.setText(String.valueOf(App.ID));
        theName.setText(info[0]);
        mail.setText(info[1]);

        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit1.setVisible(false);
                option.setVisible(true);
                theName.setEditable(true);
                mail.setEditable(true);
            }
        });
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame change = new changePasswordPage("Change Password");
                App.showPage(change);
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit1.setVisible(true);
                option.setVisible(false);
                theName.setEditable(false);
                mail.setEditable(false);
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB.updateUserinfo(theName.getText(),mail.getText());
                edit1.setVisible(true);
                option.setVisible(false);
                theName.setEditable(false);
                mail.setEditable(false);
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame profile = new profilePage("profile  Page");
        App.showPage(profile);
    }
}
