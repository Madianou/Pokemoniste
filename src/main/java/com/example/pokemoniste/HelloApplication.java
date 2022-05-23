package com.example.pokemoniste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Carte;
import modele.GrapheOriente;
import modele.Membres;
import modele.Scenario;
import vue.HBoxTest;
import vue.PageAccueil;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/files/scenario/scenario_2_1.txt"));
        //GrapheOriente test = new GrapheOriente(scenario,carte,membres);
        //System.out.println(test);
        PageAccueil root = new PageAccueil();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Page d'accueil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}