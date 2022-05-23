package Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Membres {

    private Map<String ,String> liste;

    public Membres() throws IOException {
        liste = new HashMap<>();

        BufferedReader bufferEntree = new BufferedReader(new FileReader("src/files/membres_APLI.txt"));
        String ligne;
        StringTokenizer tokenizer;

        do{
            ligne  = bufferEntree.readLine();
            if(ligne != null) {
                tokenizer = new StringTokenizer(ligne, " ");
                liste.put(tokenizer.nextToken(), tokenizer.nextToken());

            }
        }
        while(ligne!=null);
        bufferEntree.close();

    }

    public String toString(){
        String chaine= "";
        for (Map.Entry mapentry : liste.entrySet()) {
            chaine = chaine + mapentry.getKey() + " vit à " + mapentry.getValue() + "\n";
        }
        return chaine;
    }

    public  Map<String, String> getListe() {
        return liste;
    }
}
