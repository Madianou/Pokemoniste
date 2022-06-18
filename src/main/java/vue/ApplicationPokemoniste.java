package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;

import java.io.File;
import java.io.IOException;

public class ApplicationPokemoniste extends Application implements ConstantesPokemoniste {
    private static String chUrlScenario = URL_SCENARIO[0];
    private static Scenario scenario;

    public void start (Stage stage) throws IOException {
        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        //VBoxRoot root = new VBoxRoot();
        TestGraphe root = new TestGraphe();
        root.setSpacing(ESPACEMENT);

        Scene scene = new Scene(root,1280, 720);

        File css = new File("css"+File.separator+"Styles.css");
        scene.getStylesheets().add(css.toURI().toString());

        stage.setScene(scene);
        stage.setTitle("Pokemoniste");
        stage.show();
    }

    public static void main(String[] args) {
        ApplicationPokemoniste.launch(args);
    }
    public static void setUrlScenario(String url){
        chUrlScenario = url;
        System.out.println(url);
    }
    public static String getUrlScenario(){
        return chUrlScenario;
    }
    public static Scenario getScenario(){
        return scenario;
    }
}
