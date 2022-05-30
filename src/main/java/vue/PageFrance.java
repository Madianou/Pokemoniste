package vue;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PageFrance extends VBox {

    public PageFrance(){
        Label label = new Label("France");
        this.getChildren().add(label);
    }
}
