package modele;

import javafx.scene.control.Tab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Carte {
    private Map<String,Integer> villesIndicé;
    private Integer[][] distances;

    public Carte() throws IOException {
        villesIndicé = new HashMap<>();
        distances = new Integer[29][29];

        BufferedReader bufferEntree = new BufferedReader(new FileReader("src/files/distances.txt"));
        String ligne;
        StringTokenizer tokenizer;
        Integer indVille = 0;

        do{
            ligne  = bufferEntree.readLine();
            if(ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                villesIndicé.put(tokenizer.nextToken(), indVille);
                for (Integer i = 0; i < 29; i++) {
                    distances[indVille][i] = Integer.valueOf(tokenizer.nextToken());
                }
                indVille++;
            }
        }
        while(ligne!=null);
        bufferEntree.close();
    }

    public String toString(){
        String chaine= "";
        for (Map.Entry mapentry : villesIndicé.entrySet()) {
            chaine = chaine + "ville : " + mapentry.getKey() + " | indice : " + mapentry.getValue() +" "+ Arrays.toString(distances[(int) mapentry.getValue()]) + "\n";
        }
        return chaine;
    }

    public Map<String,Integer> getVillesIndicés() {
        return villesIndicé;
    }

    public Integer[][] getDistance() {
        return distances;
    }
}
