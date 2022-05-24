package vue;

import controleur.Controleur;
import javafx.scene.layout.VBox;

public class AccueilRoot extends VBox{
    private static PageAccueil chAccueil;
    private static Controleur chControleur;

    public AccueilRoot(){
        chControleur = new Controleur();
        chAccueil = new PageAccueil();

        this.getChildren().add(chAccueil);
    }

    public static PageAccueil getAccueil(){
        return chAccueil;
    }
    public static Controleur getControleur(){
        return chControleur;
    }
}
