package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PageMembre extends VBox {

    public PageMembre(){
        Label label = new Label("Membre");
        this.getChildren().add(label);
    }
}
