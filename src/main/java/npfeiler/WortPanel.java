package npfeiler;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class WortPanel extends JPanel {

    private JLabel l1,l2,l3,l4,l5,l6;
    private JTextField t;
    private JButton b1,b2,b3,b4,b5;

    /**
     * Konstruktor
     * @param controller
     */
    public WortPanel(WortController controller) {

        //GridLayouts erstellen
        BorderLayout border = new BorderLayout(1,1);
        this.setLayout(border);
        GridLayout grid1 = new GridLayout(2,1);
        GridLayout grid2 = new GridLayout(2,1);
        GridLayout grid3 = new GridLayout(2,3);
        GridLayout grid4 = new GridLayout(1,2);
        GridLayout grid5 = new GridLayout(2,1);

        //Panels erstellen und Layouts setzen
        JPanel p1 = new JPanel();
        p1.setLayout(grid1);

        JPanel p2 = new JPanel();
        p2.setLayout(grid2);

        JPanel p3 = new JPanel();
        p3.setLayout(grid3);

        JPanel p4 = new JPanel();
        p4.setLayout(grid4);

        JPanel p5 = new JPanel();
        p5.setLayout(grid5);

        //Labels, Buttons und Textfield setzen
        this.l1 = new JLabel("Welches Wort wird unten dargestellt (Eingabe zum Überprüfen)?");
        this.t = new JTextField(10);
        this.l2 = new JLabel("Richtige Wörter:");
        this.l3 = new JLabel("0");
        this.b1 = new JButton("Zurücksetzen");
        this.l4 = new JLabel("Anzahl Wörter:");
        this.l5 = new JLabel("0");
        this.b2 = new JButton("Wort hinzufügen");
        this.b3 = new JButton("Speichern TXT");
        this.b5 = new JButton("Speichern JSON");
        this.b4 = new JButton("Laden");
        this.l6 = new JLabel();

        //Action- & KeyListener für Buttons & Textfield
        b1.addActionListener(controller);
        b1.setActionCommand("b1");
        b2.addActionListener(controller);
        b2.setActionCommand("b2");
        b3.addActionListener(controller);
        b3.setActionCommand("b3");
        b4.addActionListener(controller);
        b4.setActionCommand("b4");
        t.addKeyListener(controller);
        b5.addActionListener(controller);
        b5.setActionCommand("b5");

        //Komponenten adden
        p1.add(l1);
        p1.add(t);

        p3.add(l2);
        p3.add(l3);
        p3.add(b1);
        p3.add(l4);
        p3.add(l5);
        p3.add(b2);

        p4.add(p5);
        p4.add(b4);

        p2.add(p3);
        p2.add(p4);

        p5.add(b3);
        p5.add(b5);

        this.add(p1, BorderLayout.NORTH);
        this.add(l6, BorderLayout.CENTER);
        this.add(p2, BorderLayout.PAGE_END);
    }

    /**
     * Setzt das Bild
     * @param url
     */
    public void setImage(String url) {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = icon.getImage(); // umwandeln in sein Image-Objekt
        image = image.getScaledInstance(250, 250,  Image.SCALE_SMOOTH); // skalieren auf gewünschte Größe
        this.l6.setIcon(new ImageIcon(image)); // anzeigen in einem JLabel
    }

    /**
     * Resetet die Statistik
     */
    public void reset() {
        l3.setText("0");
        l5.setText("0");
    }

    /**
     * Setzt die Statisik
     * @param richtig
     * @param falsch
     */
    public void setStatistik(int richtig, int falsch) {
        int gesamt = richtig + falsch;
        l3.setText("" + richtig);
        l5.setText("" + gesamt);
    }

    /**
     * Gibt den eingegeben Text zurück
     * @return String
     */
    public String wort() {
        return t.getText();
    }

    /**
     * Setzt die Statistik und färbt grün
     * @param richtig
     * @param falsch
     */
    public void richtigGeschrieben(int richtig, int falsch) {
        t.setBackground(Color.GREEN);
        int gesamt = richtig + falsch;
        l3.setText("" + richtig);
        l5.setText("" + gesamt);
    }

    /**
     * Setzt die Statistik und färbt rot
     * @param richtig
     * @param falsch
     */
    public void falschGeschrieben(int richtig, int falsch) {
        t.setBackground((Color.RED));
        int gesamt = richtig + falsch;
        l3.setText("" + richtig);
        l5.setText("" + gesamt);
    }

    /**
     * Gibt den Inhalt des Textfields zurück
     * @return String
     */
    public String getText() {
        return t.getText();
    }
}
