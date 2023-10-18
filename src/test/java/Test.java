import npfeiler.model.TXTSpeichern;
import npfeiler.model.JSONSpeichern;
import npfeiler.model.WortEintrag;
import npfeiler.model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    private WortTrainer trainer;

    @BeforeEach
    public void clear() {
        this.trainer = new WortTrainer();
    }

    /**
     * Testet die "reset"-Methode in WortTrainer.java
     */
    @org.junit.jupiter.api.Test
    public void testReset() {
        trainer.setRichtig(3);
        trainer.setFalsch(3);

        trainer.reset();

        assertEquals(trainer.getRichtig(), 0);
        assertEquals(trainer.getRichtig(), 0);
    }

    /**
     * Testet die "addWort"-Methode in WortListe.java
     */
    @org.junit.jupiter.api.Test
    public void testAddWort() {
        trainer.getListe().addWort(new WortEintrag("Haus", "https://www.haus.at"));
        assertEquals(trainer.getListe().laenge(), 1);
    }

    /**
     * Testet die "deleteWort"-Methode in WortListe.java
     */
    @org.junit.jupiter.api.Test
    public void testDeleteWort() {
        trainer.getListe().addWort(new WortEintrag("Haus", "https://www.haus.at"));
        trainer.getListe().deleteEintrag(0);
        assertEquals(trainer.getListe().laenge(), 0);
    }

    /**
     * Testet die "check"-Methode in WortTrainer.java
     */
    @org.junit.jupiter.api.Test
    public void testCheck() {
        trainer.getListe().addWort(new WortEintrag("Haus", "https://www.haus.at"));
        assertTrue(trainer.check("Haus"));
    }

    /**
     * Testet die "getEintrag"-Methode in WortTrainer.java
     */
    @org.junit.jupiter.api.Test
    public void testGetEintrag() {
        trainer.getListe().addWort(new WortEintrag("Haus", "https://www.haus.at"));
        WortEintrag eintrag = trainer.getEintrag("Haus;https://www.haus.at");
        assertEquals(eintrag.getWort(), "Haus");
    }

    private TXTSpeichern TXTspeichern;
    private JSONSpeichern JSONspeichern;

    /**
     * Testet die "speichern" & "laden"-Methode in TXTSpeichern.java
     */
    @org.junit.jupiter.api.Test
    public void testSpeichernTXT() {
        trainer.getListe().addWort(new WortEintrag("Haus", "https://www.haus.at"));

    }
}