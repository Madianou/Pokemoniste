package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;

import java.io.IOException;

public class VBoxRoot extends VBox implements ConstantesPokemoniste{
    private static Controleur chControleur;
    private static PagePrésentation chPrésentation;
    private static PageMembre chMembre;
    private static PageFrance chFrance;
    private static Menu chScenario;
    private static Menu chPage;
    private static MenuItem chScenarioItem;
    private static MenuItem chPageItem;

    public VBoxRoot() throws IOException {
        chControleur = new Controleur();
        chPrésentation = new PagePrésentation();
        chMembre = new PageMembre();
        chFrance = new PageFrance();

        MenuBar bar = new MenuBar();
        chScenario = new Menu("Selection des fichiers");
        chPage = new Menu("Selection des pages");
        bar.getMenus().addAll(chScenario,chPage);

        Node [] components = new Node[3];
        components[0] = chPrésentation;
        chPrésentation.setId("opaque");
        components[1] = chMembre;
        chMembre.setId("opaque");
        components[2] = chFrance;
        chFrance.setId("opaque");

        this.getChildren().add(chPrésentation);

        StackPane stackPane = new StackPane(components);
        stackPane.getChildren().get(0).toFront();


        for (int i=0; i < MENU_FICHIERS.length; i++) {
            chScenarioItem = new MenuItem(MENU_FICHIERS[i]);
            chScenarioItem.setUserData(i);
            chScenarioItem.setOnAction(chControleur);
            chScenario.getItems().add(chScenarioItem);
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
            chPage.getItems().add(menuItem2);
        }

        this.getChildren().addAll(bar,stackPane);
    }

    public static PagePrésentation getPresetation(){
        return chPrésentation;
    }
    public static PageMembre getMembre(){
        return chMembre;
    }
    public static PageFrance getFrance(){
        return chFrance;
    }
    public static String getScenario(){
        String url = MENU_FICHIERS[(int) chScenarioItem.getUserData()];
        return url;
    }
    public static int getIndiceScenario(){
        int indice = (int) chScenarioItem.getUserData();
        return indice;
    }
    public static Controleur getControleur(){
        return chControleur;
    }

}
