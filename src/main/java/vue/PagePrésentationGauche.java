package vue;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PagePrésentationGauche extends VBox {
    private static TextArea chCheminBref;

    public PagePrésentationGauche(){
        chCheminBref = new TextArea();
        this.getChildren().add(chCheminBref);
    }
}
