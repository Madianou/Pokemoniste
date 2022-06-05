package vue;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PageMembreGauche extends VBox {
    public static TextArea chArea;
    public PageMembreGauche(){
        chArea = new TextArea();
        this.getChildren().add(chArea);
    }
    public static void setArea(String string){
        chArea.setText(string);
    }
}
