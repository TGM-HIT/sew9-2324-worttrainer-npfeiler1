package npfeiler.model;

public interface SpeichernStradegy {

        void speichern(WortTrainer trainer, String pfad);

        WortTrainer laden(String pfad);
}
