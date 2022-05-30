package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;

public class VBoxRoot extends VBox implements ConstantesPokemoniste{
    private static PagePrésentation chPrésetation;
    private static PageMembre chMembre;
    private static PageFrance chFrance;

    public VBoxRoot(){
        chPrésetation = new PagePrésentation();
        chMembre = new PageMembre();
        chFrance = new PageFrance();

        MenuBar bar = new MenuBar();
        Menu menuFichier = new Menu("Selection des fichiers");
        Menu menuPage = new Menu("Selection des pages");
        bar.getMenus().addAll(menuFichier,menuPage);

        Node [] components = new Node[3];
        components[0] = chPrésetation;
        chPrésetation.setId("opaque");
        components[1] = chMembre;
        chMembre.setId("opaque");
        components[2] = chFrance;
        chFrance.setId("opaque");

        this.getChildren().add(chPrésetation);

        StackPane stackPane = new StackPane(components);
        stackPane.getChildren().get(0).toFront();


        for (int i=0; i < MENU_FICHIERS.length; i++) {
            MenuItem menuItem1 = new MenuItem(MENU_FICHIERS[i]);
            menuItem1.setUserData(i);

            menuFichier.getItems().add(menuItem1);
        }

        for (int y=0; y< MENU_PAGES.length; y++){
            MenuItem menuItem2 = new MenuItem(MENU_PAGES[y]);
            menuItem2.setUserData(y);
            menuItem2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    int data = (int) ((MenuItem) actionEvent.getSource()).getUserData();
                    System.out.println(data);
                    int last = stackPane.getChildren().size() - 1;
                    while (stackPane.getChildren().get(last) != components[data]) {
                        stackPane.getChildren().get(0).toFront();
                    }
                }
            });
            menuPage.getItems().add(menuItem2);
        }

        this.getChildren().addAll(bar,stackPane);
    }
    public static PagePrésentation getPresetation(){
        return chPrésetation;
    }

}
