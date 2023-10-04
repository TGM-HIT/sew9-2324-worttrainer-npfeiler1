package npfeiler.model;

import java.util.ArrayList;
import java.util.List;

public class WortTest {
    public static void main(String[] args) {
        //Erstellen von 4 Worteinträgen
        WortEintrag eintrag1 = new WortEintrag("Haus", "https://www.haus.at");
        WortEintrag eintrag2 = new WortEintrag("Baum", "https://www.baum.at");
        WortEintrag eintrag3 = new WortEintrag("Auto", "https://www.auto.at");
        WortEintrag eintrag4 = new WortEintrag("Sonne", "https://www.sonne.at");

        //Erstellen einer Wortliste
        List<WortEintrag> array = new ArrayList<>();
        WortListe liste = new WortListe(array);

        //Hinzufügen von Worteinträgen
        liste.addWort(eintrag1);
        liste.addWort(eintrag2);
        liste.addWort(eintrag3);
        liste.addWort(eintrag4);

        System.out.println(liste.toString());

        //Löschen eines Eintrags
        liste.deleteEintrag(1);
        System.out.println(liste.toString());

        //Erstellen eines Worttrainers
        WortTrainer trainer = new WortTrainer(liste, 0);

        //Ausgeben eines zufälligen Eintrags
        System.out.println(trainer.randomEintrag().toString());

        //Ausgeben des aktuellen Eintrags
        System.out.println(trainer.currentEintrag().toString());

        //Überprüft Worte auf ihre Richtigkeit
        trainer.check("Haus");
        trainer.check("Baum");
        trainer.check("Auto");
        trainer.check("Sonne");
        trainer.checkIgnoreCase("HAUS");
        trainer.checkIgnoreCase("BAUM");
        trainer.checkIgnoreCase("AUTO");
        trainer.checkIgnoreCase("SONNE");

        System.out.println("Anzahl der abgefragten Worte: " + (trainer.getRichtig()+ trainer.getFalsch()) +
                           "\nRichtig: " + trainer.getRichtig() + "\nFalsch: " + trainer.getFalsch());

        TXTSpeichern x = new TXTSpeichern();
        x.speichern(trainer, "Worttrainer.txt");
        WortTrainer trainer1 = new WortTrainer(liste, 0);
        trainer1.setRichtig(-1);
        x.speichern(trainer1, "Test.txt");
        x.laden();

        //Durchschnittliche Wortlänge & filtern
        System.out.println("\nDurchschnittliche Wortlänge: " + trainer.getListe().averageWordLength());
        trainer.getListe().filter(4);
        System.out.println(liste.toString());
    }
}
