import npfeiler.model.WortListe;
import npfeiler.model.WortTrainer;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class Test {
    private WortTrainer trainer;

    @BeforeEach
    public void clear() {
        this.trainer = new WortTrainer();
        this.trainer.setRichtig(0);
        this.trainer.setFalsch(0);
    }

    @org.junit.jupiter.api.Test
    public void testWordCountZero() {
        trainer.setListe(new WortListe());
        assertEquals(0, this.trainer.getListe().laenge());
    }

}