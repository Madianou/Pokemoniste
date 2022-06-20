package vue;

import com.gluonhq.maps.MapLayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import java.util.concurrent.TimeUnit;

public class PageFrance extends HBox implements ConstantesPokemoniste {

    private static String chUrlScenario = URL_SCENARIO[0];
    private static Scenario chScenario;
    private static TilePane chTilePane;
    private static MapView chMap;
    private static Carte chVille;
    private static VBox VBoxGauche;
    private static VBox VBoxDroite;

    public PageFrance() throws IOException {
        VBoxGauche = new VBox();
        VBoxGauche.setSpacing(ESPACEMENT);
        VBoxGauche.setMinWidth(LARGEUR/2);
        VBoxDroite = new VBox();
        VBoxDroite.setSpacing(ESPACEMENT);
        VBoxDroite.setMinWidth(LARGEUR/2);

        chVille = new Carte();
        chScenario = Scenario.lectureScenario(new File(chUrlScenario));


        doTilePane(chUrlScenario);
        chTilePane.setPrefColumns(4);
        this.setHgrow(VBoxGauche, Priority.ALWAYS);

        doMap();
        doPoint((ArrayList<String>) chScenario.getMembresListe());

        this.getChildren().addAll(VBoxGauche, VBoxDroite);
    }

    public static void setUrlScenario(String url) throws IOException {
        chUrlScenario = url;
        chScenario = Scenario.lectureScenario(new File(url));
        doTilePane(chUrlScenario);
        doMap();
        doPoint((ArrayList<String>) chScenario.getMembresListe());
    }
    public static void doTilePane(String url) throws IOException {
        VBoxGauche.getChildren().remove(chTilePane);
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.TOP_CENTER);
        tilePane.setHgap(ESPACEMENT);
        tilePane.setVgap(ESPACEMENT);
        ToggleGroup toggleGroup = new ToggleGroup();
        Scenario scenario = Scenario.lectureScenario(new File(url));
        Membres gens = new Membres();
        Map ville = gens.getListe();

        for (String i : scenario.getMembresListe()){
            Label villeString = new Label((String) ville.get(i));
            villeString.setId("VillePane");
            ToggleButton bouton = new ToggleButton(i + "\n" + villeString.getText());
            bouton.setUserData(i);
            bouton.setToggleGroup(toggleGroup);
            tilePane.getChildren().add(bouton);
        }
        chTilePane = tilePane;
        VBoxGauche.getChildren().add(chTilePane);
    }
    public static void doMap() throws IOException {
        VBoxDroite.getChildren().remove(chMap);
        MapView mapView = new MapView();
        MapPoint mapPoint = new MapPoint(47.337710, 1.9);
        mapView.setZoom((6));
        mapView.setCenter(mapPoint);
        chMap = mapView;
        VBoxDroite.getChildren().add(chMap);
    }
    public static void doPoint(ArrayList<String> liste) throws IOException {
        Membres gens = new Membres();
        Map ville = gens.getListe();

        for (String membre : liste){
            Double x = POINT_VILLE[chVille.getVillesIndicés().get(ville.get(membre))][0];
            Double y = POINT_VILLE[chVille.getVillesIndicés().get(ville.get(membre))][1];
            MapPoint mapPoint = new MapPoint(x,y);
            MapLayer mapLayer = new PointCarte(mapPoint);
            chMap.addLayer(mapLayer);
        }
        MapPoint velizy = new MapPoint(POINT_VILLE[chVille.getVillesIndicés().get("Velizy")][0], POINT_VILLE[chVille.getVillesIndicés().get("Velizy")][1]);
        MapLayer velizyLayer = new PointCarte(velizy);
        chMap.addLayer(velizyLayer);
    }
    public static void fly(MapPoint point){
        chMap.flyTo(0,point,0.5);
    }
}