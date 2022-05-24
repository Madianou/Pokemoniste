package com.example.pokemoniste;

import javafx.scene.layout.VBox;
import vue.PageAccueil;
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

public class HelloApplication extends Application {
    private static VBox chRoot;
    private static Scene chScene;
    @Override
    public void start(Stage stage) throws IOException {

        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/files/scenario/scenario_2_1.txt"));
        //GrapheOriente test = new GrapheOriente(scenario,carte,membres);
        //System.out.println(test);
        chRoot = new PageAccueil();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        chScene = new Scene(chRoot, 700, 400);
        stage.setTitle("Page d'accueil");
        stage.setScene(chScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void setRoot(VBox parBox){
        chRoot = parBox;
        chScene = new Scene(chRoot, 700, 400);
    }
}