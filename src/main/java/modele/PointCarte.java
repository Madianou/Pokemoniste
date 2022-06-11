package modele;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** Affiche un point rouge sur la carte */
public class PointCarte extends MapLayer implements ConstantesPokemoniste{

    private final MapPoint mapPoint;
    private final Circle circle;

    public PointCarte(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
        this.circle = new Circle(TAILLE_POINT, Color.RED);
        this.getChildren().add(circle);
    }
    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());
        /* Déplace le cercle selon les coordonnées du point */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());
    }
}
