package fr.univ_amu.iut.exercice1;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

/** Tests de l'exercice 1 - Première fenêtre. */
@ExtendWith(ApplicationExtension.class)
class PremiereFenetreTest {

  private Stage stage;

  @Start
  void start(Stage stage) throws Exception {
    stage.setScene(null); // évite la fuite de Scene entre tests (TestFX réutilise le Stage)
    this.stage = stage;
    new PremiereFenetre().start(stage);
  }

  @Test
  void laFenetreEstVisible(FxRobot robot) {
    assertThat(stage.isShowing()).as("le Stage doit être affiché après un appel à show()").isTrue();
  }
}
