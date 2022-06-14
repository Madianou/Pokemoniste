package vue;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;
import modele.Scenario;

import java.io.File;
import java.io.IOException;


public class PageMembre extends HBox implements ConstantesPokemoniste {
    public static TextArea chAcheteur;
    public static TextArea chVendeur;
    public static TextArea chArea;

    public PageMembre() throws IOException {
        this.setSpacing(ESPACEMENT);

        VBox VBoxGauche = new VBox();
        VBoxGauche.setSpacing(ESPACEMENT);
        VBox VBoxDroite = new VBox();
        VBoxDroite.setSpacing(ESPACEMENT);

        Scenario scenario = Scenario.lectureScenario(new File(URL_SCENARIO[0]));
        String membresInit = scenario.getMembresString();

        chArea = new TextArea(MEMBRE + membresInit);
        chArea.setMinWidth(LARGEUR/2);
        chArea.setMinHeight(HAUTEUR+10);
        VBoxGauche.getChildren().add(chArea);

        chAcheteur = new TextArea();
        chAcheteur.setText(ACHETEUR + scenario.getAcheteursString());
        chVendeur = new TextArea();
        chVendeur.setText(VENDEUR + scenario.getVendeursString());
        chAcheteur.setMinWidth(LARGEUR/2);
        chAcheteur.setMinHeight(HAUTEUR/2);
        chVendeur.setMinWidth(LARGEUR/2);
        chVendeur.setMinHeight(HAUTEUR/2);
        VBoxDroite.getChildren().addAll(chAcheteur, chVendeur);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);

    }
    public void setMembres(String membres, String acheteurs, String vendeurs){
        chArea.clear();
        chAcheteur.clear();
        chVendeur.clear();
        chArea.setText(MEMBRE + membres);
        chAcheteur.setText(ACHETEUR + acheteurs);
        chVendeur.setText(VENDEUR + vendeurs);
    }
}
