package vue;

import com.gluonhq.maps.MapLayer;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;
import modele.PointCarte;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

public class PageFrance extends HBox implements ConstantesPokemoniste {

    private static String chUrlScenario = URL_SCENARIO[0];
    private static Scenario chScenario;

    public PageFrance() throws IOException {
        VBox VBoxGauche = new VBox();
        VBoxGauche.setSpacing(ESPACEMENT);
        VBox VBoxDroite = new VBox();
        VBoxDroite.setSpacing(ESPACEMENT);

        TilePane tilePane = new TilePane();
        ToggleGroup toggleGroup = new ToggleGroup();

        chScenario = Scenario.lectureScenario(new File(chUrlScenario));

        for (String i : chScenario.getMembresListe()){
            ToggleButton bouton = new ToggleButton(i);
            bouton.setUserData(i);
            bouton.setToggleGroup(toggleGroup);
        }
        tilePane.setPrefColumns(4);


        Label label = new Label("Hello");
        VBoxGauche.getChildren().addAll(tilePane, label);


        MapView mapView = new MapView();

        //test avec toutes les villes
        for (int i=0; i < POINT_VILLE.length; i++){
            MapPoint mapPoint = new MapPoint(POINT_VILLE[i][0],POINT_VILLE[i][1]);
            MapLayer mapLayer = new PointCarte(mapPoint);
            mapView.addLayer(mapLayer);
        }

        MapPoint mapPoint = new MapPoint(47.337710, 2.635016);
        mapView.setZoom(6);
        mapView.flyTo(0,mapPoint,0.1);

        MapLayer mapLayer = new PointCarte(mapPoint);
        mapView.addLayer(mapLayer);

        VBoxDroite.getChildren().add(mapView);

        this.getChildren().addAll(VBoxGauche, VBoxDroite);
    }
    public static void setUrlScenario(String url) throws IOException {
        chUrlScenario = url;
        chScenario = Scenario.lectureScenario(new File(chUrlScenario));
    }
}