package npfeiler;

import javax.swing.*;

public class WortFrame extends JFrame {
    public WortFrame(WortPanel panel) {
        super("Worttrainer");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 100, 400, 300);
        this.setLocationRelativeTo(null);
        this.add(panel);
        this.setVisible(true);
    }
}
