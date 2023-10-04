package npfeiler.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JSONSpeichern implements SpeichernStradegy {

    /**
     * Speichert den gesamten WortTrainer in einem JSON-File (Default-Pfad)
     * @param trainer
     * @throws IOException
     */
    @Override
    public void speichern(WortTrainer trainer) {
        try {
            speichern(trainer, "WortTrainer.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Speichert den gesamten WortTrainer in einem JSON-File
     * @param trainer
     * @param pfad
     * @throws IOException
     */
    @Override
    public void speichern(WortTrainer trainer, String pfad) throws IOException {

        if(!new File(pfad).exists()) new File(pfad).createNewFile();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("liste", trainer.getListe());
        jsonObject.put("aktuell", trainer.currentEintrag());
        jsonObject.put("richtig", trainer.getRichtig());
        jsonObject.put("falsch", trainer.getFalsch());

        FileWriter fileWriter = new FileWriter(pfad);
        fileWriter.write(jsonObject.toString());
        fileWriter.close();
    }

    /**
     * Lädt den gesamten Worttrainer aus einem gewählten JSON-File (Default-Pfad)
     * @return
     */
    public WortTrainer laden() {
        return laden("Worttrainer.json");
    }

    /**
     * Lädt den gesamten Worttrainer aus einem gewählten JSON-File
     * @param pfad
     * @return
     */
    @Override
    public WortTrainer laden(String pfad) {
        if(!new File(pfad).exists()) return null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pfad));
            String json = bufferedReader.readLine();
            bufferedReader.close();

            JSONObject jsonObject = new JSONObject(json);
            WortTrainer trainer = new WortTrainer();
            trainer.setListe(convertJSONArrayToWortListe(jsonObject.getJSONArray("liste")));
            trainer.setAktuell(jsonObject.getInt("aktuell"));
            trainer.setRichtig(jsonObject.getInt("richtig"));
            trainer.setFalsch(jsonObject.getInt("falsch"));

            return trainer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Konvertiert ein JSONArray in eine WortListe (ArrayList)
     * @param jsonArray
     * @return
     */
    private static WortListe convertJSONArrayToWortListe(JSONArray jsonArray) {
        WortListe liste = new WortListe();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            liste.addWort(new WortEintrag(jsonObject.getString("word"), jsonObject.getString("translation")));
        }
        return liste;
    }
}
