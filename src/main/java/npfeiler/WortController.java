package npfeiler;

import npfeiler.model.*;

import java.awt.event.*;

public class WortController implements ActionListener, KeyListener {

    private WortFrame frame;
    private WortPanel panel;
    private WortTrainer trainer;
    private TXTSpeichern TXTspeichern;
    private npfeiler.model.JSONSpeichern JSONSpeichern;

    private TXTSpeichern TXTLaden;
    private JSONSpeichern JSONLaden;

    /**
     * Konstrukter
     */
    public WortController() {
        this.panel = new WortPanel(this);
        this.frame = new WortFrame(this.panel);
        this.trainer = new WortTrainer();
        this.TXTspeichern = new TXTSpeichern();
        this.JSONSpeichern = new JSONSpeichern();
        this.TXTLaden = new TXTSpeichern();
        this.JSONLaden = new JSONSpeichern();
        this.trainer.getListe().addWort(new WortEintrag("Hund", "https://www.mera-petfood.com/files/_processed_/b/b/csm_iStock-521697453_bb8fbb7807.jpg"));
        this.trainer.getListe().addWort(new WortEintrag("Katze", "https://einfachtierisch.de/media/cache/article_main_image/cms/2015/09/Katze-lacht-in-die-Kamera-shutterstock-Foonia-76562038.jpg?266705"));
        this.trainer.getListe().addWort(new WortEintrag("Schwein", "https://assets.puzzlefactory.pl/puzzle/192/732/original.jpg"));
        this.trainer.getListe().addWort(new WortEintrag("Tiger", "https://downloadwap.com/thumbs2/wallpapers/p2ls/2019/animals/43/5c2cf50e13580048.jpg"));
        panel.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6aX7pEdLCvDNKRSkTndg5XCtM4YYQavSnGw&usqp=CAU");
    }

    /**
     * Main-Methode
     * @param args
     */
    public static void main(String[] args) {
        new WortController();
    }

    /**
     * Resetet die Statistik sowie den aktuellen WortEintrag
     */
    public void reset() {
        this.trainer.setFalsch(0);
        this.trainer.setRichtig(0);
        this.panel.reset();
    }

    /**
     * Setzt einen neuen WortEintrag
     */
    public void refresh() {
        WortEintrag wort = trainer.randomEintrag();
        panel.setImage(wort.getUrl());
    }

    /**
     * Prüft, welcher Button betätigt wurde
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("b1")) {
            this.panel.reset();
            this.reset();
            refresh();
        } else if(e.getActionCommand().equals("b2")) {
            this.trainer.getListe().addWort(trainer.getEintrag(panel.getText()));
        } else if(e.getActionCommand().equals("b3")) {
            this.TXTspeichern.speichern(trainer);
        } else if(e.getActionCommand().equals("b5")) {
            this.JSONSpeichern.speichern(trainer);
        } else if(e.getActionCommand().equals("b4")) {
            this.trainer = this.TXTLaden.laden();
            panel.setStatistik(trainer.getRichtig(), trainer.getFalsch());
        } else if(e.getActionCommand().equals("b6")) {
            this.trainer = this.JSONLaden.laden();
            panel.setStatistik(trainer.getRichtig(), trainer.getFalsch());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Vergleicht die Eingabe mit dem aktuellen WortEintrag, wenn Enter gedrückt wurde
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(this.trainer.check(panel.getText())) {
                panel.richtigGeschrieben(this.trainer.getRichtig(), this.trainer.getFalsch());
                refresh();
            } else {
                panel.falschGeschrieben(this.trainer.getRichtig(), this.trainer.getFalsch());
                refresh();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
