package vue;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PagePrésentationGauche extends VBox {
    private static TextArea chCheminBref;
    private static ScrollPane chMembre;

    public PagePrésentationGauche(){
        chCheminBref = new TextArea();
        chMembre = new ScrollPane();
        this.getChildren().addAll(chCheminBref, chMembre);
    }
}
