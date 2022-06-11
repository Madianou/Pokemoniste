package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.ConstantesPokemoniste;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

public class ApplicationPokemoniste extends Application implements ConstantesPokemoniste {
    private static String chUrlScenario = URL_SCENARIO[0];

    public void start (Stage stage) throws IOException {
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        VBoxRoot root = new VBoxRoot();
        root.setSpacing(ESPACEMENT);

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
    public static void setUrlScenario(int indice){
        String url = URL_SCENARIO[indice];
        chUrlScenario = url;
    }
    public static String getScenario(){
        return chUrlScenario;
    }
}
