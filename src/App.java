import javax.swing.*;
import java.awt.*;

public class App {
    protected static int ID;

    public static void showPage(JFrame frame)
    {
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        JFrame login = new loginPage("Welcome to the smart home!");
        showPage(login);
    }
}
