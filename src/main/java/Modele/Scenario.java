package Modele;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scenario {
    List<String> vendeurs ;
    List <String> acheteurs ;
    public Scenario () {
        vendeurs = new ArrayList<>();
        acheteurs = new ArrayList<>();
    }


    public static Scenario lectureScenario (File fichier) throws IOException {
        Scenario scenario = new Scenario();
        BufferedReader bufferEntree = new BufferedReader(new FileReader(fichier));
        String ligne ;
        StringTokenizer tokenizer ;
        do {
            ligne = bufferEntree.readLine ();
            if (ligne != null) {
                tokenizer = new StringTokenizer(ligne ," ->");
                scenario.ajoutVendeurAcheteur(tokenizer.nextToken(),tokenizer.nextToken());
            }
        }
        while (ligne != null );
        bufferEntree.close();
        return scenario;
    }

    public static void ecritureScenario (String nomFichier, Scenario scenario) throws IOException {
        PrintWriter sortie = new PrintWriter(new BufferedWriter(new FileWriter(nomFichier)));
        int i = 0;
        for (String vendeur : scenario.getVendeurs()) {
            sortie.println(vendeur+ " -> " + scenario.getAcheteurs().get(i));
        }
        sortie.close();
    }



    public void ajoutVendeurAcheteur(String vendeur, String acheteur) {
        vendeurs.add(vendeur);
        acheteurs.add(acheteur);
    }



    public List<String> getVendeurs() {
        return vendeurs ;
    }

    public List<String> getAcheteurs() {
        return acheteurs ;
    }

    public String toString() {
        return vendeurs + "\n" + acheteurs ;
    }

    public String toStringCommeFich(){
        String chaine = "";
        for( int i = 0; i<vendeurs.size();i++){
            chaine = chaine + vendeurs.get(i) + " -> " + acheteurs.get(i) + "\n";
        }
        return chaine;
    }
}