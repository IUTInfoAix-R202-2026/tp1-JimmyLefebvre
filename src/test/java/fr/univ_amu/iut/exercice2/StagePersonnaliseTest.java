package fr.univ_amu.iut.exercice2;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 2 - Stage personnalisé. */
@ExtendWith(ApplicationExtension.class)
class StagePersonnaliseTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    // On crée un Stage neuf car initStyle() ne peut pas être appelé
    // sur un Stage qui a déjà été affiché (le primary Stage de TestFX
    // peut l'avoir été lors d'un test précédent).
    this.stage = new Stage();
    new StagePersonnalise().start(this.stage);
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing())
        .as("le Stage doit être affiché - appelle show() à la fin de start()")
        .isTrue();
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leStyleEstUndecorated(FxRobot robot) {
    assertThat(stage.getStyle()).isEqualTo(StageStyle.UNDECORATED);
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void leTitreEstDefini(FxRobot robot) {
    assertThat(stage.getTitle()).isEqualTo("Ma fenêtre personnalisée");
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void lesDimensionsSontDefinies(FxRobot robot) {
    assertThat(stage.getWidth()).isEqualTo(500.0);
    assertThat(stage.getHeight()).isEqualTo(300.0);
  }

  // @Disabled("Retire cette annotation pour activer le test")
  @Test
  void laFenetreNestPasRedimensionnable(FxRobot robot) {
    assertThat(stage.isResizable()).isFalse();
  }
}
