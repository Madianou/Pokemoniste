package com.example.pokemoniste;

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
    @Override
    public void start(Stage stage) throws IOException {

        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/files/scenario_2_1.txt"));
        //System.out.println(carte);
        System.out.println(scenario.toStringCommeFich());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}