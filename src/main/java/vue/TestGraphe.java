package vue;

import javafx.scene.layout.VBox;
import modele.Carte;
import modele.GrapheOriente;
import modele.Membres;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestGraphe extends VBox {
    public  TestGraphe() throws IOException {
        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/main/test/ressource/scenario_test"));
        GrapheOriente test = new GrapheOriente(scenario, carte, membres);
        System.out.println(GrapheOriente.parcoursLargeurToString((HashMap<Integer, int[]>) test.parcoursLargeur(9,carte)));
        System.out.println(test.sourceEnSource(carte));
        //System.out.println(test.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),test.getSource(),test.getDegreEntrant(),carte,0));
        List<List<Integer>> chaine = test.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),test.getSource(),test.getDegreEntrant());

        System.out.println(GrapheOriente.BestChemin(chaine,carte));
    }
}
