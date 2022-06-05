package vue;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PageMembreDroite extends VBox {
    public static TextArea chAcheteur;
    public static TextArea chVendeur;
    public PageMembreDroite(){
        chAcheteur = new TextArea();
        chVendeur = new TextArea();
        this.getChildren().addAll(chAcheteur, chVendeur);

    }
}
