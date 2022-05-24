package vue;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PageAccueil extends VBox {
    private static Image chImage;
    private static Button chBouton;

    public PageAccueil(){

        HBox boxPokemon = new HBox(10);
        HBox boxBouton = new HBox(10);

        final ImageView imageView = new ImageView();

        String url = "C:\\Users\\Raphaël\\Documents\\Ecole\\2e semestre\\Saé\\Saé Commune - Dev\\Pokemoniste\\images\\Pokemoniste.png";
        //Il faut changer le chemin pour pouvoir accéder à l'image.

        chImage = new Image(url);
        imageView.setImage(chImage);
        imageView.setFitHeight(200);
        imageView.setFitWidth(700);

        boxPokemon.getChildren().add(imageView);

        chBouton = new Button("Accéder à l'application");
        chBouton.setAlignment(Pos.CENTER);
        boxBouton.setHgrow(chBouton, Priority.ALWAYS);
        boxBouton.getChildren().add(chBouton);

        this.getChildren().addAll(boxPokemon, boxBouton);
    }
}
