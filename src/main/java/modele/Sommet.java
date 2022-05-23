package modele;

public class Sommet {
    private String ville;
    private int degréEntrant;
    private int degréSortant;


    public Sommet(String parVille){
        ville = parVille;

    }

    //public setDegrés(){

    //}

    @Override
    public String toString() {
        return ville;
    }
}


