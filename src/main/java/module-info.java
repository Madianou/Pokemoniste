module com.example.pokemoniste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.gluonhq.maps;

    opens vue to javafx.fxml;
    exports vue;
}