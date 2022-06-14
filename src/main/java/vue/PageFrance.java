package vue;

import com.gluonhq.maps.MapLayer;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageFrance extends HBox implements ConstantesPokemoniste {

    private static String chUrlScenario = URL_SCENARIO[0];
    private static Scenario chScenario;
    private static TilePane chTilePane;
    private static MapView chMap;
    private static Carte chVille;

    public PageFrance() throws IOException {
        VBox VBoxGauche = new VBox();
        VBoxGauche.setSpacing(ESPACEMENT);
        VBoxGauche.setMinWidth(LARGEUR/2);
        VBox VBoxDroite = new VBox();
        VBoxDroite.setSpacing(ESPACEMENT);
        VBoxDroite.setMaxWidth(LARGEUR/2);

        chVille = new Carte();
        chScenario = Scenario.lectureScenario(new File(chUrlScenario));

        chTilePane = doTilePane(chUrlScenario);
        chTilePane.setPrefColumns(4);
        VBoxGauche.getChildren().add(chTilePane);
        this.setHgrow(VBoxGauche, Priority.ALWAYS);

        chMap = doMap((ArrayList<String>) chScenario.getMembresListe());
        MapPoint mapPoint = new MapPoint(47.337710, 2.030016);
        chMap.setZoom((5.9));
        chMap.flyTo(0,mapPoint,0.1);
        VBoxDroite.getChildren().add(chMap);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);
    }

    public static void setUrlScenario(String url) throws IOException {
        chUrlScenario = url;
        System.out.println(chUrlScenario);
    }
    public static void setScenario(Scenario scenario) throws IOException {
        chScenario = scenario;
        chTilePane = doTilePane(chUrlScenario);
        chMap = doMap((ArrayList<String>) chScenario.getMembresListe());
    }
    public static TilePane doTilePane(String url) throws IOException {
        System.out.println("3");
        TilePane tilePane = new TilePane();
        ToggleGroup toggleGroup = new ToggleGroup();
        Scenario scenario = Scenario.lectureScenario(new File(url));

        for (String i : scenario.getMembresListe()){
            ToggleButton bouton = new ToggleButton(i);
            bouton.setUserData(i);
            bouton.setToggleGroup(toggleGroup);
            tilePane.getChildren().add(bouton);
        }
        return tilePane;
    }
    public static MapView doMap(ArrayList<String> liste) throws IOException {
        System.out.println("4");
        MapView mapView = new MapView();
        Membres gens = new Membres();
        Map ville = gens.getListe();

        for (String membre : liste){
            System.out.println(chVille.getVillesIndicés());

            Double x = POINT_VILLE[chVille.getVillesIndicés().get(ville.get(membre))][0];
            Double y = POINT_VILLE[chVille.getVillesIndicés().get(ville.get(membre))][1];
            MapPoint mapPoint = new MapPoint(x,y);
            MapLayer mapLayer = new PointCarte(mapPoint);
            mapView.addLayer(mapLayer);
        }
        System.out.println("5");
        return mapView;
    }
}