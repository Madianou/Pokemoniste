package vue;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.scene.layout.TilePane;

public class PageFrance extends HBox {

    public PageFrance(){
        TilePane tilePane = new TilePane();
        ToggleGroup toggleGroup = new ToggleGroup();


        MapView mapView = new MapView();
        MapPoint mapPoint = new MapPoint(46.227638, 2.213749);
        mapView.setZoom(6);
        mapView.flyTo(0,mapPoint,0.1);


        Label label = new Label("France");

        this.getChildren().addAll(mapView);
    }
}