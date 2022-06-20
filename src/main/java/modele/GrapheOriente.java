package modele;

import java.util.*;


public class GrapheOriente {

    /**
      Graphe orientée décrit par ses sommets, ses arcs et une liste d'adjacence. Les informations sur ses degrés sont également disponibles.
     **/

    private List<Integer> sommets;
    private List<List<Integer>> arcs;
    private List<Integer> source;
    private int ordre;
    private int taille;
    private  int degréMaxS;
    private int degréMinS;
    private  int degréMaxE;
    private int degréMinE;
    private Map<Integer,ArrayList<Integer>> adjacence;
    private Map<Integer,Integer> degréSortant;
    private Map<Integer,Integer> degréEntrant;


    /**
     * @param scenario liste d'arc de type : vendeurs -> acheteurs
     * @param carte attribue à chaque villes un indice
     * @param membre liste de membres avec leurs villes respectives
     *             Prends en entrée un scénario , une carte et une liste de membres.
     *             Construit un graphe orienté décrit par ses sommets, ses arcs et une liste d'adjacence. Les informations sur ses degrés sont également disponibles.
     */
    public GrapheOriente(Scenario scenario,Carte carte,Membres membre){


        sommets = new ArrayList<>();
        arcs = new ArrayList<List <Integer>>();
        source = new ArrayList<>();
        adjacence = new HashMap<>();
        degréEntrant = new HashMap<>();
        degréSortant = new HashMap<>();


        List<String> departs = scenario.getAllVendeurs();
        List<String> arrivés = scenario.getAllAcheteurs();
        Map<String, String> membres = membre.getListe();
        Map<String,Integer> IndiceVille = carte.getVillesIndicés();



        for (int i = 0; i<departs.size();i++){   /** Construction de la liste des sommets, arcs et liste d'adjacence **/

            String vendeur = departs.get(i);
            String acheteur = arrivés.get(i);
            Integer indiceV = IndiceVille.get(membre.getListe().get(vendeur));
            Integer indiceA = IndiceVille.get(membre.getListe().get(acheteur));


            if (i == 0){ /** Au début on doit ajouter forcément le premier couple de vendeurs acheteurs au graphe **/
                sommets.add(indiceV);
                adjacence.put(indiceV,new ArrayList<>());
                degréSortant.put(indiceV,0);
                degréEntrant.put(indiceV,0);

                sommets.add(indiceA);
                adjacence.put(indiceA, new ArrayList<>());
                degréEntrant.put(indiceA,0);
                degréSortant.put(indiceA,0);
            }
            else {   /** On regarde si le vendeur ou l'acheteurs est nouveaux dans le graphe et si oui on l'ajoute **/
                if (!sommets.contains(indiceV)) {
                    sommets.add(indiceV);
                    adjacence.put(indiceV,new ArrayList<>());
                    degréSortant.put(indiceV,0);
                    degréEntrant.put(indiceV,0);
                }
                if (!sommets.contains(indiceA)) {
                    sommets.add(indiceA);
                    adjacence.put(indiceA, new ArrayList<>());
                    degréEntrant.put(indiceA,0);
                    degréSortant.put(indiceA,0);
                }
            }
            List <Integer> tab =new ArrayList<>();
            tab.add(indiceV);
            tab.add(indiceA);
            arcs.add(tab);
            adjacence.get(indiceV).add(indiceA);


            int val = degréSortant.get(indiceV);
            val+=1;
            degréSortant.put(indiceV,val);

            int val2 = degréEntrant.get(indiceA);
            val2+=1;
            degréEntrant.put(indiceA,val2);
        }

        for (Map.Entry<Integer, Integer> mapentry : degréEntrant.entrySet()) {   /** Préparation d'une liste des sources(sommet avec degré entrant = 0) du graphe **/
            if (mapentry.getValue() == 0){
                source.add(mapentry.getKey());
            }
        }
        ordre = sommets.size();
        taille = arcs.size();

        degréMaxS =  Collections.max(degréSortant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMinS =  Collections.min(degréSortant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMaxE =  Collections.max(degréEntrant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
        degréMinE =  Collections.min(degréEntrant.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
    }


    public Map parcoursLargeur(Integer indiceDepart, Carte carte){

        /**
            Parcours en largeur avec une file FIFO qui permet d'obtenir le plus court chemin jusqu'aux sommets accesibles depuis le sommet indiceDepart avec la distance réelle pour chaque sommet.
            Prend en entrée un indiceDepart et une carte qui donne les distances entre chaques villes.
            Retourne une map avec pour clé chaque sommet du graphe et comme valeur un tableau avec son prédécesseur et sa distance au sommet de départ.
         **/

        Map listePredDist = new HashMap<Integer,int[]>();
        Deque file = new ArrayDeque();


        // liste avec pred et dist

        for (Map.Entry<Integer,ArrayList<Integer>> mapentry : adjacence.entrySet()) {
            if (mapentry.getKey() == indiceDepart){
                int [] tab = {-2,0};
                listePredDist.put(mapentry.getKey(),tab);
            }
            int [] tab = {-1,-1};
            listePredDist.put(mapentry.getKey(),tab);
        }

        file.addLast(indiceDepart);

        while (!file.isEmpty()){
            Integer courant = (Integer) file.removeFirst();
            Iterator iterator = adjacence.get(courant).iterator();
            while (iterator.hasNext()){
                int indice = (int) iterator.next();
                int[] tab = (int[]) listePredDist.get(indice);
                int[] tabCourant = (int[]) listePredDist.get(courant);
                if(-1 ==tab[1] ){
                    tab[0] = courant;
                    tab[1] =  tabCourant[1]+carte.getDistance()[courant][indice];
                    file.addLast(indice);
                }
            }
        }
        return  listePredDist;
    }

    public static String parcoursLargeurToString (HashMap<Integer, int[]> map){

        /**
            Met en forme le retour de la fonction parcoursLargeur avec la forme suivante : indice du sommet : pred=indice dist=val
         **/
        String chaine = "";
        if (map==null){
            return chaine;
        }
        for (Map.Entry<Integer, int[]> mapentry : map.entrySet()) {
            chaine = chaine + mapentry.getKey() + " : pred=" + mapentry.getValue()[0] + " dist=" + mapentry.getValue()[1] + "\n";
        }
        return chaine;

    }


    public List<Integer> sourceEnSource(List<Integer> source,Map<Integer,Integer> degréEntrant,Carte carte){
        /**
            Fait un parcours de source en source et retourne qu'une seule solution ( pas forcément la meilleure ).
            L'algorithme avance de source en source en "éliminant" les sources utilisés au fur à mesure en dimininuant les degrés entrant des sommets pour créer de nouvelles sources
         **/
        Integer dist = 0;
        List<Integer> sourceTemp = new ArrayList<>(source);
        Map<Integer,Integer> degréE = new HashMap<>( degréEntrant);
        Deque file = new ArrayDeque(sourceTemp);
        List<Integer> chemin = new ArrayList<>();
        Integer origine = sourceTemp.remove(0);
        dist+= carte.getDistance()[28][origine];
        for (Integer x : sourceTemp){
            dist += carte.getDistance()[origine][x];
            origine = x;
        }


        while(!file.isEmpty()){
            Integer courant = (Integer) file.removeFirst();
            chemin.add(courant);
            Iterator iterator = adjacence.get(courant).iterator();
            while(iterator.hasNext()){  /** On diminue les degrés entrants de tous les voisins de courant car on "l'élimine" du graphe **/
                Integer v = (Integer) iterator.next();
                degréE.put(v,degréE.get(v)-1);
                if (degréE.get(v) == 0){  /** On trouve une nouvelle source et on l'ajoute dans la file **/
                    dist+= carte.getDistance()[courant][v];
                    file.addLast(v);
                }
            }
            if (file.isEmpty()){
                dist +=  carte.getDistance()[28][courant];
            }
        }
        chemin.add(dist);
        return chemin;
    }



    public List<List<Integer>> sourceComplet(Integer origine,List<List<Integer>> chemins,List<Integer> cheminPred,List<Integer> source,Map<Integer,Integer> degréEntrant){

        if (!source.isEmpty()) {
            if (source.size()>1){
                for (Integer s : source){

                    List <Integer> cheminPredTemp = new ArrayList<>(cheminPred);
                    List <Integer> sourceTemp = new ArrayList<>(source);
                    HashMap<Integer, Integer> degreE = new HashMap<Integer,Integer>(degréEntrant);
                    sourceTemp.remove(s);
                    Iterator iterator = adjacence.get(s).iterator();
                    while(iterator.hasNext()){
                        Integer v = (Integer) iterator.next();
                        degreE.put(v,degreE.get(v)-1);
                        if (degreE.get(v) == 0){
                            sourceTemp.add(v);
                        }
                    }
                    if (origine == 28) {
                        cheminPredTemp.add(origine);
                    }
                    cheminPredTemp.add(s);
                    sourceComplet(s,chemins,cheminPredTemp,sourceTemp,degreE);
                }
            }
            else{
                List <Integer> cheminPredTemp = new ArrayList<>(cheminPred);
                if (origine == 28) {
                    cheminPredTemp.add(origine);
                }
                Deque <Integer> sourceTemp = new ArrayDeque<>(source);
                HashMap<Integer, Integer> degréE = new HashMap<Integer,Integer>(degréEntrant);

                while(sourceTemp.size()==1) {
                    Integer courant = sourceTemp.removeFirst();
                    cheminPredTemp.add(courant);
                    Iterator iterator = adjacence.get(courant).iterator();
                    while (iterator.hasNext()) {  /** On diminue les degrés entrants de tous les voisins de courant car on "l'élimine" du graphe **/
                        Integer v = (Integer) iterator.next();
                        degréE.put(v, degréE.get(v) - 1);
                        if (degréE.get(v) == 0) {  /** On trouve une nouvelle source et on l'ajoute dans la file **/

                            sourceTemp.addLast(v);
                        }
                    }
                }
                if (sourceTemp.isEmpty()){
                    cheminPredTemp.add(28);
                    chemins.add(cheminPredTemp);
                }
                else{
                    sourceComplet(cheminPredTemp.get(cheminPredTemp.size()-1),chemins,cheminPredTemp,new ArrayList<>(sourceTemp),degréE);
                }
            }
        }
        return chemins;
    }


    public static Integer CalculDistanceChemin (List <Integer> chemin,Carte carte){
        Integer dist = 0;
        for (int i = 0 ; i < chemin.size() - 1; i++){
            dist += carte.getDistance()[chemin.get(i)][chemin.get(i+1)];
        }
        return  dist;
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

    public List<Integer> getSource() {
        return source;
    }

    public Map<Integer, Integer> getDegreEntrant() {
        return degréEntrant;
    }

    public List<Integer> getSommets() {
        return sommets;
    }
}
