package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.*;
import vue.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controleur implements EventHandler, ConstantesPokemoniste {
    private static String chUrlScenario = URL_SCENARIO[0];
    private Map<String,List<List<Integer>>> chParcoursComplet = new HashMap<>();

    @Override
    public void handle(Event event) {
        PagePrésentation presentation = VBoxRoot.getPresentation();
        PageMembre membre = VBoxRoot.getMembre();
        PageFrance france = VBoxRoot.getFrance();
        PageAccueil accueil = VBoxRoot.getAccueil();


        if (event.getSource() instanceof MenuItem) {
            chUrlScenario = URL_SCENARIO[(int) ((MenuItem) event.getSource()).getUserData()];
            Carte carte;
            Membres membres;
            try {
                presentation.setUrlScenario(chUrlScenario);
                france.setUrlScenario(chUrlScenario);
                carte = new Carte();
                membres = new Membres();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(presentation.getScenario());
            membre.setMembres(presentation.getScenario().getMembresString(), presentation.getScenario().getAcheteursString(), presentation.getScenario().getVendeursString());

            GrapheOriente graphe = new GrapheOriente(presentation.getScenario(), carte, membres);
            //List<List<Integer>> chaine = graphe.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),graphe.getSource(),graphe.getDegreEntrant());
            presentation.setTextChemin(graphe.toStringSimplifié());
            presentation.setTextCheminBref(graphe.BestChemin(chParcoursComplet.get(chUrlScenario), carte));
        }
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getId().equals("Accueil")){
                for (String elem : URL_SCENARIO){
                    GrapheOriente graphe = null;

                    try {
                        graphe = new GrapheOriente(Scenario.lectureScenario(new File(elem)),new Carte(), new Membres());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    List<List<Integer>> chaine = graphe.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),graphe.getSource(),graphe.getDegreEntrant());
                    chParcoursComplet.put(elem,chaine);
                }
                VBoxRoot.removeAccueil();
            }
            if (((Button) event.getSource()).getId().equals("France")){
                VBoxRoot.goFrance();
            }
        }
    }
}

