package npfeiler.model;

import java.io.IOException;

public interface SpeichernStradegy {

        void speichern(WortTrainer trainer);
        void speichern(WortTrainer trainer, String pfad) throws IOException;

        WortTrainer laden(String pfad);
        WortTrainer laden();
}
