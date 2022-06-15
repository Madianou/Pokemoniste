package vue;

import controleur.Controleur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import modele.ConstantesPokemoniste;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

public class PagePrésentation extends HBox implements ConstantesPokemoniste {
    private static TextArea chCheminBref;
    private static ScrollPane chMembre;
    private static ScrollPane chChemin;
    private static Button chBouton;
    private static String chUrlScenario = URL_SCENARIO[0];
    private static Scenario chScenario;

    public PagePrésentation() throws IOException {
        this.setSpacing(ESPACEMENT);
        Controleur controleur = VBoxRoot.getControleur();
        chScenario = Scenario.lectureScenario(new File(chUrlScenario));

        VBox VBoxGauche = new VBox();
        VBoxGauche.setMinWidth(LARGEUR/2);
        VBoxGauche.setSpacing(ESPACEMENT);
        VBox VBoxDroite = new VBox();
        VBoxDroite.setMinWidth(LARGEUR/2);
        VBoxDroite.setSpacing(ESPACEMENT);

        chCheminBref = new TextArea();
        chMembre = new ScrollPane();

        VBoxGauche.getChildren().addAll(chCheminBref, chMembre);

        chChemin = new ScrollPane();

        HBox bouton = new HBox();
        chBouton = new Button("Bouton vers France");
        chBouton.setId("France");
        chBouton.setOnAction(controleur);
        bouton.getChildren().add(chBouton);
        bouton.setAlignment(Pos.CENTER);
        bouton.setHgrow(chBouton, Priority.ALWAYS);

        VBoxDroite.getChildren().addAll(chChemin, bouton);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);
    }
    public static void setUrlScenario(String url) throws IOException {
        chUrlScenario = url;
        chScenario = Scenario.lectureScenario(new File(chUrlScenario));
    }
    public static Scenario getScenario(){
        return chScenario;
    }

}
