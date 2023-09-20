package npfeiler.model;

import java.util.*;

public class WortListe {
    private List<WortEintrag> array;

    public WortListe() {
        this.array = new ArrayList<>();
    }

    public WortListe(List<WortEintrag> array) {
        this.array = array;
    }

    /**
     * Fügt einen Worteintrag am nächstmöglichen Index hinzu
     * @param wortEintrag
     */
    public void addWort(WortEintrag wortEintrag) {
        if(wortEintrag.getWort().length() > 2) {
            array.add(wortEintrag);
        } else {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }

    /**
     * Gibt den gewünschten Worteintrag zurück
     * @param index
     * @return worteintrag
     */
    public WortEintrag eintragByIndex(int index) {
        try {
            return array.get(index);
        } catch(NumberFormatException exc) {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }

    /**
     * Löscht den gewünschten Worteintrag
     * @param index
     * @return true/false
     */
    public boolean deleteEintrag(int index) {
        try {
            if (array.get(index) == null) {
                return false;
            } else {
                array.remove(index);
                return true;
            }
        } catch(NumberFormatException exc) {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }

    /**
     * Gibt die Länge des Arrays zurück
     * @return length
     */
    public int laenge() {
        return this.array.size();
    }

    /**
     * Returniert eine formatierte Liste mit allen Worteinträgen
     * @return t
     */
    @Override
    public String toString() {
        String t = "";
        for(int i = 0; i < array.size(); i++) {
            if(array.get(i) != null) {
                t += array.get(i).toString();
            }
        }
        return t;
    }

    public double averageWordLength() {
        double average = 0;
        int anzahl = 0;

        for(WortEintrag w: array) {
            average += w.getWort().length();
            anzahl++;
        }
        return average / anzahl;
    }

    public void filter(int laenge) {
        Iterator<WortEintrag> it = array.iterator();
        while(it.hasNext()) {
            if(it.next().getWort().length() == laenge) {
                it.remove();
            }
        }
    }
}
