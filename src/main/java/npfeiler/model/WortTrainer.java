package npfeiler.model;

import java.util.Random;

public class WortTrainer {
    private WortListe liste;
    private int aktuell;
    private int richtig = 0;
    private int falsch = 0;

    /**
     * Parameterfreier Kontruktor
     */
    public WortTrainer() {
        this.liste = new WortListe();
        this.aktuell = 0;
    }

    /**
     * Konstruktor
     *
     * @param liste
     * @param aktuell
     */
    public WortTrainer(WortListe liste, int aktuell) {
        this.liste = liste;
        this.aktuell = aktuell;
    }

    //setter- und getter-Methoden
    public int getRichtig() {
        return this.richtig;
    }
    public int getFalsch() {
        return this.falsch;
    }
    public WortListe getListe() {
        return this.liste;
    }
    public void setListe(WortListe liste) {
        this.liste = liste;
    }
    public void setRichtig(int richtig) {
        this.richtig = richtig;
    }
    public void setFalsch(int falsch) {
        this.falsch = falsch;
    }
    public void setAktuell(int aktuell) {
        this.aktuell = aktuell;
    }

    /**
     * Gibt einen zufällien Worteintrag zurück
     *
     * @return liste.eintragByIndex(random)
     */
    public WortEintrag randomEintrag() {
        do {
            Random zufall = new Random();
            int random = zufall.nextInt(liste.laenge());
            this.aktuell = random;
        } while(liste.eintragByIndex(aktuell) == null);

        return liste.eintragByIndex(aktuell);
    }

    /**
     * Gibt den aktuell ausgewählten Worteintrag zurück
     *
     * @return liste.eintragByIndex(aktuell)
     */
    public WortEintrag currentEintrag() {
        return liste.eintragByIndex(aktuell);
    }

    /**
     * Überprüft, ob das aktuelle Wort dem übergebenen Wort entspricht
     *
     * @param wort
     * @return true/false
     */
    public boolean check(String wort) {
        if (currentEintrag().getWort().equals(wort)) {
            this.richtig += 1;
            return true;
        }
        this.falsch += 1;
        return false;
    }

    /**
     * Überprüft, ob das aktuelle Wort dem übergebenen Wort entspricht
     * und ignoriert dabei die Groß-Klein-Schreibung
     *
     * @param wort
     * @return
     */
    public boolean checkIgnoreCase(String wort) {
        String klein = wort.toLowerCase();
        if(currentEintrag().getWort().equalsIgnoreCase(wort)) {
            this.richtig += 1;
            return true;
        }
        this.falsch += 1;
        return false;
    }

    public WortEintrag getEintrag(String eingabe) {
        int indexSemi = eingabe.indexOf(";");
        String wort = eingabe.substring(0,(indexSemi));
        String url = eingabe.substring((indexSemi +1),(eingabe.length()));
        return new WortEintrag(wort, url);
    }
}