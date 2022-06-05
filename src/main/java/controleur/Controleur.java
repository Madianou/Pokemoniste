package controleur;

import com.example.pokemoniste.HelloApplication;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import vue.VBoxRoot;

public class Controleur implements EventHandler {

    @Override
    public void handle(Event event) {


        if (event.getSource() instanceof MenuItem){
            String url = VBoxRoot.getScenario();
            HelloApplication.setUrlScenario(url);
            System.out.println("Let's go");
        }
    }
}

