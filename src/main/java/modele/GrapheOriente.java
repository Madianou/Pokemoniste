package modele;

import java.util.*;


public class GrapheOriente {
    private Map<Integer,Sommet> sommets;
    private List<Arc> arcs;
    private int ordre;
    private int taille;
    private  int degréMaxS;
    private int degréMinS;
    private  int degréMaxE;
    private int degréMinE;
    private  Map<Integer,ArrayList<Integer>> adjacence;
    private Map<Integer,Integer> degréSortant;
    private Map<Integer,Integer> degréEntrant;


    public GrapheOriente(Scenario scenario,Carte carte,Membres membre){
        sommets = new HashMap<>();
        arcs = new ArrayList<>();
        adjacence = new HashMap<>();
        degréEntrant = new HashMap<>();
        degréSortant = new HashMap<>();


        List<String> departs = scenario.getVendeurs();
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
                degréSortant.put(indiceV,0);
                degréEntrant.put(indiceV,0);

                sommets.put(indiceA, new Sommet(membre.getListe().get(acheteur)));
                adjacence.put(indiceA, new ArrayList<>());
                degréEntrant.put(indiceA,0);
                degréSortant.put(indiceA,0);
            }
            else {
                if (!sommets.keySet().contains(indiceV)) {
                    sommets.put(indiceV, new Sommet(membre.getListe().get(vendeur)));
                    adjacence.put(indiceV,new ArrayList<>());
                    degréSortant.put(indiceV,0);
                    degréEntrant.put(indiceV,0);
                }
                if (!sommets.keySet().contains(indiceA)) {
                    sommets.put(indiceA, new Sommet(membre.getListe().get(acheteur)));
                    adjacence.put(indiceA, new ArrayList<>());
                    degréEntrant.put(indiceA,0);
                    degréSortant.put(indiceA,0);
                }
            }

            arcs.add(new Arc(sommets.get(indiceV),sommets.get(indiceA)));
            adjacence.get(indiceV).add(indiceA);




            int val = degréSortant.get(indiceV);
            val+=1;
            degréSortant.put(indiceV,val);

            int val2 = degréEntrant.get(indiceA);
            val2+=1;
            degréEntrant.put(indiceA,val2);
        }

        ordre = sommets.size();
        taille = arcs.size();

        degréMaxS =  Collections.max(degréSortant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMinS =  Collections.min(degréSortant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMaxE =  Collections.max(degréEntrant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMinE =  Collections.min(degréEntrant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
    }


    @Override
    public String toString() {
        return "GrapheOriente{" +
                "sommets=" + sommets + "\n" +
                ", arcs=" + arcs + "\n" +
                ", adjacence=" + adjacence +  "\n" +
                ", ordre=" + ordre + "\n" +
                ", taille=" + taille + "\n" +
                "degré Entrant=" +degréEntrant +"\n" +
                "degré sortant=" + degréSortant + "\n" +
                ", degré entrant max="+ degréMaxE +"\n" +
                ",degré sortant max=" + degréMaxS +"\n" +
                ", degré entrant min="+ degréMinE +"\n" +
                ",degré sortant min=" + degréMinS +
                '}';
    }
}
