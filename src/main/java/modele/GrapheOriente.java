package modele;

import java.util.*;


public class GrapheOriente {
    private Map<Integer,Sommet> sommets;
    private List<Arc> arcs;
    private int ordre;
    private int taille;
    private  int degréMax;
    private int degréMin;
    private  Map<Integer,ArrayList<Sommet>> adjacence;


    public GrapheOriente(Scenario scenario,Carte carte,Membres membre){
        sommets = new HashMap<>();
        arcs = new ArrayList<>();
        adjacence = new HashMap<>();


        List<String> departs =scenario.getVendeurs();
        List<String> arrivés = scenario.getAcheteurs();
        Map<String, String> membres = membre.getListe();
        Map<String,Integer> IndiceVille = carte.getVillesIndicés();



        for (int i = 0; i<departs.size();i++){
            String vendeur = departs.get(i);
            String acheteur = arrivés.get(i);
            Integer indiceV = IndiceVille.get(membre.getListe().get(vendeur));
            Integer indiceA = IndiceVille.get(membre.getListe().get(acheteur));
            if (i == 0){
                sommets.put(indiceV, new Sommet(membre.getListe().get(vendeur)));
                adjacence.put(indiceV,new ArrayList<>());
                sommets.put(indiceA, new Sommet(membre.getListe().get(acheteur)));
                adjacence.put(indiceA, new ArrayList<>());
            }
            else {
                if (!sommets.keySet().contains(indiceV)) {
                    sommets.put(indiceV, new Sommet(membre.getListe().get(vendeur)));
                    adjacence.put(indiceV,new ArrayList<>());
                }
                if (!sommets.keySet().contains(indiceA)) {
                    sommets.put(indiceA, new Sommet(membre.getListe().get(acheteur)));
                    adjacence.put(indiceA, new ArrayList<>());
                }
            }

            arcs.add(new Arc(sommets.get(indiceV),sommets.get(indiceA)));
            adjacence.get(indiceV).add(sommets.get(indiceA));

        }

        ordre = sommets.size();
        taille = arcs.size();


    }


    @Override
    public String toString() {
        return "GrapheOriente{" +
                "sommets=" + sommets + "\n" +
                ", arcs=" + arcs + "\n" +
                ", adjacence=" + adjacence +  "\n" +
                ", ordre=" + ordre + "\n" +
                ", taille=" + taille +
                '}';
    }
}
