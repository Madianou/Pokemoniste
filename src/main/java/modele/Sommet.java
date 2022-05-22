package modele;

public class Sommet {
    private String ville;
    private int degré;


    public Sommet(String parVille){
        ville = parVille;

    }

    @Override
    public String toString() {
        return ville;
    }
}


