package vue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PagePrésentationDroite extends VBox {
    private static ScrollPane chChemin;
    private static Button chBouton;

    public PagePrésentationDroite(){
        chChemin = new ScrollPane();

        HBox bouton = new HBox();
        chBouton = new Button("Bouton vers France");
        bouton.getChildren().add(chBouton);
        bouton.setAlignment(Pos.CENTER);
        this.getChildren().addAll(chChemin, bouton);
    }
}
