package modele;

public class Arc {
    private Sommet départ;
    private Sommet arrivé;


    public Arc(Sommet d, Sommet a){
        départ = d;
        arrivé = a;
    }

    public String toString(){
        return départ.toString() + " -> " + arrivé.toString();
    }
}
