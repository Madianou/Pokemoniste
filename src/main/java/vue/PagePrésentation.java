package vue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;

public class PagePrésentation extends HBox implements ConstantesPokemoniste {
    private static TextArea chCheminBref;
    private static ScrollPane chMembre;
    private static ScrollPane chChemin;
    private static Button chBouton;

    public PagePrésentation(){
        this.setSpacing(ESPACEMENT);

        VBox VBoxGauche = new VBox();
        VBox VBoxDroite = new VBox();

        chCheminBref = new TextArea();
        chMembre = new ScrollPane();

        VBoxGauche.getChildren().addAll(chCheminBref, chMembre);

        chChemin = new ScrollPane();

        HBox bouton = new HBox();
        chBouton = new Button("Bouton vers France");
        bouton.getChildren().add(chBouton);
        bouton.setAlignment(Pos.CENTER);
        bouton.setHgrow(chBouton, Priority.ALWAYS);

        VBoxDroite.getChildren().addAll(chChemin, bouton);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);
    }
}
