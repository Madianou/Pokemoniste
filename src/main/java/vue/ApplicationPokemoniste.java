package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

import static modele.ConstantesPokemoniste.URL_SCENARIO;

public class ApplicationPokemoniste extends Application {
    private static String chUrlScenario = URL_SCENARIO[0];

    public void start (Stage stage) throws IOException {
        VBoxRoot root = new VBoxRoot();
        Scene scene = new Scene(root,1280, 720);

        File css = new File("css"+File.separator+"Styles.css");
        scene.getStylesheets().add(css.toURI().toString());

        Scenario scenario = Scenario.lectureScenario(new File(chUrlScenario));

        stage.setScene(scene);
        stage.setTitle("Pokemoniste");
        stage.show();
    }

    public static void main(String[] args) {
        ApplicationPokemoniste.launch(args);
    }
    public static void setUrlScenario(String string){
        chUrlScenario = string;
    }
}
