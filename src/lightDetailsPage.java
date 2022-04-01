import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class lightDetailsPage extends JFrame{
    private JButton goBackIcon;
    private JLabel deskLight;
    private JLabel lampPicture;
    protected JToggleButton controlSwitch;
    private JLabel state;
    private JPanel lightDetailsPage;
    private JSlider brightnessSlider;
    private JLabel Brightness;

    public lightDetailsPage(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// to avoid closing help page but closing all pages
        this.setContentPane(lightDetailsPage);
        this.setIconImage(new ImageIcon("src/icons/logo.png").getImage());

        float[] info = DB.getLampInfo();
        float status = info[0];
        float brightness = info[1];
        state.setText(status==1 ? "ON" : "OFF");
        controlSwitch.setSelected(status==1);
        brightnessSlider.setValue((int) (100*brightness));

        controlSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controlSwitch.isSelected())
                {
                    state.setText("ON");
                    DB.updateLampState(1);
                }
                else
                {
                    state.setText("OFF");
                    DB.updateLampState(0);
                }
            }
        });
        goBackIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        brightnessSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(!brightnessSlider.getValueIsAdjusting())
                    DB.updateBrightness((float)brightnessSlider.getValue()/100);
                //controlSwitch.setSelected(brightnessSlider.getValue()>0);
                //state.setText((brightnessSlider.getValue()>0) ? "ON" : "OFF");
            }
        });
    }

        public static void main(String[] args)
        {
            JFrame lightDetailsPage = new lightDetailsPage("details page");
            lightDetailsPage.setVisible(true);
            lightDetailsPage.pack();
        }
}
