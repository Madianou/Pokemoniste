package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.Carte;
import modele.ConstantesPokemoniste;
import modele.GrapheOriente;
import modele.Membres;
import vue.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controleur implements EventHandler, ConstantesPokemoniste {
    private static String chUrlScenario = URL_SCENARIO[0];

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
            List<List<Integer>> chaine = graphe.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),graphe.getSource(),graphe.getDegreEntrant());
            presentation.setTextChemin(graphe.toStringSimplifié());
            presentation.setTextCheminBref(graphe.BestChemin(chaine, carte));
        }
        if (event.getSource() instanceof Button){
            if (((Button) event.getSource()).getId().equals("Accueil")){
                VBoxRoot.removeAccueil();
            }
            if (((Button) event.getSource()).getId().equals("France")){
                VBoxRoot.goFrance();
            }
        }
    }
}

