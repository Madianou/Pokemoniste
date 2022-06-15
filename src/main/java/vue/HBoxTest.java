package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import modele.Carte;
import modele.GrapheOriente;
import modele.Membres;
import modele.Scenario;

import java.io.File;
import java.io.IOException;

import static modele.Scenario.lectureScenario;

public class HBoxTest extends HBox {

    public HBoxTest() throws IOException {
        Carte carte = new Carte();
        Scenario scenario = lectureScenario(new File("src/files/scenario/scenario_0.txt"));
        Membres membres = new Membres();
        GrapheOriente g = new GrapheOriente(scenario,carte,membres);
        TextArea txt = new TextArea();
        TextArea txt2 = new TextArea();
        TextArea txt3 = new TextArea();

        String chaine = "";
        for(int i = 0; i<scenario.getAllVendeurs().size(); i++){
            chaine = chaine + scenario.getAllVendeurs().get(i) + " -> " + scenario.getAllAcheteurs().get(i)
                    + " | "+membres.getListe().get(scenario.getAllVendeurs().get(i))+ " -> " +membres.getListe().get(scenario.getAllAcheteurs().get(i))
                    +" : "+ carte.getDistance()[carte.getVillesIndicés().get(membres.getListe().get(scenario.getAllVendeurs().get(i)))][carte.getVillesIndicés().get(membres.getListe().get(scenario.getAllAcheteurs().get(i)))]
                    +" km "+"\n";
        }
        txt2.setText(chaine);

        this.getChildren().add(txt);
        txt.setText(membres.toString());

        File f = new File("src/files/scenario");
        File[] files = f.listFiles();

        ComboBox fichierDeroulant = new ComboBox<>();

        for (int i = 0; i < files.length; i++) {
            fichierDeroulant.getItems().add(files[i].getName());
        }
        fichierDeroulant.getSelectionModel().select("scenario_0.txt");
        this.getChildren().addAll(fichierDeroulant,txt2);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Scenario scenario = null;
                try {
                    scenario = lectureScenario(new File("src/files/scenario/"+fichierDeroulant.getValue()));

                } catch (IOException e) {
                    e.printStackTrace();
                }
                String chaine = "";
                for(int i = 0; i<scenario.getAllVendeurs().size(); i++){
                    chaine = chaine + scenario.getAllVendeurs().get(i) + " -> " + scenario.getAllAcheteurs().get(i)
                            + " | "+membres.getListe().get(scenario.getAllVendeurs().get(i))+ " -> " +membres.getListe().get(scenario.getAllAcheteurs().get(i))
                            +" : "+ carte.getDistance()[carte.getVillesIndicés().get(membres.getListe().get(scenario.getAllVendeurs().get(i)))][carte.getVillesIndicés().get(membres.getListe().get(scenario.getAllAcheteurs().get(i)))]
                            +" km "+"\n";
                }
                txt2.setText(chaine);
                GrapheOriente g2 = new GrapheOriente(scenario,carte,membres);
                txt3.setText(g2.toString());
            }
        };
        fichierDeroulant.setOnAction(event);

        txt3.setText(g.toString());
        this.getChildren().add(txt3);


    }
}
