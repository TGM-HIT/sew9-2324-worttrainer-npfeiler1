package npfeiler.model;

import java.net.URL;

public class WortEintrag {
    private String wort;
    private String url;

    /**
     * Konstruktor
     * @param wort
     * @param url
     */
    public WortEintrag(String wort, String url) {
        try {
            if (wort.length() > 2) {
                this.wort = wort;
            } else {
                throw new IllegalArgumentException("Ungültige Eingabe!");
            }
            if (this.checkURL(url)) {
                this.url = url;
            } else {
                throw new IllegalArgumentException("Ungültige Eingabe!");
            }
        } catch(IllegalArgumentException exc) {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }

    /**
     * Überprüft, ob die übergebene URL gültig ist
     * @param url
     * @return
     */
    public static boolean checkURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch(Exception exc) {
            throw new IllegalArgumentException("Diese URL ist ungültig!");
        }
    }

    //Getter- und Setter-Methoden
    public String getWort() {
        return wort;
    }
    public void setWort(String wort) {
        try {
            this.wort = wort;
        } catch(IllegalArgumentException exc) {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        try {
            if(this.checkURL(url)) {
                this.url = url;
            }
        } catch(IllegalArgumentException exc) {
            throw new IllegalArgumentException("Ungültige Eingabe!");
        }
    }

    /**
     * Returniert eine formatierte Zeile mit dem Wort und dessen URL
     * @return t
     */
    @Override
    public String toString() {
        return this.wort + ";" + this.url + "\n";
    }
}
