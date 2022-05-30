package vue;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class PagePrésentationDroite extends VBox {
    private static ScrollPane chChemin;

    public PagePrésentationDroite(){
        chChemin = new ScrollPane();
        this.getChildren().add(chChemin);
    }
}
