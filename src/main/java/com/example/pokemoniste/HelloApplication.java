package com.example.pokemoniste;

import javafx.scene.layout.VBox;
import vue.VBoxRoot;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Carte;
import modele.Membres;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

import static modele.ConstantesPokemoniste.URL_SCENARIO;

public class HelloApplication extends Application {
    private static VBox chRoot;
    private static Scene chScene;
    private static String chUrlScenario = URL_SCENARIO[0];
    @Override
    public void start(Stage stage) throws IOException {
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File(chUrlScenario));
        //GrapheOriente test = new GrapheOriente(scenario,carte,membres);
        //System.out.println(test);
        chRoot = new VBoxRoot();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        chScene = new Scene(chRoot, 1280, 720);

        File css = new File("css"+File.separator+"Styles.css");
        chScene.getStylesheets().add(css.toURI().toString());

        stage.setTitle("Page d'accueil");
        stage.setScene(chScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void setRoot(VBox parBox){
        chRoot = new VBoxRoot();
        chScene = new Scene(chRoot, 700, 400);
    }
    public static void setUrlScenario(String string){
        chUrlScenario = string;
    }
}