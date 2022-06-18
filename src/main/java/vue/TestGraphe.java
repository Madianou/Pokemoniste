package vue;

import javafx.scene.layout.VBox;
import modele.Carte;
import modele.GrapheOriente;
import modele.Membres;
import modele.Scenario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestGraphe extends VBox {
    public  TestGraphe() throws IOException {
        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/files/scenario/scenario_2_1.txt"));
        GrapheOriente test = new GrapheOriente(scenario, carte, membres);
        System.out.println(test.sourceEnSource(test.getSource(),test.getDegreEntrant(),carte));
        //System.out.println(test.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),test.getSource(),test.getDegreEntrant(),carte,0));
        List<List<Integer>> chaine = test.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),test.getSource(),test.getDegreEntrant(),carte);
        System.out.println(chaine.size());
        System.out.println( "--------------------------------------");
        for (List<Integer> elem : chaine){
               System.out.println(GrapheOriente.CalculDistanceChemin(elem,carte));
        }
        System.out.println( "--------------------------------------");
    }
}
