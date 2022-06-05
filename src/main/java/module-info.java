module com.example.pokemoniste {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.gluonhq.maps;

    opens com.example.pokemoniste to javafx.fxml;
    exports com.example.pokemoniste;
}