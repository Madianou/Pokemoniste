package modele;

import java.util.*;


/**
 Graphe orientée décrit par ses sommets, ses arcs et une liste d'adjacence. Les informations sur ses degrés sont également disponibles.
 **/
public class GrapheOriente {


    /**
     * Liste des sommets numérotés de 0 à 28
     */
    private List<Integer> sommets;
    /**
     * Liste des arcs
     */
    private List<List<Integer>> arcs;
    /**
     * Liste des sources du graphe (source = sommet avec degré entrant = 0)
     */
    private List<Integer> source;
    /**
     * Nombre de sommets du graphe
     */
    private int ordre;
    /**
     * Nombre d'arcs du graphe
     */
    private int taille;
    /**
     * degré maximum sortant du graphe
     */
    private  int degréMaxS;
    /**
     * degré min sortant du graphe
     */
    private int degréMinS;
    /**
     * degré max entrant du graphe
     */
    private  int degréMaxE;
    /**
     * degré min entrant du graphe
     */
    private int degréMinE;
    /**
     * HashMap d'adjacence du graphe, pour chaque sommet (clé) on a la liste de ses voisins(valeurs)
     */
    private Map<Integer,ArrayList<Integer>> adjacence;
    /**
     * HashMap des degrés (valeur) sortant de chaque sommet (clé) du graphe
     */
    private Map<Integer,Integer> degréSortant;
    /**
     * HashMap des degrés (valeur) entrant de chaque sommet (clé) du graphe
     */
    private Map<Integer,Integer> degréEntrant;


    /**
     * Prends en entrée un scénario , une carte et une liste de membres.
     *      *             Construit un graphe orienté décrit par ses sommets, ses arcs et une liste d'adjacence. Les informations sur ses degrés sont également disponibles.
     * @param scenario liste d'arc de type : vendeurs -> acheteurs
     * @param carte attribue à chaque villes un indice
     * @param membre liste de membres avec leurs villes respectives
     *
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


    /**
     * Parcours en largeur avec une file FIFO qui permet d'obtenir le plus court chemin jusqu'aux sommets accessibles depuis le sommet indiceDepart avec la distance réelle pour chaque sommet.
     *      *              On choisit la valeur -1 pour montrer qu'un sommet est impossible à atteindre.
     * @param indiceDepart indice du sommet avec lequel on débute le parcours en largeur
     * @param carte carte qui donne les distances entre chaques villes
     *
     *
     * @return Retourne une map avec pour clé chaque sommet du graphe et comme valeur un tableau avec son prédécesseur et sa distance au sommet de départ.
     */
    public Map parcoursLargeur(Integer indiceDepart, Carte carte){
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

    /**
     * Met en forme le retour de la fonction parcoursLargeur avec la forme suivante : indice du sommet : pred=indice dist=val
     * @param map le résultat d'un parcours en largeur
     * @return String
     */
    public static String parcoursLargeurToString (HashMap<Integer, int[]> map){


        String chaine = "";
        if (map==null){
            return chaine;
        }
        for (Map.Entry<Integer, int[]> mapentry : map.entrySet()) {
            chaine = chaine + mapentry.getKey() + " : pred=" + mapentry.getValue()[0] + " dist=" + mapentry.getValue()[1] + "\n";
        }
        return chaine;

    }


    /**
     *
     *Fait un parcours de source en source et retourne qu'une seule solution ( pas forcément la meilleure ).
     *L'algorithme avance de source en source en "éliminant" les sources utilisés au fur à mesure en dimininuant les degrés entrant des sommets pour créer de nouvelles sources
     *
     *
     * @param carte carte qui donne les distances entre chaques villes
     * @return une liste contenant une solution
     */
    public List<Integer> sourceEnSource(Carte carte){

        Integer dist = 0;
        List<Integer> sourceTemp = new ArrayList<>(this.source);
        Map<Integer,Integer> degréE = new HashMap<>( this.degréEntrant);
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


    /** Fait un parcours de source en source de manière récursive pour trouvezr toutes les solutions, a chaque fois qu'on a le choix entre plusieurs sources
     * on effectue un parcours sur chacune de ces possibilités.
     * @param origine sommet initial du parcours
     * @param chemins Liste des chemins qui se remplit au fur à mesure de l'algorithme
     * @param cheminPred chemin deja construit au moment du lancement du parcours
     * @param source source du graphe (en paramètres car elles évoluent en temps réel)
     * @param degréEntrant détail des degrés entrants des sommets du graphe (en paramètres car cela évolue en temps réel)
     * @return Une liste de tous les chemins possibles
     */
    public List<List<Integer>> sourceComplet(Integer origine,List<List<Integer>> chemins,List<Integer> cheminPred,List<Integer> source,Map<Integer,Integer> degréEntrant){

        if (!source.isEmpty()) {
            if (source.size()>1){ /** On regarde si on a le choix entre plusieurs sources, si oui il faut donc faire le parcours sur toutes les possibilités**/
                for (Integer s : source){

                    List <Integer> cheminPredTemp = new ArrayList<>(cheminPred); /** On crée des nouveaux objets pour ne pas avoir des problèmes de pointeurs **/
                    List <Integer> sourceTemp = new ArrayList<>(source);
                    HashMap<Integer, Integer> degreE = new HashMap<Integer,Integer>(degréEntrant);
                    sourceTemp.remove(s); /**  On enlève la source qu'on a choisi**/
                    Iterator iterator = adjacence.get(s).iterator(); /** On diminue les degrés entrants de tous les voisins de s car on "l'élimine" du graphe **/
                    while(iterator.hasNext()){
                        Integer v = (Integer) iterator.next();
                        degreE.put(v,degreE.get(v)-1);
                        if (degreE.get(v) == 0){
                            sourceTemp.add(v);
                        }
                    }
                    if (origine == 28) { /**  Ici j'ajoute seulement vélizy si on est au tout début du parcours**/
                        cheminPredTemp.add(origine);
                    }
                    cheminPredTemp.add(s);
                    sourceComplet(s,chemins,cheminPredTemp,sourceTemp,degreE); /** On recommence l'algorithme en ayant choisi s comme origine et lis à jour les sources et les degrés entrants**/
                }
            }
            else{ /**  On arrive ici quand il n'y a plus qu'une seule source dans la liste des sources**/
                List <Integer> cheminPredTemp = new ArrayList<>(cheminPred);
                if (origine == 28) { /**  Ici j'ajoute seulement vélizy si on est au tout début du parcours**/
                    cheminPredTemp.add(origine);
                }
                Deque <Integer> sourceTemp = new ArrayDeque<>(source);
                HashMap<Integer, Integer> degréE = new HashMap<Integer,Integer>(degréEntrant);

                while(sourceTemp.size()==1) { /** on avance dans le parcours tant que l'on a qu'un seul choix de source**/
                    Integer courant = sourceTemp.removeFirst();
                    cheminPredTemp.add(courant);
                    Iterator iterator = adjacence.get(courant).iterator();
                    while (iterator.hasNext()) {
                        Integer v = (Integer) iterator.next();
                        degréE.put(v, degréE.get(v) - 1);
                        if (degréE.get(v) == 0) {
                            sourceTemp.addLast(v); /** On trouve une nouvelle source et on l'ajoute dans la file **/
                        }
                    }
                }
                if (sourceTemp.isEmpty()){ /**  La file est vide la parcours de ce chemin est terminé on peut ajouter vélizy et le chemin dans la liste des chemins**/
                    cheminPredTemp.add(28);
                    chemins.add(cheminPredTemp);
                }
                else{ /**  Sinon cela veut dire que la parcours n'est pas terminé et que l'on a à nouveaux le choix entre plusieurs sources
                        On recommence donc le parcours avec les sources actuelles, le chemin actuelle et avec comme origine le dernier élément de ce chemin.**/
                    sourceComplet(cheminPredTemp.get(cheminPredTemp.size()-1),chemins,cheminPredTemp,new ArrayList<>(sourceTemp),degréE);
                }
            }
        }
        return chemins;
    }


    /** Calcul la distance total parcourut par une personne suivant le chemin en paramètre
     * @param chemin une liste de sommet formant un chemin
     * @param carte carte qui donne les distances entre chaques villes
     * @return Integer Distance totale
     */
    public static Integer CalculDistanceChemin (List <Integer> chemin,Carte carte){
        Integer dist = 0;
        for (int i = 0 ; i < chemin.size() - 1; i++){
            dist += carte.getDistance()[chemin.get(i)][chemin.get(i+1)];
        }
        return  dist;
    }

    /** Retourne le meilleur chemin en terme de distance parcouru (le moins).
     * @param chemins  Liste de chemins
     * @param carte carte qui donne les distances entre chaques villes
     * @return String un affichage qui donne le meilleur chemin, son numéro et la distance que couvre ce chemin.
     */
    public static String BestChemin (List<List<Integer>> chemins,Carte carte){
        String chaine = "";
        Integer total = 1;
        Integer minDist = GrapheOriente.CalculDistanceChemin(chemins.get(0),carte);
        List<Integer> chemin = chemins.get(0);
        for (List<Integer> elem : chemins){
            Integer dist = GrapheOriente.CalculDistanceChemin(elem,carte);
            if (dist< minDist){
                minDist = dist;
                chemin = elem;
            }
            total +=1;
        }
        chaine = "Meilleur Chemin : " + total + "\n"
                + chemin + "\n"
                + "Distance Totale : "+ minDist + " km" + "\n";
        return chaine;
    }


    /**
     * @return Un affichage avec tous les champs de grapheOrienté
     */
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

    /**
     * @return Un affichage simplifié pour grapheOrienté (seulement la liste des sommets, des arcs ainsi que l'ordre et la taiile)
     */
    public String toStringSimplifié(){
        return "sommets=" + sommets + "\n" +
                "arcs=" + arcs + "\n" +
                "ordre=" + ordre + "\n" +
                "taille=" + taille + "\n" ;
    }

    /** Affiche tous les chemins d'une liste de chemins d'une manière plus lisible
     * @param chemins lliste de chemins
     * @param carte carte qui donne les distances entre chaques villes
     * @return
     */
    public static String toStringSourceComplet(List <List<Integer>> chemins,Carte carte){
        String chaine = "";
        Integer total = 1;
        for (List<Integer> elem : chemins){
            chaine += "Chemin :" + total + "\n"
                    + elem + "\n"
                    + "Distance Totale : "+GrapheOriente.CalculDistanceChemin(elem,carte) +"\n";
            total +=1;
        }
        return chaine;
    }

    /** Accesseur pour champ source
     * @return la liste des sources du graphe
     */
    public List<Integer> getSource() {
        return source;
    }

    /** Accesseur pour le champ degréEntrant
     * @return la HashMap des degré entrant du graphe
     */
    public Map<Integer, Integer> getDegreEntrant() {
        return degréEntrant;
    }

    /** Accesseur pour le champ sommet
     * @return La liste des sommets du graphe
     */
    public List<Integer> getSommets(){
        return sommets;
    }
}
