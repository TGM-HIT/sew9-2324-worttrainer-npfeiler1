package npfeiler.model;

public interface SpeichernStradegy {
        void speichern(String pfad, WortTrainer trainer);
        WortTrainer laden(String pfad);
}
