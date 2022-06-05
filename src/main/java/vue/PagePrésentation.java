package vue;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PagePrésentation extends HBox {


    public PagePrésentation(){
        PagePrésentationGauche gauche = new PagePrésentationGauche();
        PagePrésentationDroite droite = new PagePrésentationDroite();
        HBox.setHgrow(droite, Priority.ALWAYS);

        this.getChildren().addAll(gauche,droite);
    }
}
