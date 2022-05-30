package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.URL;

public class PageAccueil extends VBox {
    private static Image chImage;
    private static Button chBouton;

    public PageAccueil(){

        HBox boxPokemon = new HBox(10);
        HBox boxBouton = new HBox(10);

        final ImageView imageView = new ImageView();

        String url = new File("images/Pokemoniste.png").toURI().toString();
        //Il faut changer le chemin pour pouvoir accéder à l'image.

        chImage = new Image(url);
        imageView.setImage(chImage);
        boxPokemon.setAlignment(Pos.CENTER);

        boxPokemon.getChildren().add(imageView);

        chBouton = new Button("Accéder à l'application");

        boxBouton.setAlignment(Pos.CENTER);
        boxBouton.setHgrow(chBouton, Priority.ALWAYS);
        boxBouton.getChildren().add(chBouton);

        this.getChildren().addAll(boxPokemon, boxBouton);
    }
}
