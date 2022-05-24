package vue;

import javafx.scene.layout.VBox;

public class VBoxRoot extends VBox {
    private static PagePrésentation chPrésetation;

    public VBoxRoot(){

        chPrésetation = new PagePrésentation();

        this.getChildren().add(chPrésetation);

    }
    public static PagePrésentation getPrésetation(){
        return chPrésetation;
    }

}
