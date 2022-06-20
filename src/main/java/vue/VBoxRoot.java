package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import modele.ConstantesPokemoniste;

import java.io.IOException;

public class VBoxRoot extends VBox implements ConstantesPokemoniste{
    private static Controleur chControleur;
    private static PagePrésentation chPrésentation;
    private static PageMembre chMembre;
    private static PageFrance chFrance;
    private static PageAccueil chAccueil;
    private static Menu chScenario;
    private static Menu chPage;
    private static MenuItem chScenarioItem;
    private static MenuItem chPageItem;
    private static StackPane chStackPanePage;
    private static Node [] chComponents;

    public VBoxRoot() throws IOException {
        chControleur = new Controleur();
        chPrésentation = new PagePrésentation();
        chMembre = new PageMembre();
        chFrance = new PageFrance();
        chAccueil = new PageAccueil();

        MenuBar bar = new MenuBar();
        bar.setId("bar");
        chScenario = new Menu("Selection des fichiers");
        chPage = new Menu("Selection des pages");
        bar.getMenus().addAll(chScenario,chPage);

        chComponents = new Node[4];
        chComponents [0] = chAccueil;
        chAccueil.setId("opaque");
        chComponents[1] = chPrésentation;
        chPrésentation.setId("opaque");
        chComponents[2] = chMembre;
        chMembre.setId("opaque");
        chComponents[3] = chFrance;
        chFrance.setId("opaque");

        this.getChildren().add(chPrésentation);

        chStackPanePage = new StackPane(chComponents);
        chStackPanePage.getChildren().get(0).toFront();

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
                    if (4 == chComponents.length){
                        removeAccueil();
                    }
                    int data = (int) ((MenuItem) actionEvent.getSource()).getUserData();
                    System.out.println(data);
                    int last = chStackPanePage.getChildren().size() - 1;
                    while (chStackPanePage.getChildren().get(last) != chComponents[data + 1]) {
                        chStackPanePage.getChildren().get(0).toFront();
                    }
                }
            });
            chPage.getItems().add(menuItem2);
        }

        this.getChildren().addAll(bar,chStackPanePage);
    }

    public static PagePrésentation getPresentation(){
        return chPrésentation;
    }
    public static PageMembre getMembre(){
        return chMembre;
    }
    public static PageFrance getFrance(){
        return chFrance;
    }
    public static PageAccueil getAccueil(){
        return chAccueil;
    };
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
    public static void removeAccueil(){
        chStackPanePage.getChildren().remove(chComponents[0]);
        while (chStackPanePage.getChildren().get(chStackPanePage.getChildren().size() - 1) != chComponents[1]) {
            chStackPanePage.getChildren().get(0).toFront();
        }
    }
    public static void goFrance(){
        while (chStackPanePage.getChildren().get(chStackPanePage.getChildren().size() - 1) != chComponents[3]) {
            chStackPanePage.getChildren().get(0).toFront();
        }
    }

}
