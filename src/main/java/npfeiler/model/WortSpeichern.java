package npfeiler.model;

import java.io.*;
import java.util.ArrayList;

public class WortSpeichern {

    /**
     * Speichert einen Worttrainer in einer Textdatei
     * @param trainer
     * @param pfad
     */

    public void speichern(WortTrainer trainer, String pfad){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pfad));
            writer.write(trainer.getListe().toString());
            writer.write("\nFalsch: " + trainer.getFalsch());
            writer.write("\nRichtig: " + trainer.getRichtig());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Speichert einen Worttrainer in einer Testdatei (Default-Pfad)
     * @param wt
     */
    public void speichern(WortTrainer wt){
        speichern(wt,"Worttrainer.txt");
    }

    /**
     * Lädt einen Worttrainer aus einer Textdatei
     * @param pfad
     * @return
     */
    public WortTrainer laden(String pfad){
        WortListe liste = new WortListe(new ArrayList<>());
        WortTrainer trainer = new WortTrainer(liste, 0);
        int falsch = -1;
        int richtig = -1;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pfad));
            String line;
            for(int i = 0;(line = reader.readLine()) != null; i++){
                if (line.contains(";")){
                    liste.addWort(trainer.getEintrag(line));
                } else if (line.indexOf("F") == 0){
                    String t = line.substring(8,(line.length()));
                    falsch = Integer.parseInt(t);
                } else if (line.indexOf("R") == 0){
                    richtig = Integer.parseInt(line.substring(9,(line.length())));
                }
                trainer.setFalsch(falsch);
                trainer.setRichtig(richtig);
                trainer.setListe(liste);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trainer;
    }

    /**
     * Lädt einen Worttrainer aus einer Textdatei (Default-Pfad)
     * @return
     */
    public WortTrainer laden() {
        return laden("Worttrainer.txt");
    }
}
