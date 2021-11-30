import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class SplashScreen extends JWindow {

    static JProgressBar progressBar = new JProgressBar();
    static int count = 1, TIMER_PAUSE = 25, PROGBAR_MAX = 100;
    static Timer progressBarTimer;
    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            if (PROGBAR_MAX == count) {
                progressBarTimer.stop();
                SplashScreen.this.setVisible(false);
                createAndShowFrame();
            }
            count++;
        }
    };

    public SplashScreen() {
        Container container = getContentPane();

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder());

        panel.setPreferredSize(new Dimension(300, 50));
        panel.setBackground(Color.DARK_GRAY);

        JLabel labelName = new JLabel("F1 Championship Manager");
        labelName.setFont(new Font("Arial", Font.BOLD, 15));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);

        JLabel labelBy = new JLabel("© 2021 Avishka Kavinda(w1810195). All Rights Reserved");
        labelBy.setFont(new Font("Arial", Font.ITALIC, 12));
        container.setBackground(Color.DARK_GRAY);
        labelBy.setForeground(Color.WHITE);
        container.add(labelBy, BorderLayout.CENTER);

        container.add(panel, BorderLayout.NORTH);
        progressBar.setMaximum(PROGBAR_MAX);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.RED);
        container.add(progressBar, BorderLayout.SOUTH);
        pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        startProgressBar();
    }
    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }
    private void createAndShowFrame() {
        MainFrame mainWindow = new MainFrame("Formula 1 Championship Manager");
        mainWindow.setVisible(true);
    }
}


public class Main {
    static Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    public static void main(String[] args) {
        SplashScreen splashScreen = new SplashScreen();
        // Run the program
        championshipManager.menu();
    }
}
