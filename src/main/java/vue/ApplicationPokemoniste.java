package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

public class ApplicationPokemoniste extends Application {

    public void start (Stage stage){
        VBoxRoot root = new VBoxRoot();
        Scene scene = new Scene(root,500, 500);

        File css = new File("css"+File.separator+"Styles.css");
        scene.getStylesheets().add(css.toURI().toString());

        stage.setScene(scene);
        stage.setTitle("Pokemoniste");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
