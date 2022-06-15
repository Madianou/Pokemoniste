package vue;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import modele.ConstantesPokemoniste;

import java.io.File;
import java.net.URL;

public class PageAccueil extends VBox implements ConstantesPokemoniste {
    private static Image chImage;
    private static Button chBouton;

    public PageAccueil(){
        this.setSpacing(ESPACEMENT);
        Controleur controleur = VBoxRoot.getControleur();

        HBox boxPokemon = new HBox();
        HBox boxBouton = new HBox();

        final ImageView imageView = new ImageView();

        String url = new File("images/Pokemoniste.png").toURI().toString();

        chImage = new Image(url);
        imageView.setImage(chImage);
        boxPokemon.setAlignment(Pos.CENTER);

        boxPokemon.getChildren().add(imageView);

        chBouton = new Button("Accéder à l'application");
        chBouton.setId("Accueil");
        chBouton.setOnAction(controleur);

        boxBouton.setAlignment(Pos.CENTER);
        boxBouton.setHgrow(chBouton, Priority.ALWAYS);
        boxBouton.getChildren().add(chBouton);

        this.getChildren().addAll(boxPokemon, boxBouton);
    }
}
