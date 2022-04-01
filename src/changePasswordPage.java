import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class changePasswordPage extends JFrame {
    private JButton confirm;
    private JButton cancel;
    private JLabel oldP;
    private JPasswordField newPassword;
    private JPasswordField repeatPassword;
    private JToggleButton see;
    private JLabel newP;
    private JPanel change;
    private JLabel repeatP;
    private JPasswordField oldPassword;
    private JLabel reminder;

    public changePasswordPage(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(change);
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldP = String.valueOf(oldPassword.getPassword());
                String newP = String.valueOf(newPassword.getPassword());
                String repeatP = String.valueOf(repeatPassword.getPassword());
                String userP = DB.getPassword();
                if(!newP.equals(repeatP))
                    JOptionPane.showMessageDialog(change,"The 2 passwords you entered are not the same","Warning",JOptionPane.WARNING_MESSAGE);
                else if(newP.length()<4)
                    JOptionPane.showMessageDialog(change,"Password must be at least four digits","Warning",JOptionPane.WARNING_MESSAGE);
                else if(!userP.equals(oldP))
                    JOptionPane.showMessageDialog(change,"Please verify your old password","Warning",JOptionPane.WARNING_MESSAGE);
                else
                    DB.updatePassword(newP);
            }
        });
        see.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (see.isSelected())
                {
                    oldPassword.setEchoChar((char)0);
                    newPassword.setEchoChar((char)0);
                    repeatPassword.setEchoChar((char)0);
                }
                else
                {
                    oldPassword.setEchoChar('•');
                    newPassword.setEchoChar('•');
                    repeatPassword.setEchoChar('•');
                }
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame change = new changePasswordPage("Change Password");
        App.showPage(change);
    }
}
