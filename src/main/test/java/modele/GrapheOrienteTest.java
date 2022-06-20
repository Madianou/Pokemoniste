package modele;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrapheOrienteTest implements  ConstantesTest{


    @Test
    void sourceComplet() throws IOException {
        Carte carte = new Carte();
        Membres membres = new Membres();
        Scenario scenario = Scenario.lectureScenario(new File("src/main/test/ressource/scenario_test"));
        GrapheOriente test = new GrapheOriente(scenario, carte, membres);
        List<List <Integer>> resultat = test.sourceComplet(28,new ArrayList<List<Integer>>(),new ArrayList<Integer>(),test.getSource(),test.getDegreEntrant());
        for (int i = 0; i <= resultat.size() - 1; i++){
            assertEquals(RESULTAT_SOURCE_COMPLET[i],GrapheOriente.CalculDistanceChemin(resultat.get(i),carte));
        }
    }
}