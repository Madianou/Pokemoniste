package vue;

import javafx.scene.layout.HBox;

public class PagePrésentation extends HBox {


    public PagePrésentation(){
        PagePrésentationGauche gauche = new PagePrésentationGauche();
        PagePrésentationDroite droite = new PagePrésentationDroite();

        this.getChildren().addAll(gauche,droite);
    }
}
