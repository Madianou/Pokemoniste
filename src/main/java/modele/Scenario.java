package modele;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Scenario {
    List<String> allVendeurs;
    List <String> allAcheteurs;
    List <String> vendeurs;
    List <String> acheteurs;
    public Scenario () {
        allVendeurs = new ArrayList<>();
        allAcheteurs = new ArrayList<>();
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
        for (String vendeur : scenario.getAllVendeurs()) {
            sortie.println(vendeur+ " -> " + scenario.getAllAcheteurs().get(i));
        }
        sortie.close();
    }



    public void ajoutVendeurAcheteur(String vendeur, String acheteur) {
        allVendeurs.add(vendeur);
        allAcheteurs.add(acheteur);
    }



    public List<String> getAllVendeurs() {
        return allVendeurs;
    }

    public List<String> getVendeursListe(){
        for (String vendeur : allVendeurs){
            if (vendeurs.contains(vendeur)){
            }
            else {
                vendeurs.add(vendeur);
            }
        }
        return vendeurs;
    }
    public String getVendeursString(){
        for (String vendeur : allVendeurs){
            if (vendeurs.contains(vendeur)){
            }
            else {
                vendeurs.add(vendeur);
            }
        }
        String chaine = "";
        for (String gens : vendeurs) {
            chaine = chaine + gens + "\n";
        }
        return chaine;
    }
    public List<String> getAllAcheteurs() {
        return allAcheteurs;
    }

    public List<String> getAcheteursListe(){
        for (String acheteur : allAcheteurs){
            if (acheteurs.contains(acheteur)){
            }
            else {
                acheteurs.add(acheteur);
            }
        }
        return acheteurs;
    }
    public String getAcheteursString(){
        for (String acheteur : allAcheteurs){
            if (acheteurs.contains(acheteur)){
            }
            else {
                acheteurs.add(acheteur);
            }
        }
        String chaine = "";
        for (String gens : acheteurs) {
            chaine = chaine + gens + "\n";
        }
        return chaine;
    }
    public String getMembresString(){
        ArrayList <String> membres = new ArrayList<>();

        for (String membre : allAcheteurs){
            if (membres.contains(membre)){
            }
            else {
                membres.add(membre);
            }
        }
        for (String personne : allVendeurs){
            if (membres.contains(personne)){
            }
            else {
                membres.add(personne);
            }
        }
        String chaine = "";
        for (String gens : membres) {
            chaine = chaine + gens + "\n";
        }
        return chaine;
    }
    public List<String> getMembresListe(){
        ArrayList <String> membres = new ArrayList<>();

        for (String membre : allAcheteurs){
            if (membres.contains(membre)){
            }
            else {
                membres.add(membre);
            }
        }
        for (String personne : allVendeurs){
            if (membres.contains(personne)){
            }
            else {
                membres.add(personne);
            }
        }
        return membres;
    }

    public String toString() {
        return allVendeurs + "\n" + allAcheteurs;
    }

    public String toStringCommeFich(){
        String chaine = "";
        for(int i = 0; i< allVendeurs.size(); i++){
            chaine = chaine + allVendeurs.get(i) + " -> " + allAcheteurs.get(i) + "\n";
        }
        return chaine;
    }
}