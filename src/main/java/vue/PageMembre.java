package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;


public class PageMembre extends HBox implements ConstantesPokemoniste {
    public static TextArea chAcheteur;
    public static TextArea chVendeur;
    public static TextArea chArea;

    public PageMembre(){
        this.setSpacing(ESPACEMENT);

        VBox VBoxGauche = new VBox();
        VBox VBoxDroite = new VBox();

        chArea = new TextArea();
        VBoxGauche.getChildren().add(chArea);

        chAcheteur = new TextArea();
        chVendeur = new TextArea();
        VBoxDroite.getChildren().addAll(chAcheteur, chVendeur);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);

    }
}
