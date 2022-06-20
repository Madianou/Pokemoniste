package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import modele.ConstantesPokemoniste;
import vue.*;

import java.io.IOException;

public class Controleur implements EventHandler, ConstantesPokemoniste {
    private static String chUrlScenario = URL_SCENARIO[0];

    @Override
    public void handle(Event event) {
        PagePrésentation presentation = VBoxRoot.getPresentation();
        PageMembre membre = VBoxRoot.getMembre();
        PageFrance france = VBoxRoot.getFrance();
        PageAccueil accueil = VBoxRoot.getAccueil();

        if (event.getSource() instanceof MenuItem){
            chUrlScenario = URL_SCENARIO[(int) ((MenuItem) event.getSource()).getUserData()];
            try {
                presentation.setUrlScenario(chUrlScenario);
                france.setUrlScenario(chUrlScenario);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(presentation.getScenario());
            membre.setMembres(presentation.getScenario().getMembresString(), presentation.getScenario().getAcheteursString(), presentation.getScenario().getVendeursString());
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

