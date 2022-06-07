package vue;

import com.gluonhq.maps.MapLayer;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.TilePane;
import modele.ConstantesPokemoniste;
import modele.PointCarte;

public class PageFrance extends HBox implements ConstantesPokemoniste {

    public PageFrance(){
        TilePane tilePane = new TilePane();
        ToggleGroup toggleGroup = new ToggleGroup();
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

        this.getChildren().add(mapView);
    }
}