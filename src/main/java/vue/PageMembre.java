package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PageMembre extends HBox {

    public PageMembre(){
        PageMembreGauche gauche = new PageMembreGauche();
        PageMembreDroite droite = new PageMembreDroite();
        this.getChildren().addAll(gauche, droite);
    }
}
