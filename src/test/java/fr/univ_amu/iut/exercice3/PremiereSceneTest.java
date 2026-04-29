package fr.univ_amu.iut.exercice3;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 3 - Première Scene avec un Label. */
@ExtendWith(ApplicationExtension.class)
class PremiereSceneTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new PremiereScene().start(stage);
  }

  // --- Étape 1 : afficher la fenêtre ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing())
        .as("le Stage doit être affiché - appelle show() à la fin de start()")
        .isTrue();
  }

  // --- Étape 2 : créer une Scene et l'attacher au Stage ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laSceneExiste(FxRobot robot) {
    assertThat(stage.getScene())
        .as("le Stage doit avoir une Scene attachée via setScene()")
        .isNotNull();
  }

  // --- Étape 3 : utiliser un BorderPane comme racine ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leRootEstUnBorderPane(FxRobot robot) {
    assertThat(stage.getScene().getRoot())
        .as("la racine de la Scene doit être un BorderPane - passez-le au constructeur de Scene")
        .isInstanceOf(BorderPane.class);
  }

  // --- Étape 4 : créer un Label ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void unLabelEstPresent(FxRobot robot) {
    assertThat(robot.lookup(".label").queryAll())
        .as("la scène doit contenir au moins un Label")
        .isNotEmpty();
  }

  // --- Étape 5 : donner le bon texte au Label ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelAfficheLeBonTexte(FxRobot robot) {
    Label label = robot.lookup("Bonjour, JavaFX !").queryAs(Label.class);
    assertThat(label)
        .as("un Label avec le texte exact 'Bonjour, JavaFX !' doit être visible")
        .isNotNull();
  }

  // --- Étape 6 : placer le Label au centre du BorderPane ---

  @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leLabelEstAuCentreDuBorderPane(FxRobot robot) {
    BorderPane root = (BorderPane) stage.getScene().getRoot();
    assertThat(root.getCenter())
        .as("le Label doit être placé au centre du BorderPane via setCenter()")
        .isInstanceOf(Label.class);
  }
}
