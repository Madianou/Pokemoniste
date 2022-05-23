package Vue;

import javafx.scene.layout.VBox;

public class VBoxRoot extends VBox {
    private static PageAccueil chAccueil;

    public VBoxRoot(){


        chAccueil = new PageAccueil();

        this.getChildren().add(chAccueil);

    }
    public static PageAccueil getAccueil(){
        return chAccueil;
    }

}
