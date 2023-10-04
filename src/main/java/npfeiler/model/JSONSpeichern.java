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

        JSONObject object1 = new JSONObject();
        JSONObject object2 = new JSONObject();
        JSONObject object3 = new JSONObject();

        object1.put("wort", "Hund");
        object1.put("url", "https://www.mera-petfood.com/files/_processed_/b/b/csm_iStock-521697453_bb8fbb7807.jpg");

        object2.put("wort", "Katze");
        object2.put("url", "https://einfachtierisch.de/media/cache/article_main_image/cms/2015/09/Katze-lacht-in-die-Kamera-shutterstock-Foonia-76562038.jpg?266705");

        object3.put("wort", "Schwein");
        object3.put("url", "https://assets.puzzlefactory.pl/puzzle/192/732/original.jpg");

        JSONArray liste = new JSONArray();
        liste.put(object1);
        liste.put(object2);
        liste.put(object3);

        jsonObject.put("liste", liste);
        jsonObject.put("aktuell", trainer.getAktuell());
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
        return laden("WortTrainer.json");
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
            JSONArray liste = jsonObject.getJSONArray("liste");
            trainer.setListe(convertJSONArrayToWortListe(liste));
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
        JSONObject object1 = jsonArray.getJSONObject(0);
        JSONObject object2 = jsonArray.getJSONObject(1);
        JSONObject object3 = jsonArray.getJSONObject(2);

        liste.addWort(new WortEintrag(object1.getString("wort"), object1.getString("url")));
        liste.addWort(new WortEintrag(object2.getString("wort"), object1.getString("url")));
        liste.addWort(new WortEintrag(object3.getString("wort"), object1.getString("url")));

        return liste;
    }
}
