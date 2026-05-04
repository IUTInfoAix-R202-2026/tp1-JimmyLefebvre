package fr.univ_amu.iut.exercice5;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/**
 * Tests d'intégration de l'exercice 5 : construction progressive de l'IHM puis vérification du
 * comportement "bouton cliqué → label mis à jour".
 *
 * <p>Le style d'écouteur (classe nommée, classe anonyme, lambda) n'a pas d'impact sur ces tests :
 * les trois produisent le même résultat.
 */
@ExtendWith(ApplicationExtension.class)
class EvenementsBoutonTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new EvenementsBouton().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing())
        .as("le Stage doit être affiché - appelle show() à la fin de start()")
        .isTrue();
  }

  // --- Étape 2 : créer une Scene ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene())
        .as("le Stage doit avoir une Scene attachée via setScene()")
        .isNotNull();
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laRacineEstUnVBox(FxRobot robot) {
    assertThat(stage.getScene().getRoot())
        .as("la racine de la Scene doit être un VBox (empilement vertical bouton + label)")
        .isInstanceOf(VBox.class);
  }

  // --- Étape 3 : ajouter un bouton ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonExiste(FxRobot robot) {
    Button bouton = robot.lookup("#bouton-clique-moi").queryAs(Button.class);
    assertThat(bouton).as("un Button avec l'id 'bouton-clique-moi' doit être présent").isNotNull();
  }

  // --- Étape 4 : le bouton a le bon texte ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leBoutonAfficheLeBonTexte(FxRobot robot) {
    Button bouton = robot.lookup("#bouton-clique-moi").queryAs(Button.class);
    assertThat(bouton.getText()).as("le bouton doit afficher 'Clique-moi'").isEqualTo("Clique-moi");
  }

  // --- Étape 5 : ajouter un label pour le compteur ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelCompteurExiste(FxRobot robot) {
    Label compteur = robot.lookup("#compteur").queryAs(Label.class);
    assertThat(compteur).as("un Label avec l'id 'compteur' doit être présent").isNotNull();
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelAfficheZeroClicsInitialement(FxRobot robot) {
    Label compteur = robot.lookup("#compteur").queryAs(Label.class);
    assertThat(compteur.getText())
        .as("avant tout clic, le label doit afficher exactement '0 clics'")
        .isEqualTo("0 clics");
  }

  // --- Étape 6 : brancher l'écouteur et vérifier le comportement ---

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void troisClicsAffichent3Clics(FxRobot robot) {
    Button bouton = robot.lookup("#bouton-clique-moi").queryAs(Button.class);
    robot.interact(bouton::fire);
    robot.interact(bouton::fire);
    robot.interact(bouton::fire);

    Label compteur = robot.lookup("#compteur").queryAs(Label.class);
    assertThat(compteur.getText()).isEqualTo("3 clics");
  }
}
