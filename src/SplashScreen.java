import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

class SplashScreen extends JFrame {
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

        panel.setPreferredSize(new Dimension(500, 300));
        panel.setBackground(Color.DARK_GRAY);

        JLabel labelName = new JLabel("F1 Championship Manager");
        labelName.setFont(new Font("Arial", Font.BOLD, 15));
        labelName.setForeground(Color.WHITE);
        panel.add(labelName);
        ImageIcon img = new ImageIcon(Objects.requireNonNull(getClass().getResource("logo.png")));
        JLabel imglabel = new JLabel(img);
        panel.add(imglabel);
        imglabel.setBounds(0, 0, 500, 300);
        panel.setLayout(null);
        setUndecorated(true);

        JLabel labelBy = new JLabel("© 2021 Avishka Kavinda(w1810195). All Rights Reserved");
        labelBy.setFont(new Font("Calibri", Font.BOLD, 12));
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
        new MainFrame("Formula 1 Championship Manager").setVisible(true);
    }
}