import javax.swing.*;

public class helpPage extends JFrame {
    private JTextArea helpText;
    private JPanel helpPage;

    public helpPage(String title) {
        super(title);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// to avoid closing help page but closing all pages
        this.setContentPane(helpPage);
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());

        helpText.setText(
                "The three buttons for three smart systems are for controlling whether to activate the automatic control function " +
                "(for example, if the switch of the desk lamp system is off,\n the lamp will still not come on even if " +
                "it is dark and people are sitting down)\n" +
                "\n" +
                "The buttons on the details page are for direct control of the desk lamp and curtain " +
                "(for example, when I turn on this switch, the desk lamp will light up regardless of the brightness and distance)\n"+
                "\n" +
                "BLUE icon means 'ON' and BLACK icon means 'OFF' \n\n"+
                "The system was designed by the EE2 course C04 team."
        );
        helpText.setEditable(false);
    }

    public static void main(String[] args)
    {
        JFrame helpPage = new helpPage("help page");
        helpPage.setVisible(true);
        helpPage.pack();
    }
}
